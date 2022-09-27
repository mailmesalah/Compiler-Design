package parser; 
import java.util.*;

class IfWithElse extends Statement
{	
	Expression ifEx;
	Statement ifSt;
	Statement elseSt;

	IfWithElse(Expression e,Statement is,Statement es)
	{
		ifEx = e;
		ifSt= is;
		elseSt= es;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " if");		
		ifEx.printParseTree(indent1);
		ifSt.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " else");
		elseSt.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		//Val termVal = dl.Eval(state);
		Val    eVal =    ifSt.Eval(state);
		if (eVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		//Class termClass = termVal.getClass();
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
		//dl.emitInstructions();
		ifSt.emitInstructions();
		lexArithArray.displayln("Block");
	}
}