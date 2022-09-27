package parser;
import java.util.*;

abstract class Primary
{
	abstract void printParseTree(String indent);
	abstract Val TypeEval(HashMap<String,Val> state);
	abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}