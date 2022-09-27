import java.io.*;
import java.util.*;

public abstract class Interpreter extends Parser
{
	public static HashMap<String, Val> varState = new HashMap<String, Val>(); 
	              // program state, i.e., virtual memory for variables
		
	
	public static void main(String argv[])
	{
		// argv[0]: input file containing the source code of an assignment list
		// argv[1]: output file to display the parse tree
		
		setLex( argv[0], argv[1] );

		getToken();
		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! errorFound )
		{
			assignmentList.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
			assignmentList.M(varState);              // interpret the assignment list
			System.out.println(varState.toString()); // print the program state on the terminal
		}
		closeIO();
	}
}
