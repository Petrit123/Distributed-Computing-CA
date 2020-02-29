package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ClientDriver {
   public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
	   
	   EchoClient2 client = new EchoClient2();
	   
	   client.startClient();
	   
   } //end main
} // end class