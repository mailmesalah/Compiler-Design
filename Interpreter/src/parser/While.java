package parser;
import java.util.*;

class While extends Statement
{	
	Expression whileEx;
	Statement whileSt;

	While(Expression e,Statement is)
	{
		whileEx = e;
		whileSt= is;

	}

	void printParseTree(String indent)
	{
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " while");		
		whileEx.printParseTree(indent1);				
		whileSt.printParseTree(indent1);
	}

	StatementVal TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		Val expType=whileEx.TypeEval(state);
		StatementVal stType=whileSt.TypeEval(state);
		
		if(expType.getClass()==TypeBoolean.class && stType.getClass()==CorrectStatement.class){
			return new CorrectStatement();
		}else if(expType.getClass()==TypeError.class){
			return new ErrorStatement(expType.getErrorString());
		}else if(stType.getClass()==ErrorStatement.class){
			return stType;
		}else if(expType.getClass()==TypeInt.class){
			return new ErrorStatement("Type Error in While Statement, boolean expected, int Found");
		}else if(expType.getClass()==TypeFloat.class){
			return new ErrorStatement("Type Error in While Statement, boolean expected, float Found");
		}else{
			//Need to be changed to show correct error
			return new ErrorStatement("Error in While Statement");
		}		
	}
	
	void emitInstructions()
	{
		//dl.emitInstructions();
		whileSt.emitInstructions();
		lexArithArray.displayln("Block");
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub		
		Val exprVal = whileEx.Eval(state);
		while ( exprVal != null && ((TypeBoolean)exprVal).val )
		{
			whileSt.M(state);
			exprVal = whileEx.Eval(state);
		}
	}
}