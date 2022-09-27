package parser;
import java.util.*;

class TypeError extends Type
{
	String ErrorString="";
	

	TypeError(String s)
	{
		ErrorString=s;
	}

	void printParseTree(String indent)
	{
	
	}

	Val Eval(HashMap<String,Val> state)
	{
		return null;
	}
	
	void emitInstructions()
	{
	
	}

	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	String getErrorString() {
		// TODO Auto-generated method stub
		return ErrorString;
	}
}