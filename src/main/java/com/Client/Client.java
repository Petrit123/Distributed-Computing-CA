package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

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
      public static ClientHelper helper;
      
      
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
      
      public static void startClientApplication(JTextArea textArea, String message) {
     	 try {
     		 
          boolean sessionStarted = true;
          
          String echo;
          
          if (sessionStarted) {
             System.out.println("Enter a line to receive an echo " + "from the server, or type exit to quit.");
             //tmp.uploadMessage(user.getUserName(), message);
             
             if ((message.trim()).equalsIgnoreCase(MESSAGE_TO_END_CONNECTION)){
                //sessionStarted = false;
                helper.terminateSession();
                System.out.print("Session terminated");
             }
             else if (message != "") {
                echo = helper.getEcho( message);
                textArea.append("\n" + echo);
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
