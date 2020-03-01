package com.Users;

public class Driver {
	public static void main(String[] args) {
		
		UserService s = new UserService();
		
		s.createUser();
		
//		String message = s.encryptPassword("This is a sample text password to encrypt",2);
//		
//		System.out.print(message);
//		
//		System.out.println(s.decryptPassword(message, 2));
	}

}
