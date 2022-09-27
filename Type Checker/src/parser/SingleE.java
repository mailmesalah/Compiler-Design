package parser;
import java.util.*;

class SingleE extends BoolPrimary
{
	E e1;

	SingleE(E p1)
	{
		e1=p1;

	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <boolPrimary>");
		e1.printParseTree(indent1);

	}

	void emitInstructions()
	{
		e1.emitInstructions();

	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return e1.Eval(state);
	}
}
