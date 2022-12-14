package parser;
import java.util.*;

class DeclarationHolder extends Declaration
{
	Type ty;
	IdList il;

	DeclarationHolder(Type t, IdList i)
	{
		ty = t;
		il = i;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent;
		lexArithArray.displayln(indent1 + indent1.length() + " <var declarations>");
		indent1 = indent1+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " <var dec>");
		indent1 = indent1+" ";
		ty.printParseTree(indent1);		
		il.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val termVal = ty.Eval(state);
		Val    eVal =    il.Eval(state);
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
		ty.emitInstructions();
		il.emitInstructions();
		lexArithArray.displayln("Program");
	}
}