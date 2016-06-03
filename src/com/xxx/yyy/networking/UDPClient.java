package com.xxx.yyy.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	public static int portNumber = 7777;
	public static String hostName = "localhost";

	public static void main(String args[]) throws IOException {
		// Enter data from system streams
		BufferedReader cin = new BufferedReader(
				new InputStreamReader(System.in));
		echo("Enter message to send : ");
		String s = (String) cin.readLine();
		byte[] b = s.getBytes();

		// Send data
		Client client = new Client(portNumber, hostName);
		client.sendPacket(new byte[3]);
		client.sendPacket(b);
	}

	// Simple function to echo data to terminal
	public static void echo(String msg) {
		System.out.println(msg);
	}
}

/**
 * 
 * @author HungHM5
 * 
 */
class Client {
	private int bufferSize;
	private int portNumber;
	private String hostName;

	private DatagramSocket sock = null;
	private InetAddress host = null;

	/**
	 * Creates a client with port, host
	 * @throws SocketException 
	 * @throws UnknownHostException 
	 */
	public Client(int portNumber, String hostName) {
		if (portNumber < 0)
			throw new IllegalArgumentException("The port number must be nonnegative");

		this.portNumber = portNumber;
		this.hostName = hostName;
		this.bufferSize = UDPConstants.BUFFER_SIZE;
		
		createSocket();
	}

	/**
	 * Creates socket and host 
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	private void createSocket() {
		try {
			this.sock = new DatagramSocket();
			this.host = InetAddress.getByName(hostName);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a client with port, host, buffer size
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public Client(int portNumber, String hostName, int bufferSize) {
		this(portNumber, hostName);

		if (bufferSize > bufferSize || bufferSize < 0)
			throw new IllegalArgumentException("The buffer size must be nonnegative");
		this.bufferSize = bufferSize;
	}

	/**
	 * Sends packet data to a server
	 * @throws IOException 
	 */
	public void sendPacket(byte[] bytes) throws IOException {
		// Send data packet
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, host, portNumber);
		sock.send(dp);

		// Now receive reply
		// Buffer to receive incoming data
		DatagramPacket reply = new DatagramPacket(new byte[bufferSize], bufferSize);
		sock.receive(reply);

		byte[] data = reply.getData();
		String s = new String(data, 0, reply.getLength());

		// echo the details of incoming data - client ip : client port -
		// client message
		echo(reply.getAddress().getHostAddress() + UDPConstants.COLON + reply.getPort()
				+ " - " + s);
	}

	// Simple function to echo data to terminal
	private void echo(String msg) {
		System.out.println(msg);
	}
}
