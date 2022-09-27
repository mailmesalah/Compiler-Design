package parser;
import java.util.*;

class SingleBoolPrimary extends BoolTerm
{
	BoolPrimary bp;

	SingleBoolPrimary(BoolPrimary p1)
	{
		bp=p1;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent + " ";
		
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		bp.printParseTree(indent1);
	}

	void emitInstructions()
	{
		bp.emitInstructions();
	}

	@Override
	Val TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return bp.TypeEval(state);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return bp.Eval(state);		
	}
}
