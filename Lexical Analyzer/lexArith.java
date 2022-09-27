
/**  *
 * This program uses the Enum type introduced in JDK 1.5.0.
 *
 * This class is a lexical analyzer for the tokens defined by the grammar:
 *
 * <plus> --> + <minus> --> - <times> --> * <div> --> / <LParen> --> "("
 * <RParen> --> ")" <int> --> { <digit> }+ <id> --><letter> { <letter> | <digit>
 * } <float> --> { <digit> }+ "." { <digit> }+ <floatE> --><float> (E|e) [+|-] {
 * <digit> }+
 *
 * This class implements a DFA that will accept the above tokens. The DFA has 10
 * final states represented by enum-type literals:
 *
 * state token accepted
 *
 * Id identifiers Int integers Float floats without exponentiation part FloatE
 * floats with exponentiation part Plus + Minus - Times * Div / LParen ( RParen
 * )
 *
 * The DFA also uses 4 non-final states:
 *
 * state string recognized
 *
 * Start the empty string Period float parts ending with "." E float parts
 * ending with E or e EPlusMinus float parts ending with + or - in
 * exponentiation part
 *
 * The states are represented by an Enum type called "State". The function
 * "driver" is the driver to operate the DFA. The function "nextState" returns
 * the next state given the current state and the input character.
 *
 * To modify this lexical analyzer to recognize a different token set, the
 * functions "nextState", "isFinal" and the enum type "State" need to be
 * modified; the function "driver" and the other utility functions remain the
 * same.
 *
 *
 */
import java.io.*;

public abstract class lexArith {

    public enum State {
        // non-final states     ordinal number

        Start, // 0
        Period, // 1
        E, // 2
        EPlusMinus, // 3

        // operator states
        Op_Or,	//4
        Op_And,	//5
        // keyword states
        
        Id_I, // 6
        Id_IN, // 7
        Id_F, // 8
        Id_FL, // 9
        Id_FLO, // 10
        Id_FLOA, // 11
        Id_B, // 12
        Id_BO, // 13
        Id_BOO, // 14
        Id_BOOL, // 15
        Id_BOOLE, // 16
        Id_BOOLEA, // 17
        Id_E, // 18
        Id_EL, // 19
        Id_ELS, // 20
        Id_W, // 21
        Id_WH, // 22
        Id_WHI, // 23
        Id_WHIL, // 24
        Id_D, // 25
        Id_FA, // 26
        Id_FAL, // 27
        Id_FALS, // 28
        Id_T, // 29
        Id_TR, // 30
        Id_TRU, // 31        
        
        // final states

        Id, // 32
        Int, // 33
        Float, // 34
        FloatE, // 35
        Plus, // 36
        Minus, // 37
        Times, // 38
        Div, // 39
        LParen, // 40
        RParen, // 41
        Inv,	//42
        lt,	//43
        gt,	//44
        LBrace,	//45
        RBrace,	//46
        semicolon,	//47
        comma,	//48
        assign,	//49
        incr,	//50
        decr,	//51
        or,	//52
        and,	//53
        le,	//54
        ge,	//55
        eq,	//56
        neq,	//57
        	
        
        INT, // 58
        FLOAT, // 59
        BOOLEAN, // 60
        IF, // 61
        ELSE, // 62
        WHILE, // 63
        DO, // 64
        FALSE, // 65
        TRUE, // 66
                                
        UNDEF	//67
                
    }
    // By enumerating the non-final states first and then the final states,
    // test for a final state can be done by testing if the state's ordinal number
    // is greater than or equal to that of Id.
    public static String t; // holds an extracted token
    public static State state; // the current state of the FA
    private static int a; // the current input character
    private static char c; // used to convert the variable "a" to 
    // the char type whenever necessary
    private static BufferedReader inStream;
    private static PrintWriter outStream;

    private static int getNextChar() // Returns the next character on the input stream.
    {
        try {
            return inStream.read();
        } catch (IOException e) {
            return -1;
        }
    } //end getNextChar

    private static int getChar() // Returns the next non-whitespace character on the input stream.
    // Returns -1, end-of-stream, if the end of the input stream is reached.
    {
        int i = getNextChar();
        while (Character.isWhitespace((char) i)) {
            i = getNextChar();
        }
        return i;
    } // end getChar

    private static int driver() // This is the driver of the FA. 
    // If a valid token is found, assigns it to "t" and returns 1.
    // If an invalid token is found, assigns it to "t" and returns 0.
    // If end-of-stream is reached without finding any non-whitespace character, returns -1.
    {
        State nextState; // the next state of the FA

        t = "";
        state = State.Start;

        if (Character.isWhitespace((char) a)) {
            a = getChar(); // get the next non-whitespace character
        }
        if (a == -1) // end-of-stream is reached
        {
            return -1;
        }

        while (a != -1) // while "a" is not end-of-stream
        {
            c = (char) a;
            nextState = nextState(state, c);
            if (nextState == State.UNDEF) // The FA will halt.
            {
                if (isFinal(state)) {
                    
                        return 1; // valid token extracted
                    
                } else // "c" is an unexpected character
                {
                    t = t + c;
                    a = getNextChar();
                    return 0; // invalid token found
                }
            } else // The FA will go on.
            {
                state = nextState;
                t = t + c;
                a = getNextChar();
            }
        }

        // end-of-stream is reached while a token is being extracted

        if (isFinal(state)) {
            return 1; // valid token extracted
        } else {
            return 0; // invalid token found
        }
    } // end driver

    private static State nextState(State s, char c) // Returns the next state of the FA given the current state and input char;
    // if the next state is undefined, UNDEF is returned.
    {
        switch (state) {
            case Start:
                if (Character.isLetter(c)) {
                    if(c=='i') {
                        return State.Id_I;
                    }
                    else if(c=='f') {
                        return State.Id_F;
                    }
                    else if(c=='b') {
                        return State.Id_B;
                    }
                    else if(c=='e') {
                        return State.Id_E;
                    }
                    else if(c=='w') {
                        return State.Id_W;
                    }
                    else if(c=='d') {
                        return State.Id_D;
                    }
                    else if(c=='t') {
                        return State.Id_T;
                    }
                    else {
                        return State.Id;
                    }

                } else if (Character.isDigit(c)) {
                    return State.Int;
                } else if (c == '+') {
                    return State.Plus;
                } else if (c == '-') {
                    return State.Minus;
                } else if (c == '*') {
                    return State.Times;
                } else if (c == '/') {
                    return State.Div;
                } else if (c == '(') {
                    return State.LParen;
                } else if (c == ')') {
                    return State.RParen;
                } else if (c == ',') {
                    return State.comma;
                } else if (c == ';') {
                    return State.semicolon;
                } else if (c == '!') {
                    return State.Inv ;
                } else if (c == '{') {
                    return State.LBrace;
                } else if (c == '}') {
                    return State.RBrace;
                } else if (c == '=') {
                    return State.assign;
                } else if (c == '>') {
                    return State.gt;
                } else if (c == '<') {
                    return State.lt;
                } else if (c == '|') {
                    return State.Op_Or;
                } else if (c == '&') {
                    return State.Op_And;
                } else {
                    return State.UNDEF;
                }
                
/*
 * Finding KeyWords Starts
 */                
            case Id_I:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='f') {
                        return State.IF;
                    }
                    else if(c=='n') {
                        return State.Id_IN;
                    }
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case IF:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
            case Id_IN:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='t') {
                        return State.INT;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case INT:
                if (Character.isLetterOrDigit(c)) {                    
                    return State.Id;                    
                } else {
                    return State.UNDEF;
                }
                
            case Id_F:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='l') {
                        return State.Id_FL;
                    }
                    else if(c=='a') {
                        return State.Id_FA;
                    }
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_FL:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='o') {
                        return State.Id_FLO;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_FLO:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='a') {
                        return State.Id_FLOA;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_FLOA:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='t') {
                        return State.FLOAT;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case FLOAT:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
            case Id_FA:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='l') {
                        return State.Id_FAL;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_FAL:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='s') {
                        return State.Id_FALS;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_FALS:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='e') {
                        return State.FALSE;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case FALSE:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
            case Id_B:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='o') {
                        return State.Id_BO;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_BO:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='o') {
                        return State.Id_BOO;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_BOO:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='l') {
                        return State.Id_BOOL;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_BOOL:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='e') {
                        return State.Id_BOOLE;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_BOOLE:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='a') {
                        return State.Id_BOOLEA;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_BOOLEA:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='n') {
                        return State.BOOLEAN;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case BOOLEAN:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
            case Id_E:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='l') {
                        return State.Id_EL;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_EL:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='s') {
                        return State.Id_ELS;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_ELS:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='e') {
                        return State.ELSE;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
             case ELSE:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
             case Id_W:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='h') {
                        return State.Id_WH;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_WH:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='i') {
                        return State.Id_WHI;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_WHI:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='l') {
                        return State.Id_WHIL;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_WHIL:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='e') {
                        return State.WHILE;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case WHILE:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
            case Id_D:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='o') {
                        return State.DO;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case DO:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
            case Id_T:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='r') {
                        return State.Id_TR;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_TR:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='u') {
                        return State.Id_TRU;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case Id_TRU:
                if (Character.isLetterOrDigit(c)) {
                    if(c=='e') {
                        return State.TRUE;
                    }                    
                    else {
                        return State.Id;
                    }
                } else {
                    return State.UNDEF;
                }
            case TRUE:
                if (Character.isLetterOrDigit(c)) {                    
                  return State.Id;
                    
                } else {
                    return State.UNDEF;
                }
/*
 * Finding KeyWords Ends
 */
            case Id:
                if (Character.isLetterOrDigit(c)) {
                    return State.Id;
                } else {
                    return State.UNDEF;
                }
            case Int:
                if (Character.isDigit(c)) {
                    return State.Int;
                } else if (c == '.') {
                    return State.Period;
                } else {
                    return State.UNDEF;
                }
            case Period:
                if (Character.isDigit(c)) {
                    return State.Float;
                } else {
                    return State.UNDEF;
                }
            case Float:
                if (Character.isDigit(c)) {
                    return State.Float;
                } else if (c == 'e' || c == 'E') {
                    return State.E;
                } else {
                    return State.UNDEF;
                }
            case E:
                if (Character.isDigit(c)) {
                    return State.FloatE;
                } else if (c == '+' || c == '-') {
                    return State.EPlusMinus;
                } else {
                    return State.UNDEF;
                }
            case EPlusMinus:
                if (Character.isDigit(c)) {
                    return State.FloatE;
                } else {
                    return State.UNDEF;
                }
            case FloatE:
                if (Character.isDigit(c)) {
                    return State.FloatE;
                } else {
                    return State.UNDEF;
                }
            case Plus:
                if (c=='+') {
                    return State.incr;
                } else {
                    return State.UNDEF;
                }
            case Minus:
                if (c=='-') {
                    return State.decr;
                } else {
                    return State.UNDEF;
                }
            case assign:
                if (c=='=') {
                    return State.eq;
                } else {
                    return State.UNDEF;
                }
            case lt:
                if (c=='=') {
                    return State.le;
                } else {
                    return State.UNDEF;
                }
            case gt:
                if (c=='=') {
                    return State.ge;
                } else {
                    return State.UNDEF;
                }
            case Inv:
                if (c=='=') {
                    return State.neq;
                } else {
                    return State.UNDEF;
                }
            case Op_Or:
                if (c=='|') {
                    return State.or;
                } else {
                    return State.UNDEF;
                }
            case Op_And:
                if (c=='&') {
                    return State.and;
                } else {
                    return State.UNDEF;
                }
            default:
                return State.UNDEF;
        }
    } // end nextState

    private static boolean isFinal(State state) {
        return (state.compareTo(State.Id) >= 0);
    }

    public static void getToken() // Extract the next token using the driver of the FA.
    // If an invalid token is found, issue an error message.
    {
        int i = driver();
        if (i == 0) {
            displayln(t + "  -- Invalid Token");
        }
    } // end getToken

    public static void display(String s) {
        outStream.print(s);
    }

    public static void displayln(String s) {
        outStream.println(s);
    }

    public static void setLex(String inFile, String outFile) // Sets the input and output streams to "inFile" and "outFile", respectively.
    // Also sets the current input character "a" to the first character on
    // the input stream.
    {
        try {
            inStream = new BufferedReader(new FileReader(inFile));
            outStream = new PrintWriter(new FileOutputStream(outFile));
            a = inStream.read();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    } // end setIO

    public static void closeIO() {
        try {
            inStream.close();
            outStream.close();
        } catch (IOException e) {
        }
    } // end closeIO

    public static void main(String argv[]) // The input/output file names must be passed as argv[0] and argv[1].
    {
        int i;

        setLex(argv[0], argv[1]);

        while (a != -1) // while "a" is not end-of-stream
        {
            i = driver(); // extract the next token
            if (i == 1) {
                displayln(t + "   : " + state.toString());
            } else if (i == 0) {
                displayln(t + "  -- Invalid Token");
            }
        }

        closeIO();
    } // end main
}
