package parser;
import java.util.*;

class MultipleBoolTerms extends Expression
{
	BoolTerm bt;
	Expression ex;

	MultipleBoolTerms(BoolTerm d, Expression dl)
	{
		bt = d;
		ex = dl;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent+" ";
		
		lexArithArray.displayln(indent + indent.length() + " <expr>");
		bt.printParseTree(indent1);
		indent1 = indent+" ";
		lexArithArray.displayln(indent1 + indent1.length() + " ||");		
		ex.printParseTree(indent1);
	}

	void emitInstructions()
	{
		bt.emitInstructions();
		ex.emitInstructions();
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val vBt=bt.Eval(state);
		Val vEx=ex.Eval(state);
		
		if(vBt.getClass()==TypeBoolean.class && vEx.getClass()==TypeBoolean.class){
			return vBt;
		}else if(vBt.getClass()==TypeError.class){
			return vBt;
		}else if(vEx.getClass()==TypeError.class){
			return vEx;
		}else if(vBt.getClass()==TypeInt.class){
			return new TypeError("boolean expected for && Operator, int Found");
		}else if(vEx.getClass()==TypeInt.class){
			return new TypeError("boolean expected for && Operator, int Found");
		}else if(vBt.getClass()==TypeFloat.class){
			return new TypeError("boolean expected for && Operator, float Found");
		}else if(vEx.getClass()==TypeFloat.class){
			return new TypeError("boolean expected for && Operator, float Found");
		}else{
			return new TypeError("boolean expected for || Operator");
		}
		
	}
}
