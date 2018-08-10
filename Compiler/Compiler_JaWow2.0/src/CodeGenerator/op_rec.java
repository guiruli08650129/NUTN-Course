package CodeGenerator;

public class op_rec{
	
	//PLUSOP, MINUS;
	String operator = "";
	
	public op_rec()
	{
		this.operator = "";
	}
	
	public void set(String s)
	{
		this.operator = s;
	}
	
	public String get()
	{
		return this.operator;
	}
	
}
