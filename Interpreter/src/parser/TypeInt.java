package parser;
import java.util.*;

class TypeInt extends Type
{
	int val;

	TypeInt(int ident)
	{
		val = ident;
	}

	public TypeInt() {
		// TODO Auto-generated constructor stub
	}

	TypeInt(String ident)
	{
		System.out.println(ident);
	}
	
	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " int");
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
		return new TypeInt(val);
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		return (float)val;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return val==0;
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