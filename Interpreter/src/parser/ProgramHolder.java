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

	StatementVal TypeEval(HashMap<String,Val> state)
	{		
		return st.TypeEval(state);
	}
	
	void emitInstructions()
	{
		dl.emitInstructions();
		st.emitInstructions();
		lexArithArray.displayln("Program");
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		dl.M(state);
		st.M(state);
	}
}