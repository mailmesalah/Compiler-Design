package parser;
import java.util.*;

class Assignment extends Statement
{
	String id;
	Expression ex;

	Assignment(String d, Expression e)
	{
		id = d;
		ex = e;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " <assignment>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " " + id);
		lexArithArray.displayln(indent1 + indent1.length() + " =");
		ex.printParseTree(indent1);
	}

	void emitInstructions()
	{
		//id.emitInstructions();
		ex.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
