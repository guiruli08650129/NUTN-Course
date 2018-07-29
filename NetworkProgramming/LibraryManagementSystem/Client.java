import java.io.Serializable;
import java.util.*;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String name;
	String email;
	String id;
	String phone;
	
	public Client(String name, String id, String email, String phone)//新增使用者
	{	
		//super(book, price, author, publisher);
		this.name = name;
		this.email = email;
		this.id = id;
		this.phone = phone;
	    System.out.println("Add a new user into the library!!");		
	}
	public boolean get_name( String name )//取得使用者，表示使用者資料存在
	{
		if( Objects.equals(this.name, name))
			return true;
		else
			return false;
	}
	
	public void edit(String name, String id, String email, String phone)//修改使用者資料
	{
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public void dump()//印出所有使用者
   {	
	   System.out.println(this.name + "		" + this.id + "		" + this.email + "		" + this.phone);
   }}
