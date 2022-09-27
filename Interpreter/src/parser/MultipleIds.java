package parser;
import java.util.*;

class MultipleIds extends IdList
{
	IdList id;
	IdList idL;

	MultipleIds(IdList d, IdList dl)
	{
		id = d;
		idL = dl;
	}
 
	void printParseTree(String indent)
	{		
		id.printParseTree(indent);
		idL.printParseTree(indent+" ");
	}

	void emitInstructions()
	{
		id.emitInstructions();
		idL.emitInstructions();
	}

	@Override
	Val TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		id.M(state);
		idL.M(state);
	}
}
