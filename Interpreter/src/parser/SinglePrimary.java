package parser;
import java.util.*;

class SinglePrimary extends Term
{
	Primary primary;

	SinglePrimary(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <term>");
		primary.printParseTree(indent+" ");
	}

	Val TypeEval(HashMap<String,Val> state)
	{
		return primary.TypeEval(state);
	}
	
	void emitInstructions()
	{
		primary.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return primary.Eval(state);
	}
}