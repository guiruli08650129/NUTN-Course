import java.util.*;
import java.io.Serializable;

public class Book  implements Serializable{

	private static final long serialVersionUID = 1L;
	String    	bookname;
	   float       	price;
	   String		author;
	   String 		publisher;
	   boolean 		checked;//false�N��b�Ϯ��]��
	   String       user;
	   String		reserve;
	   
	   public   Book(String book, float price, String author, String publisher)//�s�W�@����
	   {
	         this.bookname= book;
	         this.price=price;
	         this.author = author;
	         this.publisher = publisher;
	         this.checked= false; //false��ܦb�Ϯ��]��
	         this.user=null;
	         this.reserve = null;
	         System.out.println("Add a new book into the library!!");
	   }
	   
	   public boolean get_bookname( String bookname)//���o�ѦW�A��ܦ��Ѧs�b��Ϯ��]�t�θ�
	   {
		   if(Objects.equals(this.bookname, bookname))
			   return true;
		   else
			   return false;		   
	   }

	   public boolean get_user( String borrower)
	   {
		   if(Objects.equals(this.user, borrower))
			   return true;
		   else
			   return false;
	   }
	   
	   public boolean get_reserve(String reserver)
	   {
		   if(Objects.equals(this.reserve, reserver))
			   return true;
		   else
			   return false;
	   }
	   
	   public void inquire_book()//�d�߬O�_�Q�ɥX
	   {
		   if(this.checked != true)
		   {
			   System.out.println("This book is in the library!!");
		   }
		   else
		   {
			   System.out.println("This book is borrowed by " + this.user);
		   }
	   }
	   
	   public  void lend(String  borrower) //�ɾ\���y
	   {
		   if(this.checked == false)
		   {
			   if(this.reserve == null)
			   {
				 this.checked = true;
			     this.user = borrower; 
			     System.out.println("Borrow succeed!!");				   
			   }
			   else
			   {
				   System.out.println("Someone have reserve this book, so you can't borrow it!!");
			   }
		   }
		   else
		   {
			   System.out.println("This book isnt in the library, your borrow fail!!");
		   }
		   		   
	    }
	   
	   public  void  return_book(String borrower, String bookname)//�k�ٮ��y
	   {
		   if (get_bookname(bookname))
		   {
			   if(get_user(borrower))
			   {
				   if (this.checked == true)
				   {
					   this.checked = false;
					   this.user = null;	
					   System.out.println("Restoration succeed!!");					   
				   }
				   else{
					   System.out.println("This book is in the library!!");}
			   }
			   else{
				   System.out.println("This user doesn't borrow any book!!");}
		   }
		   else{
			   System.out.println("This book is naver in the library!!");}   
		} 
	   
	   public void edit(String bookname, String author, String publisher, float price)//�ק���y���
	   {
			   this.bookname = bookname;
			   this.author = author;
			   this.publisher = publisher;
			   this.price = price;
	   }

	   public void reserve_book( String reserver )//�w�����y
	   {
		   if(this.checked == false)
		   {
			   if(this.reserve == null)
			   {
				   System.out.println("This book is already in library, you can borrow it without reserve!!");
			   }
			   else
			   {
				   System.out.println("This book has reserved, you can't borrow or reserve it!!");
			   }
		   }
		   else
		   {
			   if(this.reserve == null)
			   {
				   this.reserve = reserver;
				   System.out.println("Reservation succeed!!");				   
			   }
			   else
			   {
				   System.out.println("This book has reserved, you can't borrow or reserve it!!");
			   }
				   
		   }
	   }
	   
	   public void cancel_reserve(String reserver)//�����w��
	   {
		   if(Objects.equals(this.reserve, reserver))
		   {
			   this.reserve = null;
			   System.out.println("Cancel succeed!!");
		   }
		   else
		   {
			   if(this.reserve == null)
				   System.out.println("You didn't reserve this book!! Cancel failed!!");
			   else
				   System.out.println("This book is already be reserved!!");
		   }			   
		   
	   }
	   
	   public boolean inquire_reserve()//�d�߹w��
	   {
		   if(this.reserve == null)
			   return false;//�S�H�w��
		   else
			   return true;//���H�w��
	   }
	   
	   public void dump()//�L�X�Ҧ��w�s���y
	   {
		   System.out.println(this.bookname + "		" + this.author + "	" + this.publisher + "		" + this.price + "	" + this.checked + "	" + this.user + "	" + this.reserve);
	   }
	   

} 
