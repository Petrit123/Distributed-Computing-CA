package com.Client;

import java.io.*;


public class Client {
      private static final String MESSAGE_TO_END_CONNECTION = "Exit";
      public InputStreamReader is = new InputStreamReader(System.in);
      public BufferedReader br = new BufferedReader(is);
      
      public void startClient() {
    	 try {
         ClientHelper helper = new ClientHelper(getHostName(), getPortNumber());
         boolean sessionStarted = true;
         
         String message; 
         String echo;
         
         while (sessionStarted) {
            System.out.println("Enter a line to receive an echo " + "from the server, or a single period to quit.");

            message = br.readLine( );
            
            if ((message.trim()).equalsIgnoreCase(MESSAGE_TO_END_CONNECTION)){
               sessionStarted = false;
               helper.terminateSession();
            }
            else {
               echo = helper.getEcho( message);
               System.out.println(echo);
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } //end catch
    	 
     }
      
      
      public String getHostName() {
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
      
      public String getPortNumber() {

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
      
} // end class