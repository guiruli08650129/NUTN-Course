import java.io.Serializable;
import java.util.*;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String name;
	String email;
	String id;
	String phone;
	
	public Client(String name, String id, String email, String phone)//�s�W�ϥΪ�
	{	
		//super(book, price, author, publisher);
		this.name = name;
		this.email = email;
		this.id = id;
		this.phone = phone;
	    System.out.println("Add a new user into the library!!");		
	}
	public boolean get_name( String name )//���o�ϥΪ̡A��ܨϥΪ̸�Ʀs�b
	{
		if( Objects.equals(this.name, name))
			return true;
		else
			return false;
	}
	
	public void edit(String name, String id, String email, String phone)//�ק�ϥΪ̸��
	{
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public void dump()//�L�X�Ҧ��ϥΪ�
   {	
	   System.out.println(this.name + "		" + this.id + "		" + this.email + "		" + this.phone);
   }}
