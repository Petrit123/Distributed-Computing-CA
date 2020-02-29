package Server;

import java.io.*;



public class ServerThread implements Runnable {
	
	public static final String endMessage = ".";
	StreamSocket myDataSocket;
	
	ServerThread(StreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
	
	public void run() {
		boolean done = false;
		String message;
		try {
			while (!done) {
				message = myDataSocket.receiveMessage();
				System.out.println("Message received: " + message);
				if ((message.trim()).equals(endMessage)) {
					// Session over; close the data socket.
					System.out.println("Session over.");
					myDataSocket.close();
					done = true;
				} // end if
				else {
					// Now send the echo to the requestor
					myDataSocket.sendMessage(message);
				} // end else
			} // end while !done
		} // end try
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} // end catch
	} // end run

} // end class
