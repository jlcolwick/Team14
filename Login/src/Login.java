/**
 * 
 * @author Justin Griffin
 * This class is the user interface for the project.
 * Written by Justin Griffin.
 * 0.1
 * 4/11/12
 *
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Login extends JFrame implements ActionListener
{
	private static int WIDTH = 400;
	private static int HEIGHT = 150;
	private String username = "";
	private String password = "";
	
	public static void main(String[] args)
	{
		CreateAccount account = new CreateAccount();
		account.setVisible(true);
		DBData conn = new DBData();
		if (conn.validateLogin("otter", "blah"))
		{
				System.out.println("Otter validated");
		}
		
	}
	
	public Login()
	{
		super("Please Enter Login Information");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(getRootPane()); 
		
		JPanel mainPanel = new JPanel();
		JTextField userTextField = new JTextField();
		userTextField.setColumns(30);
		JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(30);
		JButton loginButton = new JButton("Login");
		JButton createAccountButton = new JButton("Create Account");
		JButton exitButton = new JButton("Exit");
		
		mainPanel.add(userTextField, BorderLayout.CENTER);
		mainPanel.add(passwordField, BorderLayout.CENTER);
		mainPanel.add(loginButton, BorderLayout.SOUTH);
		mainPanel.add(createAccountButton, BorderLayout.SOUTH);
		mainPanel.add(exitButton, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
	}
}
