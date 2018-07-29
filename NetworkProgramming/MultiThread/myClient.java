import java.io.*;
import java.net.*;

public class myClient {
	public static void main(String[] args) throws IOException{
		 
		Socket mySocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try
		{
			mySocket = new Socket("127.0.0.1", 1234);
			out = new PrintWriter(mySocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
		}
		catch(IOException e)
		{
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromSever;
		String fromClient;
		
		while((fromSever = in.readLine()) != null)
		{
			System.out.println("Sever: " + fromSever);
			if(fromSever.equals("BYE BYE!!"))
				break;
			
			fromClient = stdIn.readLine();
			if(fromClient != null)
			{
				System.out.println("Client: " + fromClient);
				out.println(fromClient);
			}			
		}
		out.close();
		in.close();
		stdIn.close();
		mySocket.close();
	}
}
