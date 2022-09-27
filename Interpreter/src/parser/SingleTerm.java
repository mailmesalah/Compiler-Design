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

	Val TypeEval(HashMap<String,Val> state)
	{
		return term.TypeEval(state);
	}
	
	void emitInstructions()
	{
		term.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return term.Eval(state);
	}
}