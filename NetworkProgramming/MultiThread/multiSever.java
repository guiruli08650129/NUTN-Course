import java.net.*;
import java.io.*;

public class multiSever {
	public static void main(String[] args) throws IOException{
		ServerSocket severSocket = null;
		boolean listening = true;
		
		try
		{
			severSocket = new ServerSocket(1234);
			System.out.println("SEVER START!!");
		}
		catch(IOException e)
		{
			System.err.println("Could not listen on port: 1234.");
			System.exit(0);
		}
		
		while(listening)
			new multiServerThread(severSocket.accept()).start();
		
		severSocket.close();
	}
}
