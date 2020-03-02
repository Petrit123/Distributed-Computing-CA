package com.Client;

import java.io.*;

import com.TMP.TMPService;
import com.Users.User;
import com.Users.UserService;


public class Client {
      private static final String MESSAGE_TO_END_CONNECTION = "Exit";
      public InputStreamReader is = new InputStreamReader(System.in);
      public BufferedReader br = new BufferedReader(is);
      public UserService s = new UserService();
      public TMPService tmp = new TMPService();
      public int sessionId = 0;
      public User user;
      
      public void startClient() {
    	 try {
         ClientHelper helper = new ClientHelper(getHostName(), getPortNumber());
         
         displayLogOnScreen();
         boolean sessionStarted = true;
         
         String message; 
         String echo;
         
         while (sessionStarted) {
            System.out.println("Enter a line to receive an echo " + "from the server, or type exit to quit.");

            message = br.readLine();
            
            tmp.uploadMessage(user.getUserName(), message);
            
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
      
      
      private String getHostName() {
    	  System.out.println("Welcome to the Echo client.\n" +
    	            "What is the name of the server host?");
    	  String hostName = "";
    	  try {
    		  hostName = br.readLine();
    		  if (hostName.length() == 0) {
    			  hostName = "localhost";
    		  }
    	  } catch (IOException e) {
    		  e.printStackTrace();
    	  }
    	  return hostName;
      }
      
      private String getPortNumber() {

    	  System.out.println("What is the port number of the server host?");
          String portNumber = "";
          try {
        	  portNumber = br.readLine();
              if (portNumber.length() == 0) {
                  portNumber = "5094";          // default port number  
              }
          }  catch (IOException e) {
    		  e.printStackTrace();
    	  }
          
          return portNumber;
      }
      
      public void displayLogOnScreen() {
    	  try {
        	  System.out.println("Would you like to log in or sign up?");
        	  String decision = br.readLine();
        	  
        	  if (decision.equalsIgnoreCase("log in")) {
        		  s.logIn();
        		  sessionId ++;
        		  user = s.getLoggedInUserBySessionId(sessionId);
        	  } else if (decision.equalsIgnoreCase("sign up")) {
        		  s.createUser();
        	  } else {
        		  System.out.println("Sorry, please enter in a valid option");
        	  }
    	  } catch (IOException e) {
    		  System.out.println("Error in displaying log on screen");
    		  e.printStackTrace();
    	  }

      }
      
} 
