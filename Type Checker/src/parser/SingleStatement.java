package parser;
import java.util.*;

class SingleStatement extends StatementList
{
	Statement stmnt;

	SingleStatement(Statement d)
	{
		stmnt = d;
	}
 
	void printParseTree(String indent)
	{
		String indent1=indent+" ";
		lexArithArray.displayln(indent + indent.length() + " <s list>");
		stmnt.printParseTree(indent1);
	}
	
	void emitInstructions()
	{
		stmnt.emitInstructions();
	}

	@Override
	StatementVal Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		return stmnt.Eval(state);		
	}
}
