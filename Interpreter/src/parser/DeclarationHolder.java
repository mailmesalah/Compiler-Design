package parser;
import java.util.*;

class DeclarationHolder extends Declaration
{
	Type ty;
	IdList il;

	DeclarationHolder(Type t, IdList i)
	{
		ty = t;
		il = i;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent;
		lexArithArray.displayln(indent1 + indent1.length() + " <var declarations>");
		indent1 = indent1+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " <var dec>");
		indent1 = indent1+" ";
		ty.printParseTree(indent1);		
		il.printParseTree(indent1);
	}


	
	void emitInstructions()
	{
		ty.emitInstructions();
		il.emitInstructions();
		lexArithArray.displayln("Program");
	}

	@Override
	Val TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub

	}


}