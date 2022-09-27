package parser;

class ErrorStatement extends StatementVal
{
	String ErrorString="";

	ErrorStatement(String s)
	{
		ErrorString=s;
	}

	@Override
	StatementVal cloneVal() {
		// TODO Auto-generated method stub
		return new ErrorStatement(ErrorString);
	}

	@Override
	String getErrorString() {
		// TODO Auto-generated method stub
		return ErrorString;
	}

	
}