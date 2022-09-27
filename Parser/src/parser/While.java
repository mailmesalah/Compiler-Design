package parser;
import java.util.*;

class While extends Statement
{	
	Expression whileEx;
	Statement whileSt;

	While(Expression e,Statement is)
	{
		whileEx = e;
		whileSt= is;

	}

	void printParseTree(String indent)
	{
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " while");		
		whileEx.printParseTree(indent1);				
		whileSt.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		//Val termVal = dl.Eval(state);
		Val    eVal =    whileSt.Eval(state);
		if (eVal == null )
			return null;
		
		// The result will be float while one or both of the arguments are float.
		
		//Class termClass = termVal.getClass();
		Class    eClass =    eVal.getClass();

		/*while ( termClass == IntVal.class && eClass == IntVal.class )
		{
			((IntVal)termVal).val = ((IntVal)termVal).val + ((IntVal)eVal).val;
			return termVal;
		}
		else while ( termClass == IntVal.class ) // eClass == FloatVal.class
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
		whileSt.emitInstructions();
		lexArithArray.displayln("Block");
	}
}