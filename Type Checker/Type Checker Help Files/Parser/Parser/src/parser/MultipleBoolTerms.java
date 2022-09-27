package parser; 
import java.util.*;

class MultipleBoolTerms extends Expression
{
	BoolTerm bt;
	Expression ex;

	MultipleBoolTerms(BoolTerm d, Expression dl)
	{
		bt = d;
		ex = dl;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent+" ";
		
		lexArithArray.displayln(indent + indent.length() + " <expr>");
		bt.printParseTree(indent1);
		indent1 = indent+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " ||");		
		ex.printParseTree(indent1);
	}

	void emitInstructions()
	{
		bt.emitInstructions();
		ex.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
