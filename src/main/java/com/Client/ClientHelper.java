package com.Client;

import java.net.*;

import javax.swing.JOptionPane;

import Requests.Request;

import java.io.*;;

public class ClientHelper {

	   private static final String MESSAGE_TO_END_CONNECTION = "Exit";
       public ClientStreamSocket mySocket;
	   public InetAddress serverHost;
	   public int serverPort;

	   ClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException, IOException {
	  	   this.serverHost = InetAddress.getByName(hostName);
	  	   this.serverPort = Integer.parseInt(portNum);
	  	   try {
	  		 this.mySocket = new ClientStreamSocket(this.serverHost, this.serverPort);
	  	   }
	  	   catch(SocketException s) {
	  		   
	  		 JOptionPane.showMessageDialog(null, s.getStackTrace(), "Error", JOptionPane.INFORMATION_MESSAGE);
	  		   
	  	   } catch (UnknownHostException u) {
	  		   
	  		 JOptionPane.showMessageDialog(null, u.getStackTrace(), "Error", JOptionPane.INFORMATION_MESSAGE);
	  		   
	  	   } catch (IOException i) {
	  		   
	  		 JOptionPane.showMessageDialog(null, i.getStackTrace(), "Error", JOptionPane.INFORMATION_MESSAGE);
	  		   
	  	   }
	   } 

	   
	   
	   public String getEcho(String message) throws SocketException, IOException {     
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