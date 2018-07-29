import java.util.*;
import java.io.*;

public class LMS{

	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) throws IOException {
		
		Scanner s = new Scanner(System.in);
		LinkedList<Client> User = new LinkedList<Client>();
		LinkedList<Book>  Lib = new LinkedList<Book>(); 
		
	   try {
		    FileInputStream ufin = new FileInputStream("User.dat");
		    ObjectInputStream uois = new ObjectInputStream(ufin);
		    User = (LinkedList<Client>) uois.readObject();	    
		    uois.close();
		    }
		   catch (Exception e) { e.printStackTrace(); }
	   
	   try {
		    FileInputStream bfin = new FileInputStream("Lib.dat");
		    ObjectInputStream bois = new ObjectInputStream(bfin);
		    Lib = (LinkedList<Book>) bois.readObject();
		    bois.close();
		    }
		   catch (Exception e) { e.printStackTrace(); }	   


		System.out.println("Welcome to library!!!!");	
		System.out.println("Please enter your choose:\na->Book Management System "
														+ "\nb->User Management System \nothers->exit");		
		String system_choose = s.next();
		boolean handle = true;
		
		do{
			switch(system_choose)
			{
				case "a":
				{
					System.out.println("\n_______________________________________");
					System.out.println("Welcome to Book Management System!!");
					System.out.println("Please enter your choose :\n1->borrow" + "		" + "5->return\n2->buy" + "			" 
							+ "6->remove\n3->dump" + "			" + "7->inquire book\n4->edit" +"			"
							+"8->reserve book\n9->cancel reservation" + "	" + "others->exit");
					int choose = s.nextInt();
					boolean ans = true;
					
					do{
						switch(choose)
						{
							case 1:
							{
								System.out.println("Please enter your name:");
								String user = s.next();
								
								System.out.println("Please enter book:");
						        Scanner scan=new Scanner(System.in);
							    String target = scan.next(); 
						
							    for(int i = 0 ; i < Lib.size() ; i++)
							    {
							    	if(Lib.get(i).bookname.equalsIgnoreCase(target))
							    		Lib.get(i).lend(user);
							    }	
							    break;
							}

							case 2:
							{
								boolean is_buy = false;
								System.out.println("Please enter book name:");
								Scanner b = new Scanner(System.in);
								String newbook = b.nextLine();
								for(int i = 0 ; i < Lib.size() ; i++)
							    {
							    	if(Lib.get(i).bookname.equalsIgnoreCase(newbook))
							    	{
							    		is_buy = true;
							    		break;
							    	}
							    }								
							    if(!is_buy)
							    {
									System.out.println("Please enter price:");
									Scanner p = new Scanner(System.in);
									float newprice = p.nextFloat();
									System.out.println("Please enter author:");
									Scanner a = new Scanner(System.in);
									String author = a.next();
									System.out.println("Please enter publisher:");
									Scanner q = new Scanner(System.in);
									String publisher = q.next();		
									
							    	Book buybook = new Book(newbook, newprice, author, publisher);
									Lib.add(buybook);
							    }
							    else
							    	System.out.println("This book is already in library!!");																	
								break;
							}
							case 3:
							{
								System.out.println("\n-------------------------------------Library-------------------------------------");
								System.out.println("bookname	author	publisher	price	checked	user	reserved");
								System.out.println("---------------------------------------------------------------------------------");
								for(int i = 0 ; i < Lib.size() ; i++)
							       	Lib.get(i).dump();							   
								break;
							}
							case 4:
							{
								System.out.println("Please enter book name:");
								Scanner b = new Scanner(System.in);
								String editbook = b.nextLine();
								boolean control = false;
								for(int i = 0 ; i < Lib.size() ; i++)
								{
									if(Lib.get(i).get_bookname(editbook))
									{
										control = true;
										System.out.println("Please enter price:");
										Scanner p = new Scanner(System.in);
										float editprice = p.nextFloat();
										System.out.println("Please enter author:");
										Scanner a = new Scanner(System.in);
										String editauthor = a.next();
										System.out.println("Please enter publisher:");
										Scanner q = new Scanner(System.in);
										String editpublisher = q.next();	
										Lib.get(i).edit(editbook, editauthor, editpublisher, editprice);
										break;
									}
								}	
								if(!control)
									System.out.println("This book is not in the library!!");								
								break;
							}
							case 5:
							{
								System.out.println("Please enter your name:");
								Scanner ss = new Scanner(System.in);
								String returnuser = ss.next();
								System.out.println("Please enter book name:");
								Scanner bb = new Scanner(System.in);
								String r_book = bb.next();
								boolean control = false;
							    for(int i = 0 ; i < Lib.size() ; i++)
							    {
							    	if(Lib.get(i).get_bookname(r_book) && Lib.get(i).get_user(returnuser))
							    	{
							    		control = true;
							    		Lib.get(i).return_book(returnuser, r_book);
							    	}							    					    		
							    }
							    if(!control)
							    	System.out.println(returnuser + " doesn't borrow the book " + r_book);
							    break;
							}
							case 6:
							{
								System.out.println("Enter book name which you want to remove¡G");
								BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
								String remove_book = buf.readLine();
								boolean control = false;
								for(int i = 0 ; i < Lib.size() ; i++)
								{
									if(Lib.get(i).get_bookname(remove_book))
									{
										control = true;
										Lib.remove(i);
										System.out.println("Removing succeed!!");
										break;
									}
								}
								if(!control)
									System.out.println("This book has not yet been bought!!\nDelete failed!!");
								break;
							}
							case 7:
							{
								System.out.println("Please enter book name:");
								BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
								String ib = reader.readLine();
								boolean is_bought = false;
								for(int i = 0 ; i < Lib.size() ; i++)
								{
									if(Lib.get(i).get_bookname(ib))
									{
										is_bought = true;
										Lib.get(i).inquire_book();
										break;
									}
								}
								if(!is_bought)
									System.out.println("This book has not yet been bought!!");								
								break;
							}
							case 8:
							{
								System.out.println("Please enter your name:");
								String user = s.next();
								
								System.out.println("Please enter book:");
						        Scanner scan=new Scanner(System.in);
							    String target = scan.next(); 
						
							    for(int i = 0 ; i < Lib.size() ; i++)
							    {
							    	if(Lib.get(i).bookname.equalsIgnoreCase(target))
							    		Lib.get(i).reserve_book(user);
							    }	
							    break;
							}
							case 9:
							{
								System.out.println("Please enter your name:");
								String user = s.next();
								
								System.out.println("Please enter book:");
						        Scanner scan=new Scanner(System.in);
							    String target = scan.next(); 
						
							    for(int i = 0 ; i < Lib.size() ; i++)
							    {
							    	if(Lib.get(i).bookname.equalsIgnoreCase(target))
							    		Lib.get(i).cancel_reserve(user);
							    }	
							    break;
							}
							default:
							{
								ans = false;
								System.out.println("Exit Book Management System!!");
								break;
							}	
						}
						if(ans)
						{
						    try {
						        FileOutputStream fout = new FileOutputStream("Lib.dat");
						        ObjectOutputStream oos = new ObjectOutputStream(fout);
						        oos.writeObject(Lib);
						        oos.close();
						        }
						     catch (Exception e) { e.printStackTrace(); }	
					
							System.out.println("\n\nPlease enter your choose :\n1->borrow" + "		" + "5->return\n2->buy" + "			" 
									+ "6->remove\n3->dump" + "			" + "7->inquire book\n4->edit" +"			"
									+"8->reserve book\n9->cancel reservation" + "	" + "others->exit");
							choose = s.nextInt();					
						}
						
					}while(ans);						
					break;
				}
				case "b":
				{
					System.out.println("\n_______________________________________");
					System.out.println("Welcome to User Management System!!");
					System.out.println("Please enter your choose :\n1->New" + "			" + "4->delete\n2->inquire user"
								+ "		" + "5->inquire reserve\n3->dump" + "			" + "6->edit\nothers->exit");
					int choose = s.nextInt();
					boolean ans = true;
					
					do{
						switch(choose)
						{
							case 1:
							{
								boolean is_user = false;
								System.out.println("Please enter user name:");
								Scanner u = new Scanner(System.in);
								String newuser = u.nextLine();
								for(int j = 0 ; j < Lib.size() ; j++)
							    {
							    	if(User.get(j).name.equalsIgnoreCase(newuser))
							    	{
							    		is_user = true;
							    		break;
							    	}
							    }
							    if(!is_user)
							    {
									System.out.println("Please enter id:");
									Scanner i = new Scanner(System.in);
									String newid = i.nextLine();
									System.out.println("Please enter email:");
									Scanner e = new Scanner(System.in);
									String email = e.nextLine();
									System.out.println("Please enter phone:");
									Scanner p = new Scanner(System.in);
									String phone = p.nextLine();						    	
							    	
							    	Client nuser = new Client(newuser, newid, email, phone);
									User.add(nuser);
							    }
							    else
							    	System.out.println("This user is already in system!!");	
								break;
							}
							case 2:
							{
								System.out.println("Please enter user:");
								BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
								String iu = reader.readLine();
								boolean is_register = false;
								for(int i = 0 ; i < User.size() ; i++)
								{
									if(User.get(i).get_name(iu))
									{
										is_register = true;
										System.out.println("-----------------------------Borrowing Book-----------------------------");
										System.out.println("bookname" + "	" + "author" + "	" + "publisher" + "	" + "price" + "	" + "checked" + "	" + "user" + "	" + "reserve");
										System.out.println("------------------------------------------------------------------------");
										for(int j = 0 ; j < Lib.size() ; j++)
										{
											if(Lib.get(j).get_user(iu))
											{
												Lib.get(j).dump();
											}
										}
										break;
									}
								}
								if(!is_register)
									System.out.println("This user has not registered!!");								
								break;
							}
							case 3:
							{
								System.out.println("\n--------------------------User--------------------------");
								System.out.println("user		ID		email		phone");
								System.out.println("--------------------------------------------------------");
								for(int i = 0 ; i < User.size() ; i++)
								{
									User.get(i).dump();
								}	
								break;
							}
							case 4:
							{
								System.out.println("Enter user name which you want to delete¡G");
								BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
								String remove_user = buf.readLine();
								boolean control = false;
								for(int i = 0 ; i < User.size() ; i++)
								{
									if(User.get(i).get_name(remove_user))
									{
										control = true;
										User.remove(i);
										System.out.println("Deletion succeed!!");
										break;
									}
								}
								if(!control)
									System.out.println("This user has naver registered!!\nDelete failed!!");
								break;
							}
							case 5:
							{
								System.out.println("Please enter user:");
								BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
								String ir = reader.readLine();
								boolean is_reserve = false;
								for(int i = 0 ; i < User.size() ; i++)
								{
									if(User.get(i).get_name(ir))
									{
										is_reserve = true;
										System.out.println("-----------------------------Reserving Book-----------------------------");
										System.out.println("bookname" + "	" + "author" + "	" + "publisher" + "	" + "price" + "	" + "checked" + "	" + "user" + "	" + "reserve");
										System.out.println("------------------------------------------------------------------------");
										for(int j = 0 ; j < Lib.size() ; j++)
										{
											if(Lib.get(j).get_reserve(ir))
											{
												Lib.get(j).dump();
											}
										}
										break;
									}
								}
								if(!is_reserve)
									System.out.println("This user has not registered!!");								
								break;								
							}							
							case 6:
							{
								System.out.println("Please enter user name:");
								Scanner us = new Scanner(System.in);
								String username = us.nextLine();
								boolean control = false;
								for(int j = 0 ; j < User.size() ; j++)
								{
									if(User.get(j).get_name(username))
									{
										control = true;
										System.out.println("Please enter id:");
										Scanner i = new Scanner(System.in);
										String newid = i.nextLine();
										System.out.println("Please enter email:");
										Scanner e = new Scanner(System.in);
										String email = e.nextLine();
										System.out.println("Please enter phone:");
										Scanner p = new Scanner(System.in);
										String phone = p.nextLine();	
										User.get(j).edit(username, newid, email, phone);
										break;
									}
								}
								if(!control)
									System.out.println("This user is not in the library!!");	
								break;
							}
							default:
							{
								ans = false;
								System.out.println("Exit User Management System!!");
								break;
							}	
						}						
						if(ans)
						{
						    try {
						        FileOutputStream ufout = new FileOutputStream("User.dat");
						        ObjectOutputStream uoos = new ObjectOutputStream(ufout);
						        uoos.writeObject(User);
						        uoos.close();
						        }
						     catch (Exception e) { e.printStackTrace(); }
							
							System.out.println("\n\nPlease enter your choose :\n1->New" + "			" + "4->delete\n2->inquire user"
									+ "		" + "5->inquire reserve\n3->dump" + "			" + "6->edit\nothers->exit");
							choose = s.nextInt();					
						}
						
					}while(ans);					
					break;
				}
				default:
				{
					handle = false;
					System.out.println("Bye Bye!!");
					System.exit(1);
				}
			}//switch end	

			System.out.println("\n_______________________________________");
			System.out.println("Please enter your choose:\na->Book Management System "
					+ "\nb->User Management System \nothers->exit");		
			system_choose = s.next();	
			
		}while(handle);//do-while end
	}
}
