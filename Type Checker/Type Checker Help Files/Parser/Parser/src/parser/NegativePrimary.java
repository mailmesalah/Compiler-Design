package parser; 
import java.util.*;

class NegativePrimary extends Primary
{
	Primary pr;
	NegativePrimary(Primary p)
	{
		pr=p;
	}

	void printParseTree(String indent)
	{
		String indent1= indent+" ";
		lexArithArray.displayln(indent + indent.length() + " <primary> ");
		lexArithArray.displayln(indent1 + indent1.length() + " ! ");
		pr.printParseTree(indent1);
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