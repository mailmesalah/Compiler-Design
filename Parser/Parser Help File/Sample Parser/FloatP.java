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

	Val Eval(HashMap<String,Val> state)
	{
		return new FloatVal(val);
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + val);
	}
}