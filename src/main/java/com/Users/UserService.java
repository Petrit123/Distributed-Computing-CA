package com.Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
	
	private static final String filePathThatStoresUserLoginInfo = "C:\\CAUsers\\";
	private File file = new File(filePathThatStoresUserLoginInfo + "\\users.txt");
	private User user = new User();
	private List<User> users = new ArrayList<User>();
	private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	
	
	public void createUser() {
		
		try {
		System.out.println("To create a new user please specify a valid username: ");
		String enteredUserName = READER.readLine();
		if (!isValidUserName(enteredUserName)) {
			createUser();
		} 
		user.setUserName(READER.readLine());
		System.out.println("Please enter in your new password: ");
		READER.readLine();
		System.out.println("Please confirm your password: ");
		String secondPasswordEntered = READER.readLine();
        user.setPassWord(secondPasswordEntered);
        saveUserInfo(user.getUserName(), user.getPassWord());
		users.add(user);
		} catch (IOException ex) {
			System.out.println("Error in creating user");
			ex.printStackTrace();

		}
	}
	
	public void saveUserInfo(String userName, String password) throws IOException {
		
	if (!file.exists()) {
		System.out.println("Making directory " + file);
		try {
			file.mkdir();
			System.out.println(file + " has successfully been created");
            
			writeUserInfoToFile(userName, password);
			
			System.out.println("User " + userName + " has been successfully registered.");
			
		} catch(SecurityException e) {
			e.printStackTrace();
		}
	} else {
		
		writeUserInfoToFile(userName, password);
		System.out.println("User " + userName + " has been successfully registered.");
	}
 }
	
	public void writeUserInfoToFile(String userName, String password) throws IOException {
		try {
			String userLogInInfoFilePath = filePathThatStoresUserLoginInfo + "\\users.txt";
			 BufferedWriter writer = new BufferedWriter (new FileWriter(userLogInInfoFilePath, true));
			 writer.write("Username: " + userName);
			 writer.newLine();
			 writer.write("Password: " + password);
			 writer.newLine();
			 writer.close();
		} catch (FileNotFoundException e) {
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

}
