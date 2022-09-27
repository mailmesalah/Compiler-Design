package parser;
import java.util.*;

class False extends Primary
{
	
	False()
	{
		
	}

	void printParseTree(String indent)
	{		
		lexArithArray.displayln(indent + indent.length() + " <primary> false");		
	}

	Val Eval(HashMap<String,Val> state)
	{
		/*Val idVal = state.get(id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			System.out.println( "variable "+id+" does not have a value" );
			return null;
		}*/
		return null;
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}
}