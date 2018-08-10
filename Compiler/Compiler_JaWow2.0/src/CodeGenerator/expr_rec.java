package CodeGenerator;

public class expr_rec {
	//IDEXPR, LITERALEXPR, TEMPEXPR ;
	String kind = "";
	String name = ""; /* for IDEXPR, TEMPEXPR */
	int val; /* for LITERALEXPR */	
	
	public expr_rec()
	{
		
	}
	
	public expr_rec(String s, String s2, int i)
	{
		this.kind = s;
		this.name = s2;
		this.val = i;
	}
	
	public void setKind(String s)
	{
		this.kind = s;
	}

	public String getKind()
	{
		return this.kind;
	}

	
	public void setVal(String s, int i)
	{
		this.name = s;
		this.val = i;
	}
	
	public void setName(String s)
	{
		this.name = s;
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
