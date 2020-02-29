package com.Client;

import java.io.*;


public class EchoClient2 {
      private static final String MESSAGE_TO_END_CONNECTION = "Exit";
      public InputStreamReader is = new InputStreamReader(System.in);
      public BufferedReader br = new BufferedReader(is);
      
      public void startClient() {
    	 try {
         EchoClientHelper2 helper = new EchoClientHelper2(getHostName(), getPortNumber());
         boolean done = false;
         String message, echo;
         while (!done) {
            System.out.println("Enter a line to receive an echo "
               + "from the server, or a single period to quit.");
            message = br.readLine( );
            if ((message.trim()).equalsIgnoreCase(MESSAGE_TO_END_CONNECTION)){
               done = true;
               helper.done( );
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
      
      
      public String getHostName() throws IOException {
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
