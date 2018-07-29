import java.util.*;
import java.io.Serializable;

public class Book  implements Serializable{

	private static final long serialVersionUID = 1L;
	String    	bookname;
	   float       	price;
	   String		author;
	   String 		publisher;
	   boolean 		checked;//false代表在圖書館裡
	   String       user;
	   String		reserve;
	   
	   public   Book(String book, float price, String author, String publisher)//新增一本書
	   {
	         this.bookname= book;
	         this.price=price;
	         this.author = author;
	         this.publisher = publisher;
	         this.checked= false; //false表示在圖書館裡
	         this.user=null;
	         this.reserve = null;
	         System.out.println("Add a new book into the library!!");
	   }
	   
	   public boolean get_bookname( String bookname)//取得書名，表示此書存在於圖書館系統裡
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
	   
	   public void inquire_book()//查詢是否被借出
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
	   
	   public  void lend(String  borrower) //借閱書籍
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
	   
	   public  void  return_book(String borrower, String bookname)//歸還書籍
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
	   
	   public void edit(String bookname, String author, String publisher, float price)//修改書籍資料
	   {
			   this.bookname = bookname;
			   this.author = author;
			   this.publisher = publisher;
			   this.price = price;
	   }

	   public void reserve_book( String reserver )//預約書籍
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
	   
	   public void cancel_reserve(String reserver)//取消預約
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
	   
	   public boolean inquire_reserve()//查詢預約
	   {
		   if(this.reserve == null)
			   return false;//沒人預約
		   else
			   return true;//有人預約
	   }
	   
	   public void dump()//印出所有庫存書籍
	   {
		   System.out.println(this.bookname + "		" + this.author + "	" + this.publisher + "		" + this.price + "	" + this.checked + "	" + this.user + "	" + this.reserve);
	   }
	   

} 
