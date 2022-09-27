package parser;
import java.util.*;

class TypeFloat extends Type
{
	float val;

	TypeFloat(float ident)
	{
		val = ident;
	}

	public TypeFloat() {
		// TODO Auto-generated constructor stub
	}
	
	TypeFloat(String ident)
	{
		System.out.println(ident);
	}
	
	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " float");
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
		return new TypeFloat(val);
	}

	@Override
	float floatVal() {
		// TODO Auto-generated method stub
		return val;
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