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

	Val Eval(HashMap<String, Val> state) {
		Val vTerm = term.Eval(state);
		Val vE = e.Eval(state);
		
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
}