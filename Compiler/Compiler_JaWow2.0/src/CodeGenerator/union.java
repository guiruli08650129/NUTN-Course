package CodeGenerator;

public class union{

	String name; /* for IDEXPR, TEMPEXPR */
	int val; /* for LITERALEXPR */
	
	public union()
	{
		this.name = "";
		this.val = 0;
	}
	
	public void setVal(String s, int i)
	{
		this.name = s;
		this.val = i;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getVal()
	{
		return this.val;
	}
}
