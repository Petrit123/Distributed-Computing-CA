package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import com.TMP.TMPService;
import com.Users.User;
import com.Users.UserService;

import Requests.Request;

import com.GUI.*;

public class Client {
      private static final String MESSAGE_TO_END_CONNECTION = "Exit";
      public InputStreamReader is = new InputStreamReader(System.in);
      public BufferedReader br = new BufferedReader(is);
      public UserService s = new UserService();
      public TMPService tmp = new TMPService();
      public int sessionId = 0;
      public User user;
      public static ClientHelper helper;
      public static String clientRequest = "";
      
      
      public void startClient(String hostName, String portNumber) {
         try {
			helper = new ClientHelper(getHostName(hostName), getPortNumber(portNumber));
			//startClientApplication();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
     }
      
      public void startClientApplication() {
     	 try {
          boolean sessionStarted = true;
          
          String request;
          String echo;
          if(sessionStarted) {
        	 request = clientRequest;
             if ((request.trim()).equals(MESSAGE_TO_END_CONNECTION)){
                sessionStarted = false;
                helper.terminateSession();
                System.out.print("Session terminated");
             }
             else if (request != ""){
                echo = helper.getEcho(request);
                System.out.println(echo);
             }
             clientRequest = "";
           } 
       }  
       catch (Exception ex) {
          ex.printStackTrace( );
       } 
      }
      


      
      private String getHostName(String hostName) {
    	  
    		  //hostName = br.readLine();
    		  if (hostName.length() == 0) {
    			  hostName = "localhost";
    		  }
    	  
    	  return hostName;
      }
      
      private String getPortNumber(String portNumber) {


        	  //portNumber = br.readLine();
              if (portNumber.length() == 0) {
                  portNumber = "5094";          // default port number  
              }

          
          return portNumber;
      }
      
      
public void displayHomeScreen() {
	HomeScreen window = new HomeScreen();
	window.frame.setVisible(true);
     ConnectToServerForm frame = new ConnectToServerForm();
	
	try {
		for (int i = 0; i <= 100; i++) {
			Thread.sleep(30);
			window.lblNewLabel.setText(Integer.toString(i));
			window.progressBar.setValue(i);
			if (i == 100) {
				window.frame.setVisible(false);
				frame.setVisible(true);
				
				//break;
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}

}

public static String sendUserLogInDetails(String responseCode, Request request, String userName, String password) {
	
	String userLogInRequest = responseCode + "," + request + "," +   userName + "," + password;
	
	System.out.println(userLogInRequest);
	
	String serverResponse = "";
	try {
		serverResponse = helper.getEcho(userLogInRequest);
	} catch (SocketException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return serverResponse;
}

public static String sendUserRegistrationDetails(String responseCode, Request request, String userName, String password) {
	
	String createUserRequest = responseCode + "," + request + "," + userName + "," + password;
	
	System.out.println(createUserRequest);
	
	String serverResponse = "";
	
	try {
		serverResponse = helper.getEcho(createUserRequest);
	}  catch (SocketException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return serverResponse;
}
      
} 
