package Server;

import java.io.*;
import java.net.*;

public class Server {
	
	private static final int SERVER_PORT = 5094;
	private boolean isServerListening = false;
	
	
	public void startServer(){
		
		try {
			ServerSocket myConnectionSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server is ready for connections");
			
			listenForConnection();
			waitForConnection(myConnectionSocket);
			
		} // end try
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void listenForConnection() {
		if (!isServerListening) {
			isServerListening = true;
		}
	}
	
	public void waitForConnection(ServerSocket serverSocket) throws IOException {
		while (isServerListening) {
			// wait for a connection
			System.out.println("Waiting for a connection");
			StreamSocket myDataSocket = new StreamSocket(serverSocket.accept());
			System.out.println("Connection accepted");
			
			// Start a thread to handle this client's session
			Thread theThread = new Thread(new ServerThread(myDataSocket));
			theThread.start();
		}
	}

}

