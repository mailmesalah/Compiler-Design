package parser;
import java.util.*;

class MultipleEs extends BoolPrimary
{
	E e1;
	RelationOperator ro;
	E e2;

	MultipleEs(E p1, RelationOperator r,E p2)
	{
		e1=p1;
		ro=r;
		e2=p2;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <boolPrimary>");
		indent1 = indent1+" ";
		e1.printParseTree(indent1);
		ro.printParseTree(indent1);		
		e2.printParseTree(indent1);
	}

	void emitInstructions()
	{
		e1.emitInstructions();
		e2.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
