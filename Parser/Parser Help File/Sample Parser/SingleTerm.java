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
		lexArithArray.displayln(indent + indent.length() + " <E>");
		term.printParseTree(indent+" ");
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