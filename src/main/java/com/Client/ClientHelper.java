package com.Client;

import java.net.*;
import java.io.*;
import com.Server.ClientSocket;;

public class ClientHelper {

	   private static final String MESSAGE_TO_END_CONNECTION = "Exit";
       public ClientSocket mySocket;
	   public InetAddress serverHost;
	   public int serverPort;

	   ClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException, IOException {
	  	   this.serverHost = InetAddress.getByName(hostName);
	  	   this.serverPort = Integer.parseInt(portNum);
	       //Instantiates a stream-mode socket and wait for a connection.
	   	   this.mySocket = new ClientSocket(this.serverHost, this.serverPort); 
	/**/   System.out.println("Connection request made");
	   } // end constructor
		
	   public String getEcho( String message) throws SocketException, IOException {     
	      String echo = "";    
	      mySocket.sendMessage( message);
		   // now receive the echo
	      echo = mySocket.receiveMessage();
	      return echo;
	   } // end getEcho

	   public void terminateSession() throws SocketException, IOException {
	      mySocket.sendMessage(MESSAGE_TO_END_CONNECTION);
	      mySocket.close();
	   } // end done 
	} //end class