package parser; 
import java.util.*;

class SingleTerm extends E
{
	Term term;

	SingleTerm(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		String indent1=indent+" ";
		lexArithArray.displayln(indent + indent.length() + " <E>");
		term.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		return term.Eval(state);
	}
	
	void emitInstructions()
	{
		term.emitInstructions();
	}
}