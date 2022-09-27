package parser;
class IntVal extends Val
{
	int val;

	IntVal(int i)
	{
		val = i;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new IntVal(val);
	}

	float floatVal()
	{
		return (float)val;
	}
	
	boolean isZero()
	{
		return val == 0;
	}

	@Override
	String getErrorString() {
		// TODO Auto-generated method stub
		return null;
	}
}
