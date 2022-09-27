package parser;
import java.util.*;

class Id extends Primary
{
	String id;

	Id(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <primary> " + id);
	}

	Val TypeEval(HashMap<String,Val> state)
	{
				
		if(state.containsKey(id)){
			return state.get(id);
		}else{
			//Error : The Variable not declared
			return new TypeError(id+" Not Declared");
		}
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + id);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub		
		Val idVal = state.get(id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			lexArithArray.displayln("variable "+ id +" does not have a value");
			return null;
		}
	}
}