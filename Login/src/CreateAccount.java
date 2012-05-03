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

public class CreateAccount extends JFrame implements ActionListener
{
	private static int WIDTH = 400;
	private static int HEIGHT = 150;
	private String username = "";
	private String password = "";
	
	
	public CreateAccount()
	{
		super("Please Enter New Account Information");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(getRootPane()); 
		
		JPanel mainPanel = new JPanel();
		JTextField userTextField = new JTextField();
		userTextField.setColumns(30);
		JTextField passwordField = new JPasswordField();
		passwordField.setColumns(30);
		JTextField repeatPasswordField = new JPasswordField();
		repeatPasswordField.setColumns(30);

		JButton createAccountButton = new JButton("Create Account");
		JButton cancelButton = new JButton("Cancel");
		
		mainPanel.add(userTextField, BorderLayout.CENTER);
		mainPanel.add(passwordField, BorderLayout.CENTER);
		mainPanel.add(repeatPasswordField, BorderLayout.CENTER);
		mainPanel.add(createAccountButton, BorderLayout.SOUTH);
		mainPanel.add(cancelButton, BorderLayout.SOUTH);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
	}
}
