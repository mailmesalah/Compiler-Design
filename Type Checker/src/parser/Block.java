package parser;
import java.util.*;

class Block extends Statement
{	
	StatementList st;

	Block(StatementList s)
	{
		st = s;		
	}

	void printParseTree(String indent)
	{
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " <block>");
		indent1 = indent1 + " ";
		st.printParseTree(indent1);
	}

	StatementVal Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		return st.Eval(state);		
	}
	
	void emitInstructions()
	{
		//dl.emitInstructions();
		st.emitInstructions();
		lexArithArray.displayln("Block");
	}
}