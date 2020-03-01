package com.Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
	
	private static final String filePathThatStoresUserLoginInfo = "C:\\CAUsers\\";
	private File file = new File("users.txt");
	private User user = new User();
	private List<User> users = new ArrayList<User>();
	private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	
	
	public void createUser() {
		
		try {
		System.out.println("To create a new user please specify a valid username: ");
		String enteredUserName = READER.readLine();
//		if (!isValidUserName(enteredUserName)) {
//			createUser();
//		} 
		user.setUserName(enteredUserName);
		System.out.println("Please enter in your new password: ");
		READER.readLine();
		System.out.println("Please confirm your password: ");
		String secondPasswordEntered = READER.readLine();
        user.setPassWord(encryptPassword(secondPasswordEntered, 2));
        saveUserInfo(enteredUserName, secondPasswordEntered);
		users.add(user);
		} catch (IOException ex) {
			System.out.println("Error in creating user");
			ex.printStackTrace();

		}
	}
	
	public void saveUserInfo(String userName, String password) throws IOException {
		
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.txt.txt")));
			 writer.write(userName+ ": ");
			 writer.write(encryptPassword(password, 2));
			 writer.close();
			 System.out.println("Successfully saved to file");
		}  catch (FileNotFoundException e) {
			System.out.println("Error writing to file ");
			e.printStackTrace();
	      }
		
 }
	
	public boolean isValidUserName(String userName) {
		
		boolean isValidUser = true;
		
		try {
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String existingUserName = sc.nextLine();
			if (existingUserName.contains(userName)) {
				System.out.println("Username already exists.");
				isValidUser = false;
				break;
			}
		}
		sc.close();
		} catch (IOException e) {
			System.out.println("Error in validating user");
			e.printStackTrace();
		}
		
		return isValidUser;
	}
	
	public int getNumRows(String passwordInPlainText, int numColumns) {
		passwordInPlainText = passwordInPlainText.replaceAll("\\s", "");
		int numRows;
		if (passwordInPlainText.length() % numColumns == 0) {
			
			numRows = (passwordInPlainText.length() / numColumns);
		} else {
			numRows = ((passwordInPlainText.length() / numColumns) + 1);
		}
		
		return numRows;
	}
	
	public String encryptPassword(String passwordInPlainText, int numColumns) {
		passwordInPlainText = passwordInPlainText.replaceAll("\\s","");
		
		int numRows = getNumRows(passwordInPlainText, numColumns);
		String encryptedText = "";
		
		for (int col = 0; col < numColumns; col++) {
			int index = col;
			
			for (int row = 0; row < numRows; row ++) {
				
				encryptedText += passwordInPlainText.charAt(index);
				index += numColumns;
			}
		}
		
		return encryptedText;
	}
	
	public String decryptPassword(String encryptedPassword, int numColumns) {
		encryptedPassword = encryptedPassword.replaceAll("\\s+", "");
		int numRows = getNumRows(encryptedPassword,numColumns);
		return encryptPassword(encryptedPassword, numRows);
	}
	

}
