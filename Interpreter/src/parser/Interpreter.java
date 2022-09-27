package parser;

import java.io.*;
import java.util.*;

public abstract class Interpreter extends Parser
{
	public static HashMap<String, Val> varState = new HashMap<String, Val>(); 
	              // program state, i.e., virtual memory for variables
	public static void printVariablesNValues() {
		// Get a set of the entries
		Set<?> set = varState.entrySet();
		// Get an iterator
		Iterator<?> i = set.iterator();
		// Display elements
		display("{");
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			display(me.getKey() + "=");
			if(me.getValue().getClass()==TypeInt.class){
				display(((TypeInt)me.getValue()).val+"");				
			}else if(me.getValue().getClass()==TypeFloat.class){
				display(((TypeFloat)me.getValue()).val+"");
			}else if(me.getValue().getClass()==TypeBoolean.class){
				display(((TypeBoolean)me.getValue()).val+"");
			}			
			if(i.hasNext())
				display(",");
		}
		displayln("}");
	}
	
	public static void main(String argv[])
	{
		// argv[0]: input file containing the source code of an assignment list
		// argv[1]: output file to display the parse tree

		if (argv.length == 2) {

			setLex(argv[0], argv[1]);
			//setLex("1.txt","2.txt");

			Program program = getProgram(); // build a parse tree
			StatementVal sv =program.TypeEval(variablesNtypes);
			varState=variablesNtypes;
			if (!t.isEmpty()){						
				//displayln(t + "  -- Unexpected Symbol");
			}else if (sv.getClass()!=ErrorStatement.class && !errorFound) {
				//program.printParseTree(""); // print the parse tree in
				//linearly				
				//printVariablesNTypes();								
				program.M(varState );
				printVariablesNValues();				
			}else if(sv.getClass()==ErrorStatement.class){
				//printVariablesNTypes();
				displayln(sv.getErrorString());
			}
			closeIO();
		}else{
			System.out.println("Arguments Not Optional !");
		}

		/*getToken();
		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! errorFound )
		{
			assignmentList.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
			assignmentList.M(varState);              // interpret the assignment list
			System.out.println(varState.toString()); // print the program state on the terminal
		}
		closeIO();*/
	}
}
