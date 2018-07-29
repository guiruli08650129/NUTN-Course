import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class udpServer {

	public final static int PORT = 4321;

	public static void main(String[] args) throws IOException {
		byte[] buffer = new byte[16];

		DatagramSocket socket = new DatagramSocket(PORT);
		DatagramPacket request = new DatagramPacket(buffer, 16);
		socket.setReceiveBufferSize(16);
		socket.setSendBufferSize(16);

		boolean listening = true;
		JOptionPane.showMessageDialog(null, "Server Start!!", "Server", JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("SUCCESS");

		while (listening) {
			
			socket.receive(request);
			String str = new String(request.getData(), 0, request.getLength(), "UTF-8");
			int reqPort = request.getPort();
			InetAddress reqAdd = request.getAddress();
			new Thread(new MultiServerThread(str, reqPort, reqAdd)).start();

		}

		socket.close();

	}
}
