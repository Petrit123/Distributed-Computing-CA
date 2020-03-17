package com.Client;

import java.net.*;

import javax.swing.JOptionPane;

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
	  	 instantiateStreamModeSocket(serverHost,serverPort);
	   } 
		
	   public void instantiateStreamModeSocket(InetAddress serverHost, int serverPort) {
		   
		   try {
			   this.mySocket = new ClientSocket(serverHost, serverPort);
			   //JOptionPane.showMessageDialog(null, "Connection to server established successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		   } catch (Exception ex) {
			   JOptionPane.showMessageDialog(null, "The server is off. Please turn on server to continue.", "Error", JOptionPane.INFORMATION_MESSAGE);
		   }
	   }
	   
	   
	   public String getEcho( String message) throws SocketException, IOException {     
	      String echo = "";    
	      mySocket.sendMessage( message);
		   // now receive the echo
	      echo = mySocket.receiveMessage();
	      return echo;
	   } 

	   public void terminateSession() throws SocketException, IOException {
	      mySocket.sendMessage(MESSAGE_TO_END_CONNECTION);
	      mySocket.close();
	   } 
	} 