package com.Server;

import java.io.*;

import com.Requests.Request;



public class ServerThread implements Runnable {
	
	private static final String MESSAGE_TO_END_CONNECTION = "Exit";
	public ServerStreamSocket myDataSocket;
	
	ServerThread(ServerStreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
	
	public void run() {
		boolean sessionStarted = true;
		String request;
		try {
			while (sessionStarted) {
				request = myDataSocket.receiveRequest();
				System.out.println("Request received: " + request);				
				if((request.trim()).equals(MESSAGE_TO_END_CONNECTION)) {
					// Session over; close the data socket.
					System.out.println("Session over.");
					myDataSocket.close();
					sessionStarted = false;
				} // end if
				else {
					// Now send the echo to the requester

					myDataSocket.sendRequest(request);
				} 
			} 
		} 
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} 
	} 
	
} 