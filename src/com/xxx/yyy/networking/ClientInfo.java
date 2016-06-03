package com.xxx.yyy.networking;

import java.net.Socket;

/**
 * ClientInfo class contains information about a client, connected to the
 * server.
 */
public class ClientInfo {
	public Socket mSocket = null;
	public ClientListener mClientListener = null;
	public ClientSender mClientSender = null;
}
