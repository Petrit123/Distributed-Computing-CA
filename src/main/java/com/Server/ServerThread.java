package com.Server;

import java.io.*;



public class ServerThread implements Runnable {
	
	public final String MESSAGE_TO_END_CONNECTION = "Exit";
	StreamSocket myDataSocket;
	private boolean sessionStarted = false;
	
	ServerThread(StreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
	
	public void run() {
		sessionStarted = true;
		String message;
		try {
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
					// Now send the echo to the requestor
					myDataSocket.sendMessage(message);
				} 
			} 
		} 
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} 
	} 
	
} 