package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ClientDriver {
   public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
	   
	   Client client = new Client();
	   
	   client.startClient();
	   
   } //end main
} // end class