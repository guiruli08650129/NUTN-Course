import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class udpClient {

	private final static int PORT = 4321;
	public static LinkedList<String> mediaList = new LinkedList<String>();

	public udpClient()
	{}
	
	public void getList()
	{
		try{
		DatagramSocket socket = new DatagramSocket(0);
		InetAddress host = InetAddress.getByName("127.0.0.1");	
		//send start
		String data = "start";
		byte[] byteData = data.getBytes();		
		DatagramPacket request = new DatagramPacket(byteData, byteData.length, host, PORT);
		socket.send(request);
		
		
		//get media list
		DatagramPacket response = new DatagramPacket(new byte[16], 16);
		int k = 0;
		while (true) {
			socket.receive(response);
			String result = new String(response.getData(), 0, response.getLength(), "UTF-8");
			if (result.equals("bye"))
				break;
			mediaList.add(result);
		}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void getFile(String fileName) throws IOException {
		
		DatagramSocket socket = new DatagramSocket(0);
		DatagramPacket response = new DatagramPacket(new byte[16], 16);
		InetAddress host = InetAddress.getByName("127.0.0.1");	
		byte[] buffer = new byte[16];
		
		//send file which user want to download
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		byte[] byteName = fileName.getBytes();

		FileOutputStream fd = new FileOutputStream("out_" + fileName);
		DatagramPacket name = new DatagramPacket(byteName, byteName.length, host, PORT);
		socket.send(name);
		response = new DatagramPacket(new byte[16], 16);
		
		while (true) {
			socket.receive(response);
			String result = new String(response.getData(), 0, response.getLength(), "UTF-8");
			if (result.equals("bye"))
			{
				break;
			}
			buffer = response.getData();
			fd.write(buffer, 0, buffer.length);
		}
		
		fd.close();
		socket.close();		

		JOptionPane.showMessageDialog(null, "Download Success!!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
		
		
		//open file
		if(fileName.contains("mp3") || fileName.contains("mp4"))
			Runtime.getRuntime().exec("cmd /c start \"%programfiles%\\Windows Media Player\\wmplayer.exe\""+" "+"\""+"out_"+fileName+"\"");
		else
			Runtime.getRuntime().exec("cmd /c start \"%programfiles%\\Windows Photo Viewer\\PhotoViewer.dll\""+" "+"\""+"out_"+fileName+"\"");
	}

}
