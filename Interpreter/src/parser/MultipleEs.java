package parser;
import java.util.*;

class MultipleEs extends BoolPrimary
{
	E e1;
	RelationOperator ro;
	E e2;

	MultipleEs(E p1, RelationOperator r,E p2)
	{
		e1=p1;
		ro=r;
		e2=p2;
	}
 
	void printParseTree(String indent)
	{		
		String indent1 = indent;

		lexArithArray.displayln(indent1 + indent1.length() + " <boolPrimary>");
		indent1 = indent1+" ";
		e1.printParseTree(indent1);
		ro.printParseTree(indent1);		
		e2.printParseTree(indent1);
	}

	void emitInstructions()
	{
		e1.emitInstructions();
		e2.emitInstructions();
	}

	@Override
	Val TypeEval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val vE1 = e1.TypeEval(state);
		Val vE2 = e2.TypeEval(state);
		
		if((vE1.getClass()==TypeInt.class || vE1.getClass()==TypeFloat.class) && (vE2.getClass()==TypeInt.class || vE2.getClass()==TypeFloat.class)){
			return new TypeBoolean();
		}else if((ro.getClass()==EqualTo.class) && (vE1.getClass()==TypeBoolean.class) && (vE2.getClass()==TypeBoolean.class)){
			return new TypeBoolean();
		}else if(vE1.getClass()==TypeError.class){
			return vE1;
		}else if(vE2.getClass()==TypeError.class){
			return vE2;		
		}else if((ro.getClass()==GreaterThan.class)&&(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeBoolean.class)){
			if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeInt.class){
				return new TypeError("Invalid Type for > Operator, boolean and int type Found");
			}else if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeFloat.class){
				return new TypeError("Invalid Type for > Operator, boolean and float type Found");
			}else if(vE1.getClass()==TypeInt.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for > Operator, int and boolean type Found");
			}else if(vE1.getClass()==TypeFloat.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for > Operator, float and boolean type Found");
			}else{
				return new TypeError("Invalid Type for > Operator");
			}
		}else if((ro.getClass()==GreaterThanOrEqualTo.class)&&(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeBoolean.class)){
			if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeInt.class){
				return new TypeError("Invalid Type for >= Operator, boolean and int type Found");
			}else if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeFloat.class){
				return new TypeError("Invalid Type for >= Operator, boolean and float type Found");
			}else if(vE1.getClass()==TypeInt.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for >= Operator, int and boolean type Found");
			}else if(vE1.getClass()==TypeFloat.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for >= Operator, float and boolean type Found");
			}else{
				return new TypeError("Invalid Type for >= Operator");
			}
		}else if((ro.getClass()==LessThan.class)&&(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeBoolean.class)){
			if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeInt.class){
				return new TypeError("Invalid Type for < Operator, boolean and int type Found");
			}else if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeFloat.class){
				return new TypeError("Invalid Type for < Operator, boolean and float type Found");
			}else if(vE1.getClass()==TypeInt.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for < Operator, int and boolean type Found");
			}else if(vE1.getClass()==TypeFloat.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for < Operator, float and boolean type Found");
			}else{
				return new TypeError("Invalid Type for < Operator");
			}
		}else if((ro.getClass()==LessThanOrEqualTo.class)&&(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeBoolean.class)){
			if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeInt.class){
				return new TypeError("Invalid Type for <= Operator, boolean and int type Found");
			}else if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeFloat.class){
				return new TypeError("Invalid Type for <= Operator, boolean and float type Found");
			}else if(vE1.getClass()==TypeInt.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for <= Operator, int and boolean type Found");
			}else if(vE1.getClass()==TypeFloat.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for <= Operator, float and boolean type Found");
			}else{
				return new TypeError("Invalid Type for <= Operator");
			}
		}else if((ro.getClass()==NotEqualTo.class)&&(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeBoolean.class)){
			if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeInt.class){
				return new TypeError("Invalid Type for != Operator, boolean and int type Found");
			}else if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeFloat.class){
				return new TypeError("Invalid Type for != Operator, boolean and float type Found");
			}else if(vE1.getClass()==TypeInt.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for != Operator, int and boolean type Found");
			}else if(vE1.getClass()==TypeFloat.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for != Operator, float and boolean type Found");
			}else{
				return new TypeError("Invalid Type for != Operator");
			}
		}else if((ro.getClass()==EqualTo.class)&&((vE1.getClass()==TypeBoolean.class || vE2.getClass()!=TypeBoolean.class)||(vE1.getClass()!=TypeBoolean.class || vE2.getClass()==TypeBoolean.class))){
			if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeInt.class){
				return new TypeError("Invalid Type for == Operator, boolean and int type Found");
			}else if(vE1.getClass()==TypeBoolean.class || vE2.getClass()==TypeFloat.class){
				return new TypeError("Invalid Type for == Operator, boolean and float type Found");
			}else if(vE1.getClass()==TypeInt.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for == Operator, int and boolean type Found");
			}else if(vE1.getClass()==TypeFloat.class || vE2.getClass()==TypeBoolean.class){
				return new TypeError("Invalid Type for == Operator, float and boolean type Found");
			}	else{
				return new TypeError("Invalid Type for == Operator");
			}
		}else{
			return new TypeError("Invalid Type for Relational Operator");
		}
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		Val vE1 = e1.Eval(state);
		Val vE2 = e2.Eval(state);
		
		if(vE1==null || vE2==null){
			return null;
		}
		
		if((vE1.getClass()==TypeInt.class || vE1.getClass()==TypeFloat.class) && (vE2.getClass()==TypeInt.class || vE2.getClass()==TypeFloat.class)){
			if((ro.getClass()==EqualTo.class)){
				if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeInt)vE1).val==((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeInt)vE1).val==((TypeFloat)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeFloat)vE1).val==((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeFloat)vE1).val==((TypeFloat)vE2).val);
				}				
			}else if((ro.getClass()==GreaterThan.class)){
				if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeInt)vE1).val > ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeInt)vE1).val > ((TypeFloat)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeFloat)vE1).val > ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeFloat)vE1).val > ((TypeFloat)vE2).val);
				}				
			}else if((ro.getClass()==LessThan.class)){
				if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeInt)vE1).val < ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeInt)vE1).val < ((TypeFloat)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeFloat)vE1).val < ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeFloat)vE1).val < ((TypeFloat)vE2).val);
				}				
			}else if((ro.getClass()==GreaterThanOrEqualTo.class)){
				if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeInt)vE1).val >= ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeInt)vE1).val >= ((TypeFloat)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeFloat)vE1).val >= ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeFloat)vE1).val >= ((TypeFloat)vE2).val);
				}				
			}else if((ro.getClass()==LessThanOrEqualTo.class)){
				if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeInt)vE1).val <= ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeInt)vE1).val <= ((TypeFloat)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeFloat)vE1).val <= ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeFloat)vE1).val <= ((TypeFloat)vE2).val);
				}				
			}else if((ro.getClass()==NotEqualTo.class)){
				if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeInt)vE1).val != ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeInt.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeInt)vE1).val != ((TypeFloat)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeInt.class){
					return new TypeBoolean(((TypeFloat)vE1).val != ((TypeInt)vE2).val);
				}else if(vE1.getClass()==TypeFloat.class && vE2.getClass()==TypeFloat.class){
					return new TypeBoolean(((TypeFloat)vE1).val != ((TypeFloat)vE2).val);
				}				
			}
			
		}else if((ro.getClass()==EqualTo.class) && (vE1.getClass()==TypeBoolean.class) && (vE2.getClass()==TypeBoolean.class)){
			return new TypeBoolean(((TypeBoolean)vE1).val == ((TypeBoolean)vE2).val);
		}

		return null;
	}
}
