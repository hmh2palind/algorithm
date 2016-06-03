package com.xxx.yyy.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPServer extends Thread{
	private int SO_TIME_OUT = 100000;
	private ServerSocket serverSocket;

	public TCPServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(SO_TIME_OUT);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				
				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				// Print local and remote information
				System.out.println("Local Port: " + server.getLocalPort());
				System.out.println("Local IP: " + server.getLocalSocketAddress());
				System.out.println("Remote Port: " + server.getPort());
				System.out.println("Remote IP: " + server.getRemoteSocketAddress());
				
				DataInputStream in = new DataInputStream( server.getInputStream());
				
				System.out.println(in.readUTF());
				DataOutputStream out = new DataOutputStream( server.getOutputStream());
				
				out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
				server.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			Thread t = new TCPServer(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
