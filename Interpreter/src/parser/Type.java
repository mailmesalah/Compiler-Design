package parser;
import java.util.*;


abstract class Type extends Val
{
	abstract void printParseTree(String indent);
	abstract Val TypeEval(HashMap<String,Val> state);
	abstract void M(HashMap<String,Val> state);
	abstract void emitInstructions();
	abstract String getErrorString();
	
}