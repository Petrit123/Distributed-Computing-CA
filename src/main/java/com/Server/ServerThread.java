package com.Server;

import java.io.*;

import Requests.Request;



public class ServerThread implements Runnable {
	
	private static final String MESSAGE_TO_END_CONNECTION = "Exit";
	public ServerStreamSocket myDataSocket;
	
	ServerThread(ServerStreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
	
	public void run() {
		boolean sessionStarted = true;
		String message;
		Request request;
		try {
			request = myDataSocket.receiveRequest();
			myDataSocket.processRequest(request);
			while (sessionStarted) {
				message = myDataSocket.receiveMessage();
				System.out.println("Message received: " + message);				
				if ((message.trim()).equalsIgnoreCase(MESSAGE_TO_END_CONNECTION)) {
					// Session over; close the data socket.
					System.out.println("Session over.");
					myDataSocket.close();
					sessionStarted = false;
				} // end if
				else {
					// Now send the echo to the requester

					myDataSocket.sendMessage(message);
				} 
			} 
		} 
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} 
	} 
	
} 