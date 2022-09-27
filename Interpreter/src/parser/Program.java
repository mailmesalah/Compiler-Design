package parser;


import java.util.*;

abstract class Program
{
	abstract void printParseTree(String indent);
	abstract StatementVal TypeEval(HashMap<String,Val> state);
	abstract void M(HashMap<String,Val> state);
	abstract void emitInstructions();
}