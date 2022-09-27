package parser;
import java.util.*;

class Int extends Primary
{
	int val;

	Int(int i)
	{
		val = i;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <primary> " + val);
	}

	Val TypeEval(HashMap<String,Val> state)
	{
		return new TypeInt(val);
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + val);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return new TypeInt(val);
	}
}