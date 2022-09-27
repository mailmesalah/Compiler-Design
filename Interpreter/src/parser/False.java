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

	Val TypeEval(HashMap<String,Val> state)
	{
		return new TypeBoolean(false);
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return new TypeBoolean(false);
	}
}