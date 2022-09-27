package parser; 
import java.util.*;

class SingleId extends IdList
{
	String id;

	SingleId(String s)
	{
		id = s;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <id list>");
		lexArithArray.displayln(indent1 + indent1.length() + " "+id);
	}

	Val Eval(HashMap<String,Val> state)
	{
		//return term.Eval(state);
		return null;
	}
	
	void emitInstructions()
	{
		//term.emitInstructions();
	}
}