
public class Character {
	protected String Name;
	protected int Life;
	protected int Magic;
	
	public Character()
	{
		this.Name = "NULL";
		this.Life = 0;
		this.Magic = 0;
	}
	public Character( String s, int l, int m )
	{
		this.Name = s;
		this.Life = l;
		this.Magic = m;
	}
	public void setLife( int L )
	{
		this.Life = L;
	}
	public int getLife()
	{
		return Life;
	}
	public void setMagic( int M )
	{
		this.Magic = M;
	}
	public int getMagic()
	{
		return Magic;
	}
	public boolean dead()
	{
		if( this.Life <= 0 )
		{
			System.out.println( this.Name + " is dead.");
			return true;
		}
		else
			return false;
	}
	public void detail()
	{
		System.out.println( this.Name + "(life/magic):" + this.Life + "/" + this.Magic);
	}
}
class Warrior extends Character {
	public Warrior( String s )
	{
		super(s, 400, 100);
	}
	public void NewMoon( Master m )
	{
		if( this.Magic >= 10 )
		{
			m.Life = m.Life - 40;
			this.Magic = this.Magic - 10;
		}
		else
			System.out.println("Attack is invalid.");
	}
	public void NewMoon( Warrior w )
	{
		if( this.Magic >= 10 )
		{
			w.Life = w.Life - 25;
			this.Magic = this.Magic - 10;
		}
		else
			System.out.println("Attack is invalid.");
	}
}
class Master extends Character {
	public Master( String s )
	{
		super(s, 280, 200);
	}
	public void SmallFire( Warrior w )
	{
		if( this.Magic >= 25 )
		{
			this.Magic = this.Magic - 25;
			w.Life = w.Life - 40;
		}
		else
			System.out.println("Attack is invalid.");			
	}
	public void SmallFire( Master m )
	{
		if( this.Magic >= 25 )
		{
			this.Magic = this.Magic - 25;
			m.Life = m.Life - 60;
		}
		else
			System.out.println("Attack is invalid.");
	}
}