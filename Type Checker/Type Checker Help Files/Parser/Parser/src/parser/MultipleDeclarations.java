package parser; 
import java.util.*;

class MultipleDeclarations extends DeclarationList
{
	Declaration d;
	DeclarationList dl;

	MultipleDeclarations(Declaration dec, DeclarationList decL)
	{
		d = dec;
		dl = decL;
	}

	void printParseTree(String indent)
	{
		d.printParseTree(indent);
		dl.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val termVal = d.Eval(state);
		Val    eVal =    dl.Eval(state);
		if ( termVal == null || eVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		//Class termClass = termVal.getClass(); take off comment
		//Class    eClass =    eVal.getClass(); take off comment

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
		d.emitInstructions();
		dl.emitInstructions();
		lexArithArray.displayln("Multiple Declarations");
	}
}