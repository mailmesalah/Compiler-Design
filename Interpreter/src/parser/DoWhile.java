package parser;
import java.util.*;

class DoWhile extends Statement
{	
	Expression doEx;
	Statement doSt;

	DoWhile(Statement is,Expression e)
	{
		doEx = e;
		doSt= is;

	}

	void printParseTree(String indent)
	{
		String indent1 = indent;
		
		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1=indent1+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " do");		
		doSt.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " while");
		doEx.printParseTree(indent1);	
	}

	StatementVal TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		Val expType=doEx.TypeEval(state);
		StatementVal stType=doSt.TypeEval(state);
		
		if(expType.getClass()==TypeBoolean.class && stType.getClass()==CorrectStatement.class){
			return new CorrectStatement();
		}else if(expType.getClass()==TypeError.class){
			return new ErrorStatement(expType.getErrorString());
		}else if(expType.getClass()==TypeInt.class){
			return new ErrorStatement("Error in Do While Statement, boolean Expected, int found");
		}else if(expType.getClass()==TypeFloat.class){
			return new ErrorStatement("Error in Do While Statement, boolean Expected, float found");
		}else if(stType.getClass()==ErrorStatement.class){
			return stType;
		}else{
			return new ErrorStatement("Error in Do While Statement, boolean Expected");
		}		
	}
	
	void emitInstructions()
	{
		//dl.emitInstructions();
		doSt.emitInstructions();
		lexArithArray.displayln("Block");
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		doSt.M(state);
		Val exprVal = doEx.Eval(state);
		while ( exprVal != null && ((TypeBoolean)exprVal).val )
		{
			doSt.M(state);
			exprVal = doEx.Eval(state);
		}
	}
}