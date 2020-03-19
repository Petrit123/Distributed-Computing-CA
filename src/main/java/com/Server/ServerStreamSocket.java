package com.Server;

import java.net.*;

import Requests.Request;

import java.io.*;

public class ServerStreamSocket extends Socket {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
		
	ServerStreamSocket(Socket socket) throws IOException {
		this.socket = socket;
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
	
	public void sendRequest(String request) throws IOException {
		output.print(request + "\n");
		/* The ensuing flush method call is necessary for the data to
		 * be written to the socket data stream before the
		 * socket is closed
		 */
		
		output.flush();
		// end message
	}
	

	
	public String receiveRequest() throws IOException {
		// read a line from the data stream
	    String request = input.readLine();
		return request;
	} // end message
	

	
	public void close() throws IOException {
		socket.close();
	}
		
	public void processRequest(Request request) {
		handleRequest(request);
	}
	
	public void handleRequest(Request request) {
		
		switch (request) {
		
		case LOGIN:
			System.out.print("Log in");
			break;
		case LOGOFF:
			System.out.print("Log off");
			break;
		case UPLOAD:
			System.out.println("Upload");
			break;
		case DOWNLOAD:
			System.out.println("Download");
			break;
		
		default:
			System.out.print("Error");
		}
	}
	
} // end class
