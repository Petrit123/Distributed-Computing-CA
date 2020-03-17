package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.TMP.TMPService;
import com.Users.User;
import com.Users.UserService;
import com.GUI.*;

public class Client {
      private static final String MESSAGE_TO_END_CONNECTION = "Exit";
      public InputStreamReader is = new InputStreamReader(System.in);
      public BufferedReader br = new BufferedReader(is);
      public UserService s = new UserService();
      public TMPService tmp = new TMPService();
      public int sessionId = 0;
      public User user;
      public ClientHelper helper;
      
      
      public void startClient(String hostName, String portNumber) {
         try {
			helper = new ClientHelper(getHostName(hostName), getPortNumber(portNumber));
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
      
      private void startClientApplication() {
     	 try {
     		 
          boolean sessionStarted = true;
          
          String message; 
          String echo;
          
          while (sessionStarted) {
             System.out.println("Enter a line to receive an echo " + "from the server, or type exit to quit.");

             message = br.readLine();
             
             //tmp.uploadMessage(user.getUserName(), message);
             
             if ((message.trim()).equalsIgnoreCase(MESSAGE_TO_END_CONNECTION)){
                sessionStarted = false;
                helper.terminateSession();
             }
             else {
                echo = helper.getEcho( message);
                System.out.println(echo);
             }
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
      
//      public void displayLogOnScreen() {
//    	  try {
//        	  System.out.println("Would you like to log in or sign up?");
//        	  String decision = br.readLine();
//        	  
//        	  if (decision.equalsIgnoreCase("log in")) {
//        		  //s.logIn();
//        		  sessionId ++;
//        		  user = s.getLoggedInUserBySessionId(sessionId);
//        	  } else if (decision.equalsIgnoreCase("sign up")) {
//        		  s.createUser();
//        	  } else {
//        		  System.out.println("Sorry, please enter in a valid option");
//        	  }
//    	  } catch (IOException e) {
//    		  System.out.println("Error in displaying log on screen");
//    		  e.printStackTrace();
//    	  }
//
//      }
      
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
      
} 
