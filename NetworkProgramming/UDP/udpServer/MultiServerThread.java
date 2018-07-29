import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
	private String request;
	private int PORT;
	private InetAddress add;
	public String[] docs;

	public MultiServerThread(String request, int Port, InetAddress add) {
		super("MultiServerThread");
		this.request = request;
		this.PORT = Port;
		this.add = add;
	}

	public void run() {

		try {
			int size;
			String bye = "bye";
			byte[] buffer = new byte[16];
			DatagramSocket socket = new DatagramSocket(0);
			
			File file = new File("MediaList\\");
			docs = file.list();
			buffer = new byte[16];
			
			if(request.equals("start"))//send media list to client
			{				
				for(int i = 0 ; i < docs.length ; i++)
				{
					String temp = docs[i];
					byte[] fd = temp.getBytes();

					DatagramPacket response = new DatagramPacket(fd, fd.length, add, PORT);
					socket.send(response);	        		
				}
				
				buffer = bye.getBytes();
        		DatagramPacket response = new DatagramPacket(buffer, buffer.length,	add, PORT);
	        		socket.send(response); 

			}
			
			//send file to client
			for(int i = 0 ; i < docs.length ; i++)
			{				
				if(request.equals(docs[i]))
				{
					FileInputStream fd = new FileInputStream(".\\MediaList\\"+request);
					buffer = new byte[16];
					
					//int j = 0;
					
					while (fd.available() > 0) {
						size = fd.read(buffer);
						DatagramPacket response = new DatagramPacket(buffer,
								buffer.length, add, PORT);
						socket.send(response);
		
						//j++;
						//System.out.println(j);
		
					}
					fd.close();
					buffer = bye.getBytes();
					DatagramPacket response = new DatagramPacket(buffer, buffer.length, add, PORT);
					socket.send(response);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
