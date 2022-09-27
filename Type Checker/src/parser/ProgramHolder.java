package parser;
import java.util.*;

class ProgramHolder extends Program
{
	DeclarationList dl;
	Statement st;

	ProgramHolder(DeclarationList dec, Statement stat)
	{
		dl = dec;
		st = stat;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <program>");
		dl.printParseTree(indent1);		
		st.printParseTree(indent1);
	}

	StatementVal Eval(HashMap<String,Val> state)
	{		
		return st.Eval(state);
	}
	
	void emitInstructions()
	{
		dl.emitInstructions();
		st.emitInstructions();
		lexArithArray.displayln("Program");
	}
}