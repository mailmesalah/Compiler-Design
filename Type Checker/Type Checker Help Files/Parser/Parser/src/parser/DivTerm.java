package parser; 
import java.util.*;

class DivTerm extends Term
{
	Primary primary;
	Term term;

	DivTerm(Primary p, Term t)
	{
		primary = p;
		term = t;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <term>");
		primary.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " /");
		term.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = primary.Eval(state);
		Val    termVal =    term.Eval(state);
		if ( primaryVal == null || termVal == null )
			return null;
		if ( termVal.isZero() )
		{
			System.out.println("division by 0");
			return null;
		}
		
		// The result will be float if one or both of the arguments are float.
		
		//Class primaryClass = primaryVal.getClass(); take off comment
		//Class    termClass =    termVal.getClass(); take off comment

		/*if ( primaryClass == IntVal.class && termClass == IntVal.class )
		{
			((IntVal)primaryVal).val = ((IntVal)primaryVal).val / ((IntVal)termVal).val;
			return primaryVal;
		}
		else if ( primaryClass == IntVal.class ) // termClass == FloatVal.class
		{
			((FloatVal)termVal).val = ((IntVal)primaryVal).val / ((FloatVal)termVal).val;
			return termVal;
		}
		
		else // primaryClass == FloatVal.class
		{
			((FloatVal)primaryVal).val = primaryVal.floatVal() / termVal.floatVal();
			return primaryVal;
		}*/
		return null;
	}
	
	void emitInstructions()
	{
		primary.emitInstructions();
		term.emitInstructions();
		lexArithArray.displayln("div");
	}
}