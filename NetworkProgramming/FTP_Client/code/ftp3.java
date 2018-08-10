import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

import java.nio.charset.*;
import java.nio.*;

public class ftp3 {

	Socket ctrlSocket;
	public PrintWriter ctrlOutput;
	public BufferedReader ctrlInput;	
	final int CTRLPORT = 21;
	
	public void openConnection(String host) throws UnknownHostException, IOException
	{
		ctrlSocket = new Socket(host, CTRLPORT);
		ctrlOutput = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ctrlSocket.getOutputStream(), "UTF-8")));
		ctrlInput = new BufferedReader(new InputStreamReader(ctrlSocket.getInputStream(), "UTF-8"));
	
	}
	
	public void closeConnection() throws IOException
	{
		ctrlSocket.close();
	}

	public void doLogin(String loginName, String password)
	{
	
		try {
			
			ctrlOutput.println("USER " + loginName);
			ctrlOutput.flush();
			ctrlOutput.println("PASS " + password);
			ctrlOutput.flush();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.exit(1);
		
		}
	}
	
	public void doCd(String dirName)
	{
		try {			
			
			String[] s = dirName.split("\\\\");			
			String target = s[s.length-1];
				
			ctrlOutput.println("CWD " + target);
			ctrlOutput.flush();			
			
		} catch (Exception e) {			
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	public void doCdup()
	{
		try{
			ctrlOutput.println("CDUP ..");
			ctrlOutput.flush();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void doLs()
	{
		try {
			StringBuffer s = new StringBuffer();
			int n;
			byte[] buff = new byte[1024];
			Socket dataSocket = dataConnection("LIST");
			BufferedInputStream dataInput = new BufferedInputStream(dataSocket.getInputStream());
		
			while((n = dataInput.read(buff)) > 0)
			{				
				String temp = new String(buff, 0, n, Charset.forName("UTF-8"));
				s.append(temp+"\n");
			}
			JOptionPane.showMessageDialog(null, s, "LIST", JOptionPane.INFORMATION_MESSAGE);
			dataSocket.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Socket dataConnection(String ctrlcmd)
	{
		String cmd = "PORT ";
		int j;
		Socket dataSocket = null;
		
		try {
			
			byte[] address = InetAddress.getLoopbackAddress().getAddress();
			ServerSocket serverDataSocket = new ServerSocket(0 ,1);
			
			for(j = 0 ; j < 4 ; ++j)
				cmd = cmd + (address[j] & 0xff) + ",";
			
			cmd = cmd + (((serverDataSocket.getLocalPort())/256 & 255) & 0xff) 
					+ "," 
					+ ((serverDataSocket.getLocalPort() & 255) & 0xff);
			
			
			ctrlOutput.println(cmd);
			ctrlOutput.flush();
			
			ctrlOutput.println(ctrlcmd);
			ctrlOutput.flush();
			
			dataSocket = serverDataSocket.accept();
			serverDataSocket.close();			
			
		} catch (IOException e) {			
			e.printStackTrace();
			System.exit(1);
		}
		
		return dataSocket;
		
	}

	public void doGet(String fileName, String choo)
	{
		try{
			int n;
			byte[] buff = new byte[1024];

			if(choo.equalsIgnoreCase("A"))
				doAscii();
			else if(choo.equalsIgnoreCase("I"))
				doBinary();		
			
			FileOutputStream outfile = new FileOutputStream(fileName);
			Socket dataSocket = dataConnection("RETR " + fileName);
			BufferedInputStream dataInput = new BufferedInputStream(dataSocket.getInputStream());
			
			while((n = dataInput.read(buff)) > 0)
			{
				outfile.write(buff, 0, n);				
			}
			
			JOptionPane.showMessageDialog(null,  System.getProperty("user.dir"), "NOW", JOptionPane.CLOSED_OPTION);
			
			dataSocket.close();
			outfile.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void doPut(File f, String choo)
	{
		try{
			
			int n;
			byte[] buff = new byte[1024];
			FileInputStream sendfile = null;
			
			if(choo.equalsIgnoreCase("A"))
				doAscii();
			else if(choo.equalsIgnoreCase("I"))
				doBinary();	

			try {
				sendfile = new FileInputStream(f.getName());
			} catch (Exception e) {
				System.out.println("The file not found: " + f.getAbsolutePath());
				return;
			}
			
			Socket dataSocket = dataConnection("STOR " + f.getName());
			BufferedOutputStream outstr = new BufferedOutputStream(dataSocket.getOutputStream());
			
			while((n = sendfile.read(buff)) > 0)
			{
				outstr.write(buff, 0, n);
				outstr.flush();
			}
			
			dataSocket.close();
			sendfile.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doAscii()
	{
		
		try {
			
			ctrlOutput.println("TYPE A");
			ctrlOutput.flush();
			JOptionPane.showMessageDialog(null, "200 Type set to A", "ASCII", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doBinary()
	{
		try {
			
			ctrlOutput.println("TYPE I");
			ctrlOutput.flush();
			JOptionPane.showMessageDialog(null, "200 Type set to I", "Binary", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void delete(String fileName)
	{

		try{

			ctrlOutput.println("DELE " + fileName);
			ctrlOutput.flush();
			JOptionPane.showMessageDialog(null, "Delete file success.", "MKD", JOptionPane.INFORMATION_MESSAGE);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doMkd(String fileName)
	{

		try{
		
			ctrlOutput.println("MKD " + fileName + "\r\n");
			ctrlOutput.flush();		
			JOptionPane.showMessageDialog(null, "Make a new folder success.", "MKD", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception e)
		{
			
			JOptionPane.showMessageDialog(null, "Make a new folder failed.", "MKD", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doRmd(String fileName)
	{
		try{
			
			ctrlOutput.println("RMD " + fileName);
			ctrlOutput.flush();
			JOptionPane.showMessageDialog(null, "Remove folder success.", "RMD", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doPwd()
	{
		
		try {
			
			ctrlOutput.println("PWD ");
			ctrlOutput.flush();			
			
		} catch (Exception e) {			
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doNlst()
	{
		try {
			StringBuffer s = new StringBuffer();
			int n;
			byte[] buff = new byte[1024];
			Socket dataSocket = dataConnection("NLST");
			BufferedInputStream dataInput = new BufferedInputStream(dataSocket.getInputStream());
		
			while((n = dataInput.read(buff)) > 0)
			{				
				String temp = new String(buff, 0, n, Charset.forName("UTF-8"));
				s.append(temp+"\n");
			}
			
			JOptionPane.showMessageDialog(null, s, "NLST", JOptionPane.INFORMATION_MESSAGE);
			dataSocket.close();
			dataSocket.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
			System.exit(1);
		}
	}	
	
	public void doQuit()
	{
		try{
			ctrlOutput.println("QUIT");
			ctrlOutput.flush();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void getMsgs()
	{
		try{
			
			CtrlListen listener = new CtrlListen(ctrlInput);
			Thread listenerThread = new Thread(listener);
			listenerThread.start();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}

class CtrlListen implements Runnable {
	
	BufferedReader ctrlInput = null;
	
	
	public CtrlListen(BufferedReader in)
	{
		ctrlInput = in;
	}

	public void run()
	{
		while(true)
		{
			try{
								
				String ss = ctrlInput.readLine();
				String temp =  new String(ss.getBytes("UTF8"),"UTF8");
				
				if(temp.startsWith("230"))
				{
					gui.control = true;
				}
				if(temp.startsWith("257"))
					JOptionPane.showMessageDialog(null,  temp, "PWD", JOptionPane.INFORMATION_MESSAGE);
				
				gui.cmdTextArea.append(temp+"\n"); 
			      
			}catch(Exception e)
			{
				System.err.print(e.getMessage());
				System.exit(1);
			}
		}
	}
}
