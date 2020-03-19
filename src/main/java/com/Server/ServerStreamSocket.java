package com.Server;

import java.net.*;
import java.util.Arrays;
import java.util.List;

import com.Users.UserService;

import Requests.Request;

import java.io.*;

public class ServerStreamSocket extends Socket {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private String request;
	private  List<String> receivedMessageSplit;
	private UserService user = new UserService();
	
		
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
	    request = input.readLine();	    
	    receivedMessageSplit = Arrays.asList(request.split(","));
	    String req = receivedMessageSplit.get(1);
	    processRequest(Request.valueOf(req));
	    System.out.print(req);
		return request;
	} // end message
	

	
	public void close() throws IOException {
		socket.close();
	}
		
	public void processRequest(Request request) {
		handleRequest(request);
	}
	
	public void handleRequest(Request requestt) {
		
		switch (requestt) {
		
		case LOGIN:	    
		    String userName = receivedMessageSplit.get(2);
		    String password= receivedMessageSplit.get(3);
		    password = user.encryptPassword(password, 2);
		    System.out.print(user.logIn(userName, password)+ "\n");
			user.addUserToListOfUsers(userName, password);
			user.getUsersAdd();
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
		case SIGNUP:
		    userName = receivedMessageSplit.get(2);
		    password= receivedMessageSplit.get(3);
		    password = user.encryptPassword(password, 2);
		    user.createUser(userName, password);
			break;
		default:
			System.out.print("Error");
		}
	}
	
} // end class
