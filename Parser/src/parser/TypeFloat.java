package parser;
import java.util.*;

class TypeFloat extends Type
{
	String id;

	TypeFloat(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " float");
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val idVal = state.get(id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			System.out.println( "variable "+id+" does not have a value" );
			return null;
		}
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + id);
	}
}