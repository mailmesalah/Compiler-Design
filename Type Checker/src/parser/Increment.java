package parser;
import java.util.*;

class Increment extends Statement
{
	String id;	

	Increment(String d)
	{
		id = d;		
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent;
		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " <increment>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " "+id);
		lexArithArray.displayln(indent1 + indent1.length() + " ++");
	}

	void emitInstructions()
	{
		//id.emitInstructions();
		//ex.emitInstructions();
	}

	@Override
	StatementVal Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		if(!state.containsKey(id)){
			return new ErrorStatement(id+" Not Declared");
		}
		
		Val idType=state.get(id);
		
		if(idType.getClass()==TypeInt.class || idType.getClass()==TypeFloat.class){
			return new CorrectStatement();
		}else if(idType.getClass()==TypeBoolean.class){
			return new ErrorStatement("Invalid Type, int/float Expected, boolean Found");
		}else{
			return new ErrorStatement("Invalid Type, int/float Expected");
		}		
	}
}
