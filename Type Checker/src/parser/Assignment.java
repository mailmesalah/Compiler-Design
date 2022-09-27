package parser;
import java.util.*;

class Assignment extends Statement
{
	String id;
	Expression ex;

	Assignment(String d, Expression e)
	{
		id = d;
		ex = e;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " <assignment>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " " + id);
		lexArithArray.displayln(indent1 + indent1.length() + " =");
		ex.printParseTree(indent1);
	}

	void emitInstructions()
	{
		//id.emitInstructions();
		ex.emitInstructions();
	}

	@Override
	StatementVal Eval(HashMap<String, Val> state) {
		/*
		 * If variable is not declared return Error
		 */
		if(!state.containsKey(id)){
			return new ErrorStatement(id+" Not Declared");
		}
		
		Val idType=state.get(id);			
		Val expType=ex.Eval(state);
		
		if((idType.getClass()==expType.getClass() && idType.getClass() != TypeError.class) || (idType.getClass()==TypeFloat.class && expType.getClass()==TypeInt.class)){
			return new CorrectStatement();
		}else if(idType.getClass()==TypeError.class){
			return new ErrorStatement(idType.getErrorString());
		}else if(expType.getClass()==TypeError.class){
			return new ErrorStatement(expType.getErrorString());
		}else if(expType.getClass()==TypeBoolean.class && idType.getClass()==TypeFloat.class){
			return new ErrorStatement("Invalid Assignment Statement, boolean cannot be assigned to float");
		}else if(expType.getClass()==TypeBoolean.class && idType.getClass()==TypeInt.class){
			return new ErrorStatement("Invalid Assignment Statement, boolean cannot be assigned to int");
		}else if(expType.getClass()==TypeFloat.class && idType.getClass()==TypeBoolean.class){
			return new ErrorStatement("Invalid Assignment Statement, float cannot be assigned to boolean");
		}else if(expType.getClass()==TypeInt.class && idType.getClass()==TypeBoolean.class){
			return new ErrorStatement("Invalid Assignment Statement, int cannot be assigned to boolean");
		}else{
			return new ErrorStatement("Invalid Assignment Statement");
		}
	}
	
}
