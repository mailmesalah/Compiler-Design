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

	Val TypeEval(HashMap<String, Val> state) {
		Val vTerm = term.TypeEval(state);
		Val vE = primary.TypeEval(state);
		
		if(vTerm.getClass()==TypeInt.class && vE.getClass()==TypeInt.class){
			return new TypeInt();
		}else if(vTerm.getClass()==TypeInt.class && vE.getClass()==TypeFloat.class){
			return new TypeFloat();
		}else if(vTerm.getClass()==TypeFloat.class && vE.getClass()==TypeInt.class){
			return new TypeFloat();
		}else if(vTerm.getClass()==TypeFloat.class && vE.getClass()==TypeFloat.class){
			return new TypeFloat();
		}else if(vTerm.getClass()==TypeError.class){
			return vTerm;
		}else if(vE.getClass()==TypeError.class){
			return vE;	
		}else if(vTerm.getClass()==TypeBoolean.class){
			return new TypeError("Invalid Type, int/float Expected for / operator, found boolean");
		}else if(vE.getClass()==TypeBoolean.class){
			return new TypeError("Invalid Type, int/float Expected for / operator, found boolean");
		}else{
			return new TypeError("Invalid Type, int/float Expected for / operator");
		}
	}
	
	void emitInstructions()
	{
		primary.emitInstructions();
		term.emitInstructions();
		lexArithArray.displayln("div");
	}

	@Override
	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = primary.Eval(state);
		Val    termVal =    term.Eval(state);
		if ( primaryVal == null || termVal == null )
			return null;
		if ( termVal.isZero() )
		{
			lexArithArray.displayln("division by 0");			
			return null;
		}
		
		// The result will be float if one or both of the arguments are float.
		
		Class primaryClass = primaryVal.getClass();
		Class    termClass =    termVal.getClass();

		if ( primaryClass == TypeInt.class && termClass == TypeInt.class )
		{
			((TypeInt)primaryVal).val = ((TypeInt)primaryVal).val / ((TypeInt)termVal).val;
			return primaryVal;
		}
		else if ( primaryClass == TypeInt.class ) // termClass == FloatVal.class
		{
			((TypeFloat)termVal).val = ((TypeInt)primaryVal).val / ((TypeFloat)termVal).val;
			return termVal;
		}
		
		else // primaryClass == FloatVal.class
		{
			((TypeFloat)primaryVal).val = primaryVal.floatVal() / termVal.floatVal();
			return primaryVal;
		}
	}
}