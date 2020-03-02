package com.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ConnectScreen {
	
	private JFrame connectToServerScreen = new JFrame("TMP: Client-Server Application");
	
	public ConnectScreen() {

	connectToServerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	connectToServerScreen.setSize(500,500);
	connectToServerScreen.setLocationRelativeTo(null);  
	
	JTextField hostNameText = new JTextField();
	hostNameText.setColumns(20);
	hostNameText.setBounds(233, 60, 92, 23);
	hostNameText.setBackground(Color.red);
	
	JTextField portNumberText = new JTextField();
	portNumberText.setColumns(20);
	portNumberText.setBounds(233, 60, 92, 23);
	portNumberText.setBackground(Color.red);
	
	connectToServerScreen.getContentPane().add(hostNameText);
    JButton connectToServerBtn = new JButton("Connect");
    connectToServerScreen.getContentPane().add(connectToServerBtn); // Adds Button to content pane of frame
    connectToServerBtn.setFont(new Font("Impact", Font.PLAIN, 40));
    connectToServerBtn.setSize(300,50);
    //connectToServerBtn.setBackground(Color.darkGray);
    connectToServerBtn.setForeground(Color.black);
    connectToServerScreen.setVisible(true);
	
	}

}
