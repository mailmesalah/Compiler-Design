package parser;
import java.util.*;

class Floatp extends Primary
{
	float val;

	Floatp(float f)
	{
		val = f;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <primary> " + val);
	}

	Val TypeEval(HashMap<String,Val> state)
	{
		return new TypeFloat(val);				
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + val);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return new TypeFloat(val);
	}
}