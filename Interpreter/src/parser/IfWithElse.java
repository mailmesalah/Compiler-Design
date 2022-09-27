package parser;
import java.util.*;

import sun.org.mozilla.javascript.internal.IdScriptableObject;

class IfWithElse extends Statement
{	
	Expression ifEx;
	Statement ifSt;
	Statement elseSt;

	IfWithElse(Expression e,Statement is,Statement es)
	{
		ifEx = e;
		ifSt= is;
		elseSt= es;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <statement>");
		indent1 = indent1 + " ";
		lexArithArray.displayln(indent1 + indent1.length() + " if");		
		ifEx.printParseTree(indent1);
		ifSt.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " else");
		elseSt.printParseTree(indent1);
	}

	StatementVal TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
		Val expType=ifEx.TypeEval(state);
		StatementVal ifStType=ifSt.TypeEval(state);
		StatementVal elseStType=elseSt.TypeEval(state);
		
		if(expType.getClass()==TypeBoolean.class && ifStType.getClass()==CorrectStatement.class && elseStType.getClass()==CorrectStatement.class ){
			return new CorrectStatement();
		}else if(expType.getClass()==TypeError.class){
			return new ErrorStatement(expType.getErrorString());
		}else if(expType.getClass()==TypeInt.class){
			return new ErrorStatement("Invalid If Else Statement, boolean Expected, int Found");
		}else if(expType.getClass()==TypeFloat.class){
			return new ErrorStatement("Invalid If Else Statement, boolean Expected, float Found");
		}else if(ifStType.getClass()==ErrorStatement.class){
			return ifStType;
		}else if(elseStType.getClass()==ErrorStatement.class){
			return elseStType;
		}else{
			return new ErrorStatement("Invalid If Else Statement, boolean Expected");
		}		
	}
	
	void emitInstructions()
	{
		//dl.emitInstructions();
		ifSt.emitInstructions();
		lexArithArray.displayln("Block");
	}

	@Override
	void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val exprVal = ifEx.Eval(state);
		if ( exprVal != null && ((TypeBoolean)exprVal).val ){
			ifSt.M(state);
		}else if(exprVal != null ){
			elseSt.M(state);
		}
	}
}