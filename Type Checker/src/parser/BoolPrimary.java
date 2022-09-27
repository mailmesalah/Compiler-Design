package parser;

import java.util.*;

abstract class BoolPrimary
{
	abstract void printParseTree(String indent);
	abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}