package parser;
// value objects used for statement evaluation and returned by Eval function

abstract class StatementVal
{
	abstract StatementVal cloneVal();	
	abstract String getErrorString();
}
