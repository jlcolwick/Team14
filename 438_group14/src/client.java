import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

//Client GUI and logic


class client extends JFrame
{
	static boolean connected; 
	static boolean logout; 
	static Socket cSocket; 
	static PrintWriter out; 
	static BufferedReader in;
	static userInput uinput; 
	static readFromServer sinput; 
	static DefaultListModel list; 
	static DefaultListModel roomList;
	String currentRoom;
	DBData myDb;
	
	void run()
	{

		setTitle("Simple Java Chat - Disconnected");
		enter = false; 
		connected = false; 

		//Gui Stuff
		jScrollPane1 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		inputText = new javax.swing.JTextArea();
		sendButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainText = new javax.swing.JTextArea();
		jScrollPane3 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		//to list "NotConnected"
		list = new DefaultListModel();
		list.addElement("Not Connected");
		
		roomList = new DefaultListModel();
		

		//default list of nicknames as JList 
		nickList = new JList(list);
		jMenuBar1 = new javax.swing.JMenuBar();

		//menu stuff
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem(); //switch rooms
		jMenuItem5 = new javax.swing.JMenuItem();		//add room
		

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		inputText.setColumns(20);
		inputText.setRows(5);
		jScrollPane1.setViewportView(inputText);

		sendButton.setText("Send");


		//sendButtonActionPerformed


		sendButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendButtonActionPerformed(evt);
			}
		});


		inputText.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				inputTextKeyReleased(evt);
			}
		});


		nickList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				nickListMouseClicked(evt);
			}
		});


		inputText.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				inputTextKeyReleased(evt);
			}
		});
		mainText.setColumns(20);
		mainText.setEditable(false);
		mainText.setRows(5);
		mainText.setLineWrap(true);
		inputText.setLineWrap(true);
		jScrollPane2.setViewportView(mainText);


		jScrollPane3.setViewportView(nickList);
		// BUILD menus
		jMenu1.setText("Commands");
		jMenu2.setText("Help");
		jMenuItem1.setText("Connect");
		jMenuItem2.setText("Disconnect");
		jMenuItem3.setText("Usage");
		jMenuItem4.setText("Switch Room");
		jMenuItem5.setText("Create Room");
		jMenuItem2.setEnabled(false);
		jMenuItem4.setEnabled(false);
		jMenuItem5.setEnabled(false);
		
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed3(evt);
			}
		});
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed2(evt);
			}
		});
		
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed4(evt);
			}
		});
		
		jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed5(evt);
			}
		});
		jMenu1.add(jMenuItem1);
		jMenu1.add(jMenuItem2);
		jMenu1.add(jMenuItem4);
		jMenu1.add(jMenuItem5);
		jMenu2.add(jMenuItem3);

		jMenuBar1.add(jMenu1);
		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		make();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		mainText.setFont(new Font("Serif", Font.ITALIC, 16));
	}

	//Built from netbeans and stolen
	void make()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane3)
										.addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
										.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
								.addGap(9, 9, 9)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(sendButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
										.addContainerGap())
		);

		pack();


		setVisible(true);
	}

	//handle targeting a nickname in the list of users
	private void nickListMouseClicked(java.awt.event.MouseEvent evt)
	{
		//If you are connected and didn't target yourself
		if (connected && (!nickList.getSelectedValue().equals(nick)))
		{
			//Send a private message?
			String msg =  JOptionPane.showInputDialog(null, "What is your private message: ");
			if(msg.equals("profile")){
				String profileInfo = myDb.getUserInfo((String) nickList.getSelectedValue());
				
				if(profileInfo != null){
					String username = profileInfo.substring(0, profileInfo.indexOf(":"));
					
					profileInfo = profileInfo.substring(profileInfo.indexOf(":")+1);
					String password = profileInfo;
					client.mainText.setText(client.mainText.getText() + "\n" + "Username: "+ username + " Password: " + password);
					client.mainText.setCaretPosition(client.mainText.getText().length());
				}
				
			}
			else if (msg != null)
			{
				//Make sure the PRIVATEPOST here matches!!
				
				
				send("PrivatePost " + msg + ", "+nickList.getSelectedValue());
			}
			//debugging output
			System.out.println(nickList.getSelectedValue());
		}
	}

	static boolean enter;  //handle enter 

	private void inputTextKeyReleased(java.awt.event.KeyEvent evt) 
	{
		//handle return and change enter to false so as not to send over and over 
		if(evt.getKeyCode() == 10)
		{
			if (enter)
			{
				//send it!!
				sendInput();
				enter = false;
			}
			else
			{

				enter = true;
			}
		}
	}
	private void jMenuItem1ActionPerformed2(java.awt.event.ActionEvent evt) 
	{
		//handler logout
		send("Logout");
		client.logout = true;
		System.exit(0);
	}

	//information pane
	//Actions - Disconnect
	private void jMenuItem1ActionPerformed3(java.awt.event.ActionEvent evt) 
	{
		String s;
		s = "Something helpful should be here.";
		JOptionPane.showMessageDialog( this, s,"Usage", JOptionPane.INFORMATION_MESSAGE );
	}

	static String server;
	//lick sto Actions - Connect
	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) 
	{

		setTitle("Connecting ...");
		logout = false;

		//to thread me to server
		sinput = new readFromServer(this);
		//create my parser

		//create thread for userinput and logging out
		uinput = new userInput();

		//Items
		cSocket = null;
		out = null;
		in = null;

		//Debugging errors
		boolean error;
		error = false;

		//Default server to local host for testing

		server = JOptionPane.showInputDialog("Pick your server!: ", "localhost");
		try 
		{
			//Initialize
			cSocket = new Socket(server,9999);
			//Initialize
			out = new PrintWriter(cSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			//start threads
			uinput.start();
			sinput.start();
		}
		catch(UnknownHostException e)
		{
			//handle unknown host
			JOptionPane.showMessageDialog( this, "Unable to connect to server!","ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println("Host Error" + e);
			setTitle("Simple Java Chat - Cannot Connect Please Try another server");
			error = true;
		}
		catch (IOException e)
		{
			System.out.println("IOException" + e);
		}
		if (!error)
		{
			//set up nickanme now
			nick = null;
			nick = JOptionPane.showInputDialog(null, "NickName: ");


			//Nickname can't contain ; (it is used to separate others
			while(nick.contains(";"))
			{
				nick = JOptionPane.showInputDialog(null, "You cannot put a \";\" in your nickname!");
			}

			//set up password 

			String password;
			password = null;
			currentRoom = "common";
			password = JOptionPane.showInputDialog(null, "Password: ");

			//Nickname can't contain ; (it is used to separate others
			while(password.contains(";"))
			{
				password = JOptionPane.showInputDialog(null, "You cannot put a \";\" in your password!");
			}
			
			myDb = new DBData();
			
			
			
			//if valid login then do it.
			if(myDb.validateLogin(nick,password))
				send("Login: "+nick);
			else {
				JOptionPane.showMessageDialog(null, "Invalid Username Or Password");
				nick = null;
			}
			
			
			
			//Disable connect and enable logout
			if (nick != null)
			{
				jMenuItem1.setEnabled(false);
				jMenuItem2.setEnabled(true);  
				jMenuItem4.setEnabled(true);
				jMenuItem5.setEnabled(true);
			}
			setTitle("Simple Java Chat - " + nick + " - Connected to " + server + " in room " + this.currentRoom);
		}
	}
	
	//handle switch rooms
	private void jMenuItem1ActionPerformed4(java.awt.event.ActionEvent evt) 
	{
	
		if(roomList.isEmpty())
			System.out.println("No Rooms!");
		Object[] possibilities = roomList.toArray();
		String s = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Pick a room to join",
		                    "Room Change",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    this.currentRoom);
		
		if ( s != null){
			this.currentRoom = s;
			send("ChangeRoom:" + s);
			client.mainText.setText(client.mainText.getText() + "\n" + "You changed rooms to " + this.currentRoom + ".");
			client.mainText.setCaretPosition(client.mainText.getText().length());
			setTitle("Simple Java Chat - " + nick + " - Connected to " + server + " in room " + this.currentRoom);

		}
		else
			return;
	}
	
	//handle make room
	private void jMenuItem1ActionPerformed5(java.awt.event.ActionEvent evt) 
	{
		String newRoomName = "";
		
		newRoomName = JOptionPane.showInputDialog(null, "Enter New Room Name: ");
		
		if(newRoomName == null)
			return;
		
		String msg = "ChangeRoom:" + newRoomName;
		this.currentRoom = newRoomName;
		send(msg);
		client.mainText.setText(client.mainText.getText() + "\n" + "You created a new room: " + this.currentRoom + ".");
		client.mainText.setCaretPosition(client.mainText.getText().length());
		setTitle("Simple Java Chat - " + nick + " - Connected to " + server + " in room " + this.currentRoom);

		
	}
	

	//If clicked send
	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) 
	{
		sendInput();	    
	}

	//send a message
	static void send(String msg)
	{
		out.println(msg);
	}
	//Handle input
	static String read()
	{

		String s = null;
		try 
		{
			//handle incoming
			s = in.readLine();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return s;
	}


	//stolen from google
	static String replace(String str, String pattern, String replace) 
	{
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = str.indexOf(pattern, s)) >= 0) 
		{
			result.append(str.substring(s, e));
			result.append(replace);
			s = e+pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}


	//Try to send button clicked
	void sendInput()
	{
		//handle send when not connected.. this shouldn't be possible.
		if (!connected)
		{
			JOptionPane.showMessageDialog( this, "Not connected! Actions - Connect","Error", JOptionPane.ERROR_MESSAGE );
			inputText.setText("");
		}
		// handle send with invalid input
		else if(inputText.getText().equals("") || inputText.getText().equals("\n") ||  inputText.getText()== null  )
		{
			inputText.setText("");
		}
		else
		{
			//Post is command to resend out to all participants
			send("Post " + currentRoom + replace(inputText.getText(),"\n"," "));
			inputText.setText("");
		}
	}
	//netbeans created.
	public static String nick;
	private javax.swing.JTextArea inputText;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	static public javax.swing.JMenuItem jMenuItem1;
	static public javax.swing.JMenuItem jMenuItem2;
	static public javax.swing.JMenuItem jMenuItem3;
	static public javax.swing.JMenuItem jMenuItem4; // get chat room list
	static public javax.swing.JMenuItem jMenuItem5; // create chat room and join it
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	public static javax.swing.JTextArea mainText;
	private javax.swing.JList nickList;
	private javax.swing.JButton sendButton;

}