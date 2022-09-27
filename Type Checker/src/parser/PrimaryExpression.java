package parser;
import java.util.*;

class PrimaryExpression extends Primary
{
	Expression ex;

	PrimaryExpression(Expression e)
	{
		ex = e;
	}

	void printParseTree(String indent)
	{
		String indent1=indent+" ";
		lexArithArray.displayln(indent + indent.length() + " <primary>");		//
		ex.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{		
		return ex.Eval(state);
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}
}