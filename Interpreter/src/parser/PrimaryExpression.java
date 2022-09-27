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

	Val TypeEval(HashMap<String,Val> state)
	{		
		return ex.TypeEval(state);
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub		
		return ex.Eval(state);
	}
}