package parser;
import java.util.*;

class GreaterThan extends RelationOperator
{
	String id;

	GreaterThan(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " >");
	}

	Val TypeEval(HashMap<String,Val> state)
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

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}