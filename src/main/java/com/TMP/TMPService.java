package com.TMP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class TMPService {
	
	private static final int MESSAGE_LIMIT = 180;
	
	
	public void uploadMessage(String userName, String message) {
		
		try {
			
			File userNameFile = new File(userName);
			
			if (!userNameFile.exists()) {
				userNameFile.mkdir();
			}
			
			if (message.length() > MESSAGE_LIMIT) {
				message = message.substring(0, MESSAGE_LIMIT);
			}			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userName + "/" + userName + "Messages.txt", true), StandardCharsets.UTF_8));
			bw.newLine();
			bw.append(message);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			
			System.out.print("Error in uploading message");
			e.printStackTrace();
		}
	}
	

}
