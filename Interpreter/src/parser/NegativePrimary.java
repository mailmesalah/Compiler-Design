package parser;
import java.util.*;

class NegativePrimary extends Primary
{
	Primary pr;
	NegativePrimary(Primary p)
	{
		pr=p;
	}

	void printParseTree(String indent)
	{
		String indent1= indent+" ";
		lexArithArray.displayln(indent + indent.length() + " <primary> ");
		lexArithArray.displayln(indent1 + indent1.length() + " - ");
		pr.printParseTree(indent1);
	}

	Val TypeEval(HashMap<String,Val> state)
	{
		return pr.TypeEval(state);
	}
	
	void emitInstructions()
	{
		//lexArithArray.displayln("push " + id);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val eVal=pr.Eval(state);
		if(eVal.getClass()==TypeInt.class){
			return new TypeInt(((TypeInt)eVal).val*-1);
		}else if(eVal.getClass()==TypeFloat.class){
			return new TypeFloat(((TypeFloat)eVal).val*-1);
		}
		return null;
	}
}