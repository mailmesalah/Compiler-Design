public abstract class Compiler extends Parser
{
			
	public static void main(String argv[])
	{
		// argv[0]: input file containing the source code of an assignment list
		// argv[1]: output file containing the compiled instruction stream
		
		setLex( argv[0], argv[1] );

		getToken();
		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! errorFound )
		{
			assignmentList.emitInstructions();
			closeIO();
		}
	}
}
