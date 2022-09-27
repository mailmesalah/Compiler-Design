package parser;
import java.util.*;

class TypeBoolean extends Type
{
	Boolean val;

	TypeBoolean(Boolean ident)
	{
		val= ident;
	}
	
	TypeBoolean(String ident)
	{
		System.out.println(ident);
	}

	public TypeBoolean() {
		// TODO Auto-generated constructor stub
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " boolean");
	}

	Val TypeEval(HashMap<String,Val> state)
	{
		return null;
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + val);
	}

	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return new TypeBoolean(val);
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

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
	}
}