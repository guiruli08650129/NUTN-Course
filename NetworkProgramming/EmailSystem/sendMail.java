import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class sendMail {

	final int SMTP_PORT = 25;
	String smtp_server = "";
	String my_email_addr = "";
	String my_email_pwd = "";

	public boolean sendCommandAndResultCheck(Socket smtp, BufferedReader smtp_in,
						PrintWriter smtp_out, String command, int success_code)	throws IOException {
		
		smtp_out.print(command + "\r\n");
		smtp_out.flush();
		//System.out.println("send> " + command);
		boolean cont = resultCheck(smtp, smtp_in, smtp_out, success_code);
		return cont;
		
	}

	public boolean resultCheck(Socket smtp, BufferedReader smtp_in, PrintWriter smtp_out, int success_code) throws IOException {
		
		String res = smtp_in.readLine();
		//System.out.println("recv> " + res);
		boolean cont = true;

		if (Integer.parseInt(res.substring(0, 3)) != success_code) {
			cont = false;
			smtp.close();
			//throw new RuntimeException(res);			
		}
		
		return cont;
	}

	public void send(String subject, String[] to, String[] msgs) throws IOException {
		
		Socket smtp = new Socket(smtp_server, SMTP_PORT);
		BufferedReader smtp_in = new BufferedReader(new InputStreamReader(smtp.getInputStream()));
		PrintWriter smtp_out = new PrintWriter(smtp.getOutputStream());

		resultCheck(smtp, smtp_in, smtp_out, 220);

		String myname = InetAddress.getLocalHost().getHostName();
		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "HELO " + myname, 250);

		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "MAIL FROM:" + my_email_addr, 250);

		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "AUTH LOGIN", 334);
		Base64 b = new Base64();
		String id = b.encode((my_email_addr).getBytes());
		String p = b.encode((my_email_pwd).getBytes());

		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, id, 334);
		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, p, 235);

		for (int i = 0; i < to.length; i++) {
			sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "RCPT TO:" + to[i], 250);
		}

		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "DATA", 354);

		smtp_out.print("Subject:" + subject + "\r\n");
		smtp_out.print("\r\n");

		for (int i = 0; i < msgs.length ; ++i) {
			smtp_out.print(msgs[i] + "\r\n");
		}
		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "\r\n.", 250);

		sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "QUIT", 221);
		
		JOptionPane.showMessageDialog(null,  "Send Success!!", "SEND", JOptionPane.INFORMATION_MESSAGE);
		
		smtp.close();

	}

	public void setAddress(String server, String addr, String pwd) {

		try {
			
			boolean cont = true;
			smtp_server = server;
			my_email_addr = addr;
			my_email_pwd = pwd;
			
			Socket smtp = new Socket(smtp_server, SMTP_PORT);
			BufferedReader smtp_in = new BufferedReader(new InputStreamReader(smtp.getInputStream()));
			PrintWriter smtp_out = new PrintWriter(smtp.getOutputStream());

			resultCheck(smtp, smtp_in, smtp_out, 220);

			String myname = InetAddress.getLocalHost().getHostName();
			if(sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "HELO " + myname, 250) == false)
			{
				JOptionPane.showMessageDialog(null,  "HELO Error!!", "HELO", JOptionPane.ERROR_MESSAGE);
				cont = false;
			}

			if(sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "MAIL FROM:" + my_email_addr, 250) == false)
			{
				JOptionPane.showMessageDialog(null,  "MAIL FROM Error!!", "MAIL FROM", JOptionPane.ERROR_MESSAGE);
				cont = false;
			}
			
			if(sendCommandAndResultCheck(smtp, smtp_in, smtp_out, "AUTH LOGIN", 334) == false)
			{
				JOptionPane.showMessageDialog(null,  "AUTH LOGIN Error!!", "AUTH LOGIN", JOptionPane.ERROR_MESSAGE);
				cont = false;
			}
			
			Base64 b = new Base64();
			String id = b.encode((my_email_addr).getBytes());
			String p = b.encode((my_email_pwd).getBytes());

			if(sendCommandAndResultCheck(smtp, smtp_in, smtp_out, id, 334) == false)
			{
				JOptionPane.showMessageDialog(null,  "ID Error!!", "AUTH LOGIN", JOptionPane.ERROR_MESSAGE);
				cont = false;
			}
			
			if(sendCommandAndResultCheck(smtp, smtp_in, smtp_out, p, 235) == false)
			{
				JOptionPane.showMessageDialog(null,  "PASSWARD Error!!", "AUTH LOGIN", JOptionPane.ERROR_MESSAGE);
				cont = false;
			}
			
			if(cont)
				gg.c = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
