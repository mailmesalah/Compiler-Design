package parser;
import java.util.*;

class ProgramHolder extends Program
{
	DeclarationList dl;
	Statement st;

	ProgramHolder(DeclarationList dec, Statement stat)
	{
		dl = dec;
		st = stat;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <program>");
		dl.printParseTree(indent1);		
		st.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val termVal = dl.Eval(state);
		Val    eVal =    st.Eval(state);
		if ( termVal == null || eVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		Class termClass = termVal.getClass();
		Class    eClass =    eVal.getClass();

		/*if ( termClass == IntVal.class && eClass == IntVal.class )
		{
			((IntVal)termVal).val = ((IntVal)termVal).val + ((IntVal)eVal).val;
			return termVal;
		}
		else if ( termClass == IntVal.class ) // eClass == FloatVal.class
		{
			((FloatVal)eVal).val = ((IntVal)termVal).val + ((FloatVal)eVal).val;
			return eVal;
		}
		else // termClass == FloatVal.class
		{
			((FloatVal)termVal).val = termVal.floatVal() + eVal.floatVal();
			return termVal;
		}*/	
		return null;
	}
	
	void emitInstructions()
	{
		dl.emitInstructions();
		st.emitInstructions();
		lexArithArray.displayln("Program");
	}
}