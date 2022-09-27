import java.util.*;

abstract class E
{
	abstract void printParseTree(String indent);
	abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}