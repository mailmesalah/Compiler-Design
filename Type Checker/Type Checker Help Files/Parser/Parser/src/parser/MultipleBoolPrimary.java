package parser; 
import java.util.*;

class MultipleBoolPrimary extends BoolTerm
{
	BoolPrimary bp;
	BoolTerm bt;

	MultipleBoolPrimary(BoolPrimary p1, BoolTerm p2)
	{
		bp=p1;
		bt=p2;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent;
		
		lexArithArray.displayln(indent1 + indent1.length() + " <boolTerm>");
		indent1 = indent1+" ";
		bp.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " &&");
		bt.printParseTree(indent1);		
	}

	void emitInstructions()
	{
		bp.emitInstructions();
		bt.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
