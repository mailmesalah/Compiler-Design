package parser;
import java.util.*;

abstract class IdList
{
	abstract void printParseTree(String indent);
	abstract Val TypeEval(HashMap<String,Val> state);
	abstract void M(HashMap<String,Val> state);
	abstract void emitInstructions();
}