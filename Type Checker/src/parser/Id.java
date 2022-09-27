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

	Val Eval(HashMap<String,Val> state)
	{
		/* 
		idVal = state.get(id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			System.out.println( "variable "+id+" does not have a value" );
			return null;
		}*/
		
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
}