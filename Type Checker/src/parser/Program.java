package parser;


import java.util.*;

abstract class Program
{
	abstract void printParseTree(String indent);
	abstract StatementVal Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}