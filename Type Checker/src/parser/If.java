package parser;
import java.util.*;

class If extends Statement
{	
	Expression ifEx;
	Statement ifSt;

	If(Expression e,Statement is)
	{
		ifEx = e;
		ifSt= is;

	}

	void printParseTree(String indent)
	{
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " if");		
		ifEx.printParseTree(indent1);				
		ifSt.printParseTree(indent1);
	}

	StatementVal Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		Val expType=ifEx.Eval(state);
		StatementVal stType=ifSt.Eval(state);
		
		if(expType.getClass()==TypeBoolean.class && stType.getClass()==CorrectStatement.class){
			return new CorrectStatement();
		}else if(expType.getClass()==TypeError.class){
			return new ErrorStatement(expType.getErrorString());
		}else if(expType.getClass()==TypeInt.class){
			return new ErrorStatement("Invalid If Statement, boolean Expected, int found");
		}else if(expType.getClass()==TypeFloat.class){
			return new ErrorStatement("Invalid If Statement, boolean Expected, float found");
		}else if(stType.getClass()==ErrorStatement.class){
			return stType;
		}else{
			return new ErrorStatement("Invalid If Statement, boolean Expected");
		}		
	}
	
	void emitInstructions()
	{
		//dl.emitInstructions();
		ifSt.emitInstructions();
		lexArithArray.displayln("Block");
	}
}