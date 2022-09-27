package parser;
import java.util.*;

class DoWhile extends Statement
{	
	Expression doEx;
	Statement doSt;

	DoWhile(Statement is,Expression e)
	{
		doEx = e;
		doSt= is;

	}

	void printParseTree(String indent)
	{
		String indent1 = indent;
		
		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1=indent1+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " do");		
		doSt.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " while");
		doEx.printParseTree(indent1);	
	}

	Val Eval(HashMap<String,Val> state)
	{
		//Val termVal = dl.Eval(state);
		Val    eVal =    doSt.Eval(state);
		if (eVal == null )
			return null;
		
		// The result will be float do one or both of the arguments are float.
		
		//Class termClass = termVal.getClass();
		Class    eClass =    eVal.getClass();

		/*do ( termClass == IntVal.class && eClass == IntVal.class )
		{
			((IntVal)termVal).val = ((IntVal)termVal).val + ((IntVal)eVal).val;
			return termVal;
		}
		else do ( termClass == IntVal.class ) // eClass == FloatVal.class
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
		doSt.emitInstructions();
		lexArithArray.displayln("Block");
	}
}