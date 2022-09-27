package parser;

import java.util.*;

class AddE extends E {
	Term term;
	E e;

	AddE(Term t, E e_) {
		term = t;
		e = e_;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <E>");
		term.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " +");
		e.printParseTree(indent1);
	}

	Val TypeEval(HashMap<String, Val> state) {
		Val vTerm = term.TypeEval(state);
		Val vE = e.TypeEval(state);
		
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
			return new TypeError("Invalid Type for + Operator, found Boolean");
		}else if(vE.getClass()==TypeBoolean.class){
			return new TypeError("Invalid Type for + Operator, found Boolean");
		}else{
			return new TypeError("Type Not Compatible for + Operator");
		}
	}

	void emitInstructions() {
		term.emitInstructions();
		e.emitInstructions();
		lexArithArray.displayln("add");
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val termVal = term.Eval(state);
		Val    eVal =    e.Eval(state);
		if ( termVal == null || eVal == null )
			return null;
		
// The result will be float if one or both of the arguments are float.
		
		Class termClass = termVal.getClass();
		Class    eClass =    eVal.getClass();

		if ( termClass == TypeInt.class && eClass == TypeInt.class )
		{
			((TypeInt)termVal).val = ((TypeInt)termVal).val + ((TypeInt)eVal).val;
			return termVal;
		}
		else if ( termClass == TypeInt.class && eClass == TypeFloat.class )
		{
			((TypeFloat)eVal).val = ((TypeInt)termVal).val + ((TypeFloat)eVal).val;
			return eVal;
		}
		else if ( termClass == TypeFloat.class && (eClass == TypeFloat.class || eClass == TypeInt.class) )
		{
			((TypeFloat)termVal).val = termVal.floatVal() + eVal.floatVal();
			return termVal;
		}
		else{
			lexArithArray.displayln("float or int Type Expected for addition");
			return null;
		}
	}
}