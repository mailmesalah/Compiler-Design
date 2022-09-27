package parser;
import java.util.*;

class NotPrimary extends Primary
{
	Primary pr;
	NotPrimary(Primary p)
	{
		pr=p;
	}

	void printParseTree(String indent)
	{
		String indent1= indent+" ";
		lexArithArray.displayln(indent + indent.length() + " <primary> ");
		lexArithArray.displayln(indent1 + indent1.length() + " ! ");
		pr.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		return pr.Eval(state);
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}
}