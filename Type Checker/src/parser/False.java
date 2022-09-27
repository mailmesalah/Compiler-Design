package parser;
import java.util.*;

class False extends Primary
{
	
	False()
	{
		
	}

	void printParseTree(String indent)
	{		
		lexArithArray.displayln(indent + indent.length() + " <primary> false");		
	}

	Val Eval(HashMap<String,Val> state)
	{
		return new TypeBoolean();
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}
}