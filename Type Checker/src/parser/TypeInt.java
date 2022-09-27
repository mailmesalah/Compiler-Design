package parser;
import java.util.*;

class TypeInt extends Type
{
	String id;

	TypeInt(String ident)
	{
		id = ident;
	}

	public TypeInt() {
		// TODO Auto-generated constructor stub
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " int");
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