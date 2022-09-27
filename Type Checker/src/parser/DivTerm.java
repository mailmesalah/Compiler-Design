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

	Val Eval(HashMap<String, Val> state) {
		Val vTerm = term.Eval(state);
		Val vE = primary.Eval(state);
		
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
}