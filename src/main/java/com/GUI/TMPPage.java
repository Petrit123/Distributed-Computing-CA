package com.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TMPPage extends JFrame {

	private JPanel contentPane;
	private JLabel lblUser;
	private JLabel lblSessionId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TMPPage frame = new TMPPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TMPPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton uploadBtn = new JButton("Upload Message");
		uploadBtn.setBounds(10, 117, 173, 30);
		contentPane.add(uploadBtn);
		
		JButton downloadBtn = new JButton("Download Message");
		downloadBtn.setBounds(10, 163, 173, 30);
		contentPane.add(downloadBtn);
		
		JButton displayBtn = new JButton("Display sent messages");
		displayBtn.setBounds(10, 216, 173, 30);
		contentPane.add(displayBtn);
		
		JButton logOffBtn = new JButton("Log Off");
		logOffBtn.setBounds(10, 339, 173, 30);
		contentPane.add(logOffBtn);
		
		lblUser = new JLabel("User:");
		lblUser.setBounds(10, 11, 65, 21);
		lblUser.setText("User: " + 1);
		contentPane.add(lblUser);
		
		lblSessionId = new JLabel("SessionId:");
		lblSessionId.setBounds(10, 47, 81, 14);
		contentPane.add(lblSessionId);
		
		
		
		setLocationRelativeTo(null);
	}
	
	public void displayUserDetails(String userName, int sessionId) {
		lblUser.setText("User: " + userName);
		lblSessionId.setText("SessionId: " + sessionId);
	}
}
