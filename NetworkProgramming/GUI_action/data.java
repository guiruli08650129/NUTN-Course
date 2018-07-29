
public class data {
	
	public String name;
	public String id;
	public String sport;
	public String food;
	
	data(String s)
	{
		String[] temp = s.split("\t");
		this.name = temp[0];
		this.id = temp[1];
		this.sport = temp[2];
		this.food = temp[3];
	}
	
	public void setName(String n)
	{
		this.name = n;
	}
	
	public void setID(String id)
	{
		this.id = id;
	}
	
	public void setSport(String s)
	{
		this.sport = s;
	}
	
	public void setFood(String f)
	{
		this.food = f;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getID()
	{
		return this.id;
	}
	
	public String getSport()
	{
		return this.sport;
	}
	
	public String getFood()
	{
		return this.food;
	}
	
	public String dump()
	{
		return this.name + "\t" + this.id + "\t" + this.sport + "\t" + this.food;
	}
	
}
