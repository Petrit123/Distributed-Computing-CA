package com.Server;

import java.net.*;
import java.io.*;

public class ClientSocket {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public ClientSocket(InetAddress acceptorHost, int acceptorPort) throws SocketException, IOException {
		socket = new Socket(acceptorHost, acceptorPort);
		setStreams();
	}
	
	private void setStreams() throws IOException {
		
		// get an input stream for reading from the data socket
		InputStream inStream = socket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream = socket.getOutputStream();
		// create a PrinterWriter object character-mode output
		output = new PrintWriter(new OutputStreamWriter(outStream));
	}
	
	public void sendMessage(String message) throws IOException {
		output.print(message + "\n");
		/* The ensuing flush method call is necessary for the data to
		 * be written to the socket data stream before the
		 * socket is closed
		 */
		
		output.flush();
		// end message
	}
	
	public String receiveMessage() throws IOException {
		// read a line from the data stream
		String message = input.readLine();
		return message;
	} // end message
	
	public void close() throws IOException {
		socket.close();
	}
	
}
