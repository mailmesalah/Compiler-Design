package parser;
import java.util.*;

abstract class IdList
{
	abstract void printParseTree(String indent);
	abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}