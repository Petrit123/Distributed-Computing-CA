package com.Server;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.Client.Client;
import com.Requests.Request;
import com.Responses.Response;
import com.Users.User;
import com.Users.UserService;

import java.awt.Desktop;
import java.io.*;

public class ServerStreamSocket extends Socket {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private  List<String> receivedMessageSplit;
	public static int sessionId = 0;
	public static boolean sessionStarted = false;
	private File file = new File("Users.txt");
	private static final int MESSAGE_LIMIT = 6;
	public static List<User> loggedInUsers = new ArrayList<User>();
	private String downloadedMessages = "";
	public static List<String> userMessages;
	
		
	ServerStreamSocket(Socket socket) throws IOException {
		this.socket = socket;
		setStreams();
	}
	
	private void setStreams() throws IOException {
		
		// get an input stream for reading from the data socket
		InputStream inStream = socket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream = socket.getOutputStream();
		// create a PrinterWriter object character-mode output
		output = new PrintWriter(new OutputStreamWriter(outStream));
	}
	
	public void sendRequest(String request) throws IOException {
		
		System.out.print("Server response " + request);
		output.print(request + "\n");
		/* The ensuing flush method call is necessary for the data to
		 * be written to the socket data stream before the
		 * socket is closed
		 */
		
		output.flush();
		// end message
	}
	
	
	public String receiveRequest() throws IOException {
		// read a line from the data stream
	    String request = input.readLine();	    
	    receivedMessageSplit = Arrays.asList(request.split(","));
	    String req = receivedMessageSplit.get(0);
	    ServerThread.loggedInUsers.add(trackLoggedInUser(request));
	    String response = handleRequest(req);
	    System.out.print("Server received " + req + "\n");
		return response;
	} // end message
	

	
	public void close() throws IOException {
		socket.close();
	}
		
	
	public String handleRequest(String requestt) throws IOException {
		switch (requestt) {
		
		case "100":	    
		    String userName = receivedMessageSplit.get(2);
		    String password= receivedMessageSplit.get(3);
		    String log = logIn(userName, password);
		    if (log == "200") {
		    	return "200 SUCCESS , " + sessionId;
		    } else if (log == "202") {
		    	return "202 ERROR , " + sessionId;
		    } else {
		    	return "404 DENIED , " + sessionId;
		    }
		    //sendRequest(response);
			//user.addUserToListOfUsers(userName, password);
		case "900":
			logOff();
			return "GoodBye";
		case "700":
		    userName = receivedMessageSplit.get(2);
		    String message= receivedMessageSplit.get(3);
		    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH.mm").format(new Date());
		    if (uploadMessageToArrayList(userName, message)) {
		    	return message += ", Sent ," + timeStamp;
		    } else {
		    	return message += ", Failed ," + timeStamp;
		    }
		case "600":
			userName = receivedMessageSplit.get(2);
			if (downloadMessage(userName)) {
				return "601, " + downloadedMessages;
			} else {
				return "602, failedToDownload";
			}
		case "101":
		    userName = receivedMessageSplit.get(2);
		    password= receivedMessageSplit.get(3);
		    if(createUser(userName, password)) {
		    	return "302 SUCCESS";
		    } else {
		    	return "301 FAILED";
		    }
//			break;
//		default:
//			System.out.print("Error");
		}
		
		return requestt;

	}
	

	
	public String logIn(String userName, String password) {
		String logInResponse = "";
		User user = new User();
		try {
			BufferedReader br = new BufferedReader(new FileReader("Users.txt"));	
			String userNameInFile;
			
			for (User loggedInUser: ServerThread.loggedInUsers) {
				if(loggedInUser.getUserName().trim().equals(userName) && loggedInUser.getSessionId() < sessionId) {
					user = loggedInUser;
				}
			}
			
			if (user.isLoggedIn()) {
				logInResponse = "202";
			} else {
				while ((userNameInFile = br.readLine()) != null) {
	                if (userName.equals(userNameInFile.substring(0, userNameInFile.indexOf(':'))) && password.equals(userNameInFile.substring(userNameInFile.indexOf(':') + 2))) {
	                	System.out.println("Credentials are valid");
	                	logInResponse = "200";
	                	//break;

	                } 
			}
			}
			
			br.close();
	} catch (IOException e) {
		System.out.println("Error in validating user log in details");
		e.printStackTrace();
	}
		
		return logInResponse;
		
	}
	
	public static User trackLoggedInUser(String request) {
		List<String> receivedMessageSplit = Arrays.asList(request.split(","));
	    String requestCode = receivedMessageSplit.get(0);
	    User user = new User();
	    if (requestCode.trim().equals("100")) {
		    String userName = receivedMessageSplit.get(2);
		    String password= receivedMessageSplit.get(3);
	    	user.setUserName(userName);
	    	user.setPassWord(password);
	    	user.setLoggedIn(true);
	    	user.setSessionId(sessionId);
	    }
	    
	    return user;
	    
	    
	}
	
	
	public boolean createUser(String userName, String password) {
		boolean isUserCreated = false;
		try {
		if (!isValidUserName(userName)) {
			isUserCreated = false;
			//serverResponse = "301 " + Response.FAILED;
		} else {
	        saveUserInfo(userName, password);
			System.out.println("Successfully registered user " + userName);
			isUserCreated = true;
			//serverResponse = "302 " + Response.SUCCESS;
		}
		} catch (IOException ex) {
			System.out.println("Error in creating user");
			ex.printStackTrace();

		}
		
		return isUserCreated;
	}
	
	
	public boolean isValidUserName(String userName) {
		
		boolean isValidName = true;
		
		try {
		BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
		String existingUserName;
		while ((existingUserName = br.readLine()) != null) {
			if (userName.equalsIgnoreCase(existingUserName.substring(0, existingUserName.indexOf(":")))) {
				System.out.println("Username already exists... \nPlease enter another username");
				isValidName = false;
			}

		}
		br.close();
		} catch (IOException e) {
			System.out.println("Error in validating user");
			e.printStackTrace();
		}
		
		return isValidName;
	}
	
	public void saveUserInfo(String userName, String password) throws IOException {
		
		try {
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.txt", true), StandardCharsets.UTF_8));
			 writer.append("\n" + userName + ": ").append(password);
			 //writer.append(encryptPassword(password, 2));
			 writer.close();
			 System.out.println("Successfully saved to file");
		}  catch (FileNotFoundException e) {
			System.out.println("Error writing to file ");
			e.printStackTrace();
	      }
		
 }
	
	
	public boolean uploadMessageToArrayList(String userName, String message) {
	   boolean isMessageUploaded = false;
		   userName = receivedMessageSplit.get(2);
		   message= receivedMessageSplit.get(3);
		  if (message.length() > MESSAGE_LIMIT) {
			 message = message.substring(0, MESSAGE_LIMIT);
			}
		   for (User loggedInUser: ServerThread.loggedInUsers) {
			   if (loggedInUser.getUserName().trim().equals(userName)) {
				   loggedInUser.getMessages().add(message);
				   System.out.print("\n\n " + loggedInUser.getMessages());
				   isMessageUploaded  = true;
				   break;
			   } 
		   }
		   
		   return isMessageUploaded;
		   	   
	}
	
	public boolean downloadMessage(String userName) {
		boolean isDownloaded = false;
		File userNameFile = new File(userName);
		
		if (!userNameFile.exists()) {
			userNameFile.mkdir();
		}
		
		String downloadedDetails = "<html><head></head><body><div><h1> Messages uploaded by " + userName + " to the TMP application</h1>";
		
		   for (User loggedInUser: ServerThread.loggedInUsers) {
			   if (loggedInUser.getUserName().trim().equals(userName)) {
				   for (int i = 0; i < loggedInUser.getMessages().size(); i ++) {
	      			   downloadedDetails +=  "<p> " + loggedInUser.getMessages().get(i)+ "<br> </p></div></body></html>";   
				   }
				   break;
			   } 
		   }
		
		File downloadedMessagesLocation = new File(userName + "/" + userName + "Messages.html");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(downloadedMessagesLocation));
			bw.write(downloadedDetails);
			bw.close();
			Desktop.getDesktop().open(downloadedMessagesLocation);
		   isDownloaded = true;
		} catch (IOException e1) {
			   isDownloaded = true;
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return isDownloaded;
	}
	
	public void logOff() {
		
		try {
			close();
			ServerThread.sessionStarted = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
} // end class
