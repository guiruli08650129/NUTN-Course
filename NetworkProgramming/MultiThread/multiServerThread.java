import java.net.*;
import java.io.*;

public class multiServerThread extends Thread {
	private Socket socket = null;
	
	public multiServerThread(Socket socket) {
		super("multiServerThread");
		this.socket = socket;
	}
	public static String max_min(String msg, String control)
	{
		String s = new String();
		String[] arr = msg.split(" ");
		int[] arr_ = new int[arr.length];
		int max;
		int min;
		try
		{
			max = Integer.parseInt(arr[0]);
			min = Integer.parseInt(arr[0]);
			for(int i = 0 ; i < arr.length ; i++)
			{
				arr_[i] = Integer.parseInt(arr[i]);
				if(arr_[i] > max)
					max = arr_[i];
				if(arr_[i] < min)
					min = arr_[i];
			}	
			if(control.equals("M"))
				s = Integer.toString(max);
			else if(control.equals("m"))
				s = Integer.toString(min);
			else
				s = "FAIL";
		}catch(NumberFormatException nfe) 
		{
			System.err.println("NumberFormatException!!");
		}
		return s;
	}
	
	public void run()
	{
		try
		{
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String input, output;
			myProtocol mp = new myProtocol();
			output = mp.processInput(null);
			out.println(output);
			
			while((input = in.readLine()) != null)
			{
				if(input.equalsIgnoreCase("y"))
					input = "y";
				else if(input.equalsIgnoreCase("n"))
					input = "n";

				else
				{
					String control;
					out.println("Want max or min?(M/m)");
					control = in.readLine();
					input = max_min(input, control);
				}
				
				output = mp.processInput(input);
				out.println(output);
				if(output.equals("BYE BYE!!"))
					break;
			}
			out.close();
			in.close();
			socket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
