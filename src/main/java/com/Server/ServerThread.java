package com.Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.Requests.Request;
import com.Users.User;



public class ServerThread implements Runnable {
	
	private static final String MESSAGE_TO_END_CONNECTION = "Exit";
	public ServerStreamSocket myDataSocket;
	public static List<User> loggedInUsers = new ArrayList<User>();
	ServerThread(ServerStreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
	
	
	public void run() {
		boolean sessionStarted = true;
		String request;
		ServerStreamSocket.sessionId ++;
		
		try {
			while (sessionStarted) {				
				request = myDataSocket.receiveRequest();
				System.out.print(loggedInUsers.get(0).getUserName());
				//System.out.println("Request received: " + request);				
				if((request.trim()).equals(MESSAGE_TO_END_CONNECTION)) {
					// Session over; close the data socket.
					System.out.println("Session over.");
					myDataSocket.close();
					sessionStarted = false;
				} // end if
				else {
					// Now send the echo to the requester

					myDataSocket.sendRequest(request);
					//myDataSocket.sendResponse();
				} 
			} 
		} 
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} 
	} 
	
} 