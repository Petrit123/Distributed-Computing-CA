package com.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Client.Client;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TMPPage extends JFrame {

	private JPanel contentPane;
	private JLabel lblUser;
	private JLabel lblSessionId;
	public JTextField textField;

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
		
		textField = new JTextField();
		textField.setBounds(189, 339, 633, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(189, 42, 631, 288);
		textArea.append("Welcome!  Enter a line to receive an echo from the server, or type exit to quit.");
		contentPane.add(textArea);		
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.setBounds(828, 339, 114, 30);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client.startClientApplication(textArea, textField.getText().toString());
				System.out.println(textField.getText().toString());
			}
		});
		setLocationRelativeTo(null);
	}
	
	public void displayUserDetails(String userName, int sessionId) {
		lblUser.setText("User: " + userName);
		lblSessionId.setText("SessionId: " + sessionId);
	}
}
