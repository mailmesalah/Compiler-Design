package parser;
import java.util.*;

class SingleBoolTerm extends Expression
{
	BoolTerm bt;

	SingleBoolTerm(BoolTerm d)
	{
		bt = d;
	}
 
	void printParseTree(String indent)
	{	
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <expr>");
		bt.printParseTree(indent1);
	}

	void emitInstructions()
	{
		bt.emitInstructions();
	}

	@Override
	Val TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return bt.TypeEval(state);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return bt.Eval(state);
	}
}
