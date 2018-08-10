import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class receieveEmail {

	final int POP_PORT = 110;
	BufferedReader pop_in = null;
	PrintWriter pop_out = null;
	Socket pop = null;
	LinkedList<String> back = new LinkedList<String>();
	LinkedList<String> send = new LinkedList<String>();
	
	public void getLines(String command) throws IOException
	{
		pop_in = new BufferedReader(new InputStreamReader(pop.getInputStream()));
		pop_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pop.getOutputStream())));
		boolean cont = true;
		String buf = null;
		
		pop_out.print(command+"\r\n");
		pop_out.flush();
		
		String res = pop_in.readLine();
		
		if(!("+OK".equals(res.substring(0, 3))))
		{
			pop.close();
			//throw new RuntimeException(res);
		}
		while(cont)
		{
			buf = pop_in.readLine();
			back.add(buf);
			if(".".equals(buf))
				cont = false;
		}
	}
	
	public String getSingleLine(String command) throws IOException
	{
		pop_in = new BufferedReader(new InputStreamReader(pop.getInputStream()));
		pop_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pop.getOutputStream())));
		pop_out.print(command+"\r\n");
		pop_out.flush();
		System.out.println(command);
		String res = pop_in.readLine();
		System.out.println(res);
		
		if(!("+OK").equals(res.substring(0, 3)))
		{
			pop.close();
			return null;
			//throw new RuntimeException(res);
		}
		else
			return res;			
	}
	
	public LinkedList saveDetail()
	{
		send.clear();
		int i = 0;
		while(i < back.size())
		{
			if(back.get(i).contains("Return-Path"))
			{
				String temp = back.get(i).replace("Return-Path:", "").trim();
				send.add("@"+temp.trim());//@ = sender
			}
			else if(back.get(i).contains("Subject:"))
			{
				String s = back.get(i).replace("Subject:", "");
				try {
					send.add("*"+(s.trim()));//* = title
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(back.get(i).equals(""))
			{
				for(int j = i ; j < back.size()-2 ; j++)
					send.add(back.get(j).trim());
				
				break;				
			}
			i++;
		}

		return send;
	}
	
	public LinkedList listDetail()
	{
		
		for(int i = 0 ; i < back.size() ; i++)
		{
			if(back.get(i).contains("CST"))
			{
				String[] temp = back.get(i).split(";|\\+");
				send.add("3"+temp[1].trim());//3 = time
			}
			if(back.get(i).contains("Return-Path"))
			{
				String temp = back.get(i).replace("Return-Path:", "").trim();
				send.add("2"+temp.trim());//2 = sender
			}
			if(back.get(i).contains("Subject:"))
			{
				String s = back.get(i).replace("Subject:", "");
				try {
					send.add("1"+(s.trim()));//1 = title
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		return send;
	}
	
	public void clear()
	{
		send.clear();
		back.clear();
	}
	
	public void authorization(String server, String name, String pwd) throws IOException
	{
		int cont = 0;

		pop = new Socket(server, POP_PORT);
		pop_in = new BufferedReader(new InputStreamReader(pop.getInputStream()));
		pop_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pop.getOutputStream())));
		
		String res = pop_in.readLine();
		
		if(!("+OK").equals(res.substring(0,3)))
		{
			pop.close();
			JOptionPane.showMessageDialog(null,  "Server Connect Failed!!", "Connect", JOptionPane.ERROR_MESSAGE);
			cont = 1;//1:connect failed
			//throw new RuntimeException(res);
		}			

		cont = Login("USER "+ name, cont);
		if(cont != 0)
		{
			JOptionPane.showMessageDialog(null , "Account Error!!", "Login", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if(Login("PASS "+ pwd, cont) != 0)
				JOptionPane.showMessageDialog(null , "Password Error!!", "Login", JOptionPane.ERROR_MESSAGE);
			else
			{
				JOptionPane.showMessageDialog(null , "Login Success!!", "Login", JOptionPane.INFORMATION_MESSAGE);
				gg.b = true;
			}
		}
		
	}
	
	public void authorization2(String server, String name, String pwd) throws IOException
	{
		int cont = 0;

		pop = new Socket(server, POP_PORT);
		pop_in = new BufferedReader(new InputStreamReader(pop.getInputStream()));
		pop_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pop.getOutputStream())));
		
		String res = pop_in.readLine();
		
		if(!("+OK").equals(res.substring(0,3)))
		{
			pop.close();
			JOptionPane.showMessageDialog(null,  "Server Connect Failed!!", "Connect", JOptionPane.ERROR_MESSAGE);
			cont = 1;//1:connect failed
			//throw new RuntimeException(res);
		}			

		cont = Login("USER "+ name, cont);

		int i = Login("PASS "+ pwd, cont);
		gg.b = true;
			
		
	}
	
	public int Login(String command, int cont) throws IOException
	{		
		pop_in = new BufferedReader(new InputStreamReader(pop.getInputStream()));
		pop_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pop.getOutputStream())));
		pop_out.print(command+"\r\n");
		pop_out.flush();
		System.out.println(command);
		String res = pop_in.readLine();
		System.out.println(res);
		
		if(!("+OK").equals(res.substring(0, 3)))
		{
			pop.close();
			cont = 2;//login failed
			//throw new RuntimeException(res);
		}		
		
		return cont;
	}
	
	public void update() throws IOException
	{
		String s = getSingleLine("QUIT");
		JOptionPane.showMessageDialog(null,  s.substring(3, s.length()), "QUIT", JOptionPane.INFORMATION_MESSAGE);
		pop.close();
	}
	
}
