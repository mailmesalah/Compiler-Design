package parser;
class ValSub extends Val
{
	int val;

	ValSub(int i)
	{
		val = i;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new ValSub(val);
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
