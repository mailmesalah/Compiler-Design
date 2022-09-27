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

	Val TypeEval(HashMap<String,Val> state)
	{
		return new TypeBoolean(true);
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return new TypeBoolean(true);
	}
}