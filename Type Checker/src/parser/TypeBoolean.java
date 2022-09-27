package parser;
import java.util.*;

class TypeBoolean extends Type
{
	String id;

	TypeBoolean(String ident)
	{
		id = ident;
	}

	public TypeBoolean() {
		// TODO Auto-generated constructor stub
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " boolean");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return null;
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + id);
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
		return null;
	}
}