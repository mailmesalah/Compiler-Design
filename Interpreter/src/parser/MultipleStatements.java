package parser;
import java.util.*;

class MultipleStatements extends StatementList
{
	Statement stmnt;
	StatementList stmntL;

	MultipleStatements(Statement d, StatementList dl)
	{
		stmnt = d;
		stmntL = dl;
	}
 
	void printParseTree(String indent)
	{		
		String indent1=indent;
		lexArithArray.displayln(indent1 + indent1.length() + " <s list>");
		indent1=indent1+" ";
		stmnt.printParseTree(indent1);		
		stmntL.printParseTree(indent1);
	}

	void emitInstructions()
	{
		stmnt.emitInstructions();
		stmntL.emitInstructions();
	}

	@Override
	StatementVal TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		StatementVal stType=stmnt.TypeEval(state);
		StatementVal stLType=stmntL.TypeEval(state);
		
		if(stLType.getClass()==CorrectStatement.class && stType.getClass()==CorrectStatement.class){
			return new CorrectStatement();
		}else if(stLType.getClass()==ErrorStatement.class){
			return stLType;
		}else if(stType.getClass()==ErrorStatement.class){
			return stType;
		}else{
			return new ErrorStatement("Error in Multiple Statement");
		}		
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		stmnt.M(state);
		stmntL.M(state);
	}
}
