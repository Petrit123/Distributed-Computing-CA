package com.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeScreen {
	
	private JFrame homeScreen = new JFrame("TMP: Client-Server Application");
	
	public HomeScreen() {

	homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	homeScreen.setSize(500,500);
	homeScreen.setLocationRelativeTo(null);  
    JButton connectToServerBtn = new JButton("Connect to server");
    homeScreen.getContentPane().add(connectToServerBtn); // Adds Button to content pane of frame
    connectToServerBtn.setFont(new Font("Impact", Font.PLAIN, 40));
    connectToServerBtn.setSize(300,50);
    connectToServerBtn.setBackground(Color.orange);
    connectToServerBtn.setForeground(Color.black);
    homeScreen.setVisible(true);
	
    connectToServerBtn.addActionListener(new ConnectToServerScreen());
	}
	
	class ConnectToServerScreen implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			ConnectScreen cn = new ConnectScreen();
			homeScreen.setVisible(false);
			homeScreen.dispose();
			
		}
		
	}
}
