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
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
