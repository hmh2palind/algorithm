package com.xxx.yyy.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 
 * @author HungHM5
 * 
 */
public class UDPServer {
	private static int portNumber = 7777;

	public static void main(String args[]) throws IOException {
		Server server = new Server(portNumber);
		server.waitAndSendDatas();
	}
}
final class UDPConstants {
	public static final String COLON = " : ";
	public static int BUFFER_SIZE = 65536;
}
/**
 * Server using UDP
 * 
 */
class Server {
	private int bufferSize;
	private int portNumber;

	private DatagramSocket socket;
	private DatagramPacket packet;

	/**
	 * Creates a server with port
	 */
	public Server(int portNumber) {
		if (portNumber < 0)
			throw new IllegalArgumentException("The port number must be nonnegative");

		this.portNumber = portNumber;
		this.bufferSize = UDPConstants.BUFFER_SIZE;
		
		createServer();
	}

	/**
	 * Creating a server socket
	 */
	private void createServer() {
		try {
			// Creating a server socket, parameter is local port number
			socket = new DatagramSocket(portNumber);
			
			// Buffer to receive incoming data
			packet = new DatagramPacket(new byte[bufferSize], bufferSize);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a server with port, buffer size
	 */
	public Server(int portNumber, int bufferSize) {
		this(portNumber);

		if (bufferSize > UDPConstants.BUFFER_SIZE || bufferSize < 0)
			throw new IllegalArgumentException("The buffer size must be nonnegative");

		this.bufferSize = bufferSize;
	}
	/**
	 * Wait incoming data and send data
	 */
	public void waitAndSendDatas() throws IOException {
//		// Creating a server socket, parameter is local port number
//		socket = new DatagramSocket(portNumber);
//
//		// Buffer to receive incoming data
//		packet = new DatagramPacket(new byte[bufferSize], bufferSize);

		// Wait for an incoming data
		echo("Server socket created. Waiting for incoming data...");

		DatagramPacket dp = null;
		// Communication loop
		while (true) {
			socket.receive(packet);
			byte[] data = packet.getData();
			String s = new String(data, 0, packet.getLength());

			// echo the details of incoming data - client ip : client port -
			// client message
			echo(packet.getAddress().getHostAddress() + UDPConstants.COLON + packet.getPort() + " - " + s);

			s = "OK" + UDPConstants.COLON + s;
			dp = new DatagramPacket(s.getBytes(), s.getBytes().length,
					packet.getAddress(), packet.getPort());

			// Send data to client
			socket.send(dp);
		}
	}

	/**
	 * Simple function to echo data to terminal
	 */
	private void echo(String msg) {
		System.out.println(msg);
	}
}
