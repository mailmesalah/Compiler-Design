package parser;
import java.util.*;

class True extends Primary
{
	
	True()
	{
		
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <primary> true");		
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