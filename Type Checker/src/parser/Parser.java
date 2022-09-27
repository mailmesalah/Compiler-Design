package parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class is a top-down, recursive-descent parser for the following
 * syntactic categories:
 * 
 * <assignment list> --> <assignment> | <assignment> <assignment list>
 * <assignment> --> <id> = <E> ";" <E> --> <term> | <term> + <E> | <term> - <E>
 * <term> --> <primary> | <primary> * <term> | <primary> / <term> <primary> -->
 * <id> | <int> | <float> | <floatE> | "(" <E> ")"
 * 
 * The definitions of the tokens are given in the lexical analyzer class file
 * "lexArithArray.java".
 * 
 * The following variables and functions of the "lexArithArray" class are used:
 * 
 * static String t // holds an extracted token static State state // the current
 * state of the finite automaton static int getToken() // extracts the next
 * token static void display(String s) static void displayln(String s) static
 * void setIO(String inFile, String outFile) static void closeIO()
 * 
 * An explicit parse tree is constructed in the form of nested class objects.
 * 
 * The main function will display the parse tree in linearly indented form. Each
 * syntactic category name labeling a node is displayed on a separate line,
 * prefixed with the integer i representing the node's depth and indented by i
 * blanks.
 **/

public abstract class Parser extends lexArithArray {
	static boolean errorFound = false;
	static Type currentType ;

	static HashMap<String, Val> variablesNtypes = new HashMap<String, Val>();

	private static boolean setValiablesNTypes(Val type, String id) {

		if (!variablesNtypes.containsKey(id)) {
			variablesNtypes.put(id, type);
			return true;

		} else {			
			return false;
		}

	}

	private static void printVariablesNTypes() {
		// Get a set of the entries
		Set<?> set = variablesNtypes.entrySet();
		// Get an iterator
		Iterator<?> i = set.iterator();
		// Display elements
		display("{");
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			display(me.getKey() + "=");
			if(me.getValue().getClass()==TypeInt.class){
				display("int");
			}else if(me.getValue().getClass()==TypeFloat.class){
				display("float");
			}else if(me.getValue().getClass()==TypeBoolean.class){
				display("boolean");
			}			
			if(i.hasNext())
				display(",");
		}
		displayln("}");
	}

	public static Program getProgram()

	// <Program> --> <Declaration List><Statement>

	{
		getToken();
		DeclarationList decList = getDeclarationList();
		Statement stat = getStatement();
		getToken();

		return new ProgramHolder(decList, stat);
	}

	public static DeclarationList getDeclarationList()

	// <Declaration List> --> <Declaration>";"|<Declaration>";"<Declaration
	// List>

	{

		Declaration dec = getDeclaration();
		if (state == State.semicolon) {
			getToken();
			if (state == State.keyword_int || state == State.keyword_float
					|| state == State.keyword_boolean) {
				DeclarationList decList = getDeclarationList();
				return new MultipleDeclarations(dec, decList);

			} else {
				return new SingleDeclaration(dec);
			}

		} else {
			errorMsg(1);// Invalid Declaration List
			return null;
		}

	}

	public static Declaration getDeclaration()

	// <Declaration> --> <Type><Id List>

	{
		Type ty = getType();
		IdList idl = getIdList();
		return new DeclarationHolder(ty, idl);
	}

	public static Type getType()

	// <Program> --> <Variable Declarations><Statement>

	{
		if (state == State.keyword_int) {
			
			String st = t;
			getToken();
			
			TypeInt ti = new TypeInt(st);			
			currentType = ti;
			
			return ti;
		} else if (state == State.keyword_float) {
			
			String st = t;
			getToken();
			
			TypeFloat tf =new TypeFloat(st);
			currentType=tf;
			
			return tf;
		} else if (state == State.keyword_boolean) {

			String st = t;
			getToken();
			
			TypeBoolean tb =new TypeBoolean(st);
			currentType=tb;
			
			return tb;
		} else {
			errorMsg(2);// Invalid Type
			return null;
		}

	}

	public static IdList getIdList()

	// <Id List> --> <Id>|<Id>,<Id List>

	{

		if (state == State.id) {
			String str = t;
			SingleId id = new SingleId(str);
			getToken();
			if (state == State.comma) {
				getToken();
				IdList idl = getIdList();
				
				if (setValiablesNTypes(currentType, str)) {
					return new MultipleIds(id, idl);
				} else {
					// Error
					errorMsg(1,str);// Multiple declaration
					return null;
				}				
			} else {
				if (setValiablesNTypes(currentType, str)) {
					return id;
				} else {
					// Error
					errorMsg(1,str);// Multiple declaration
					return null;
				}
			}
		} else {
			// Error
			errorMsg(3);// Invalid IdList
			return null;
		}

	}

	public static Statement getStatement()

	// <statement> → <assignment> | <increment> | <decrement> | <block> | <cond>
	// | <while> | <do>

	{
		if (state == State.id) {
			String str = t;
			getToken();
			if (state == State.assign) {
				getToken();
				Expression ex = getExpression();
				if (state == State.semicolon) {
					getToken();
					return new Assignment(str, ex);
				} else {
					// Error
					errorMsg(1);// Invalid Assignment
					return null;
				}
			} else if (state == State.incr) {
				getToken();
				if (state == State.semicolon) {
					getToken();
					return new Increment(str);
				} else {
					// Error
					errorMsg(1);// Invalid Increment
					return null;
				}
			} else if (state == State.decr) {
				getToken();
				if (state == State.semicolon) {
					getToken();
					return new Decrement(str);
				} else {
					// Error
					errorMsg(1);// Invalid Decrement
					return null;
				}
			} else {
				// Error
				errorMsg(4);// Invalid Statement
				return null;
			}
		} else if (state == State.LBrace) {
			getToken();
			StatementList sl = getStatementList();
			if (state == State.RBrace) {
				getToken();
				return new Block(sl);
			} else {
				// Error
				errorMsg(5);// Invalid Block
				return null;
			}

		} else if (state == State.keyword_if) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Expression ex = getExpression();
				if (state == State.RParen) {
					getToken();
					Statement st = getStatement();
					if (state == State.keyword_else) {
						getToken();
						Statement elseSt = getStatement();
						return new IfWithElse(ex, st, elseSt);
					} else {
						return new If(ex, st);
					}
				} else {
					// Error
					errorMsg(5);// Invalid If Statement
					return null;
				}

			} else {
				// Error
				errorMsg(6);// Invalid If Statement
				return null;
			}
		} else if (state == State.keyword_while) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Expression ex = getExpression();
				if (state == State.RParen) {
					getToken();
					Statement st = getStatement();
					return new While(ex, st);
				} else {
					// Error
					errorMsg(5);// Invalid While Statement
					return null;
				}

			} else {
				// Error
				errorMsg(6);// Invalid While Statement
				return null;
			}

		} else if (state == State.keyword_do) {
			getToken();
			Statement st = getStatement();
			if (state == State.keyword_while) {
				getToken();
				if (state == State.LParen) {
					getToken();
					Expression ex = getExpression();
					if (state == State.RParen) {
						getToken();
						return new DoWhile(st, ex);
					} else {
						// Error
						errorMsg(5);// Invalid Do While Statement
						return null;
					}
				} else {
					// Error
					errorMsg(6);// Invalid Do While Statement
					return null;
				}

			} else {
				// Error
				errorMsg(7);// Invalid Do While Statement
				return null;
			}

		} else {
			// Error
			errorMsg(3);// Invalid Statement
			return null;

		}
	}

	public static StatementList getStatementList()

	// <Statement List> --> <Statement>|<Statement><Statement List>

	{
		Statement stmnt = getStatement();
		if (state == State.id) {
			StatementList sl = getStatementList();
			return new MultipleStatements(stmnt, sl);

		} else if (state == State.LBrace) {
			StatementList sl = getStatementList();
			return new MultipleStatements(stmnt, sl);

		} else if (state == State.keyword_if) {
			StatementList sl = getStatementList();
			return new MultipleStatements(stmnt, sl);
		} else if (state == State.keyword_while) {
			StatementList sl = getStatementList();
			return new MultipleStatements(stmnt, sl);
		} else if (state == State.keyword_do) {
			StatementList sl = getStatementList();
			return new MultipleStatements(stmnt, sl);
		} else {
			// Return Statement
			return new SingleStatement(stmnt);
		}

	}

	public static Expression getExpression()

	// <Expression> --> <boolTerm>|<boolTerm>||<Expression>

	{
		BoolTerm bt = getBoolTerm();
		if (state == State.or) {
			getToken();
			Expression ex = getExpression();
			return new MultipleBoolTerms(bt, ex);
		} else {
			return new SingleBoolTerm(bt);
		}

	}

	public static BoolTerm getBoolTerm()

	// <Bool Term> --> <Bool Primary>|<Bool Primary>&&<Bool Term>

	{
		BoolPrimary bp = getBoolPrimary();
		if (state == State.and) {
			getToken();
			BoolTerm bt = getBoolTerm();
			return new MultipleBoolPrimary(bp, bt);
		} else {
			return new SingleBoolPrimary(bp);
		}
	}

	public static BoolPrimary getBoolPrimary()

	// <BoolPrimary> --> <E>[<RealOperator><E>]

	{
		E e1 = getE();
		if (state == State.eq) {
			String str = t;
			EqualTo et = new EqualTo(str);
			getToken();
			E e2 = getE();
			return new MultipleEs(e1, et, e2);
		} else if (state == State.lt) {
			String str = t;
			LessThan et = new LessThan(str);
			getToken();
			E e2 = getE();
			return new MultipleEs(e1, et, e2);
		} else if (state == State.gt) {
			String str = t;
			GreaterThan et = new GreaterThan(str);
			getToken();
			E e2 = getE();
			return new MultipleEs(e1, et, e2);
		} else if (state == State.le) {
			String str = t;
			LessThanOrEqualTo et = new LessThanOrEqualTo(str);
			getToken();
			E e2 = getE();
			return new MultipleEs(e1, et, e2);
		} else if (state == State.ge) {
			String str = t;
			GreaterThanOrEqualTo et = new GreaterThanOrEqualTo(str);
			getToken();
			E e2 = getE();
			return new MultipleEs(e1, et, e2);
		} else if (state == State.neq) {
			String str = t;
			NotEqualTo et = new NotEqualTo(str);
			getToken();
			E e2 = getE();
			return new MultipleEs(e1, et, e2);
		} else {
			return new SingleE(e1);
		}
	}

	public static E getE()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		Term term = getTerm();
		if (state == State.add) {
			getToken();
			E e = getE();
			return new AddE(term, e);
		} else if (state == State.sub) {
			getToken();
			E e = getE();
			return new SubE(term, e);
		} else
			return new SingleTerm(term);
	}

	public static Term getTerm()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{
		Primary primary = getPrimary();
		if (state == State.mul) {
			getToken();
			Term term = getTerm();
			return new MulTerm(primary, term);
		} else if (state == State.div) {
			getToken();
			Term term = getTerm();
			return new DivTerm(primary, term);
		} else
			return new SinglePrimary(primary);
	}

	public static Primary getPrimary()

	// <primary> --> <id> | <int> | <float> | <floatE> |BoolLiteral| "("<Expression> ")"
	//|-<Primary>|!Primary

	{
		switch (state) {
		case inv:
			getToken();
			Primary p1 = getPrimary();
			NotPrimary np1 = new NotPrimary(p1);
			return np1;
		case sub:

			getToken();
			Primary p2 = getPrimary();
			NegativePrimary np2 = new NegativePrimary(p2);
			return np2;
		case id:

			Id id = new Id(t);
			getToken();
			return id;
		case keyword_true:

			True tr = new True();
			getToken();
			return tr;
		case keyword_false:

			False fl = new False();
			getToken();
			return fl;

		case Int:

			Int intElem = new Int(Integer.parseInt(t));
			getToken();
			return intElem;

		case Float:
		case floatE:

			Floatp floatElem = new Floatp(Float.parseFloat(t));
			getToken();
			return floatElem;

		case LParen:

			getToken();
			Expression ex = getExpression();
			if (state == State.RParen) {
				getToken();
				return new PrimaryExpression(ex);
			} else {
				// Error
				errorMsg(5);// Invalid Primary
				return null;
			}

		default:

			// Error
			errorMsg(8);// Invalid Primary
			return null;
		}
	}
	
	public static void errorMsg(int i,String s) {
		/*
		 * For Type Checking Errors
		 */
		errorFound = true;

		//display(t + ": unexpected symbol where");

		switch (i) {
		case 1:
			displayln(" Error : Multiple Declarations for Variable "+s);
			return;
		
		}
	}

	public static void errorMsg(int i) {
		/*
		 * For printing Errors while parsing
		 */
		errorFound = true;

		display(t + ": unexpected symbol where");

		switch (i) {
		case 1:
			displayln(" Error : ';' Expected");
			return;
		case 2:
			displayln(" Error : Data Type(int/float/boolean) Expected");
			return;
		case 3:
			displayln(" Error : Id Expected");
			return;
		case 4:
			displayln(" Error : '='/'++'/'--' Expected");
			return;
		case 5:
			displayln(" Error : ')' Expected");
			return;
		case 6:
			displayln(" Error : '(' Expected");
			return;
		case 7:
			displayln(" Error : 'while' Statement Expected");
			return;
		case 8:
			displayln(" Error : Primary Value Expected");
			return;
		case 9:
			displayln(" Error : Multiple Declarations Variable "+t);
			return;
		}
	}

	public static void main(String argv[]) {
		// argv[0]: input file containing the source code of an assignment list
		// argv[1]: output file to display the parse tree

		if (argv.length == 2) {

			setLex(argv[0], argv[1]);

			Program program = getProgram(); // build a parse tree
			StatementVal sv =program.Eval(variablesNtypes);
			if (!t.isEmpty()){						
				//displayln(t + "  -- Unexpected Symbol");
			}else if (sv.getClass()!=ErrorStatement.class && !errorFound) {
				// program.printParseTree(""); // print the parse tree in
				// linearly				
				printVariablesNTypes();
			}else if(sv.getClass()==ErrorStatement.class){
				printVariablesNTypes();
				displayln(sv.getErrorString());
			}
			closeIO();
		}else{
			System.out.println("Arguments Not Optional !");
		}
	}
}
