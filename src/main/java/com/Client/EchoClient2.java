package com.Client;

import java.io.*;


public class EchoClient2 {
   private static final String MESSAGE_TO_END_CONNECTION = "Exit";
   public static void main(String[] args) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      try {
         System.out.println("Welcome to the Echo client.\n" +
            "What is the name of the server host?");
         String hostName = br.readLine();
         if (hostName.length() == 0) // if user did not enter a name
            hostName = "localhost";  //   use the default host name
         System.out.println("What is the port number of the server host?");
         String portNum = br.readLine();
         if (portNum.length() == 0)
            portNum = "5094";          // default port number
         EchoClientHelper2 helper = 
            new EchoClientHelper2(hostName, portNum);
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
   } //end main
} // end class
