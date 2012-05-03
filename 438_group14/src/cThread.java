import java.net.*;
import java.io.*;
import java.util.*;


//Client thread to handle a single client connection
public class cThread extends Thread
{

	String nick;      //login name (this needs to be updated to create users.
	Boolean connected;
	Socket socket;
	PrintWriter out; //I/O
	BufferedReader in;
	Socket clientSocket;
	String currentRoom;

	cThread(Socket s)
	{
		super("cThread");
		//create thread.
		connected = false;
		//blank nickname
		nick = "";

		//set up clientSocket
		//constructor
		clientSocket = s;
		try 
		{
			//create ne printwriter foroutput and input
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	//handle equals for testing if destinatino is whisper, breaks if someone has same name
	public boolean equals(cThread c)
	{
		return (c.nick.equals(this.nick));
	}

	// Try to send.
	synchronized void send(String msg)
	{
		out.println(msg);	
	}

	//Listner on socket

	void listen()
	{
		try 
		{
			//repeat always
			while (true)
			{	   			
				String msg = in.readLine();
				System.out.println(msg);	
				//login 
				if (msg.startsWith("Login"))
				{
					login(msg);
					
				}
				//logout
				else if (msg.equals("Logout"))
				{
					//if sent logout

					if (connected)
					{	
						connected = false;
						int k = server.clients.indexOf(this);
						server.clients.remove(this);
						sendList();
						out.println("OK");
						out.close();
						in.close();
						clientSocket.close();
						return;
					}
					//Failed because of not logged in and trying to close a socket
					else
					{
						send("Not Logged in !!");
					}

				}
				
				
				else if(msg.startsWith("ChangeRoom:")){
					
					this.currentRoom = msg.substring(11);
					System.out.println(this.nick + " changed rooms to " + this.currentRoom);
					this.sendRoomList();
				}
				//Post a message?

				else if (msg.startsWith("Post "))
				{
					

						//Iterate through all clients and pass out message
						for (int i = 0; i < server.clients.size() ; i ++)
						{
							cThread t = (cThread)server.clients.get(i);
							if (t.connected && t.currentRoom.equals(currentRoom))
							{
								// What: Who: message (msg starts with "Post " so skip that part)
								t.send("Recieve "+ nick+": " +msg.substring(5, msg.length()));
							}
						}
					
				}

				//Private post
				else if (msg.startsWith("PrivatePost "))
				{
					//handle tokenizer for privatepost and make sure it only goes out to the right nickname
					StringTokenizer st = new StringTokenizer(msg.substring(12,msg.length()),", ");   	

					String message = msg.substring(12, msg.lastIndexOf(", "));	        		
					String to = msg.substring(msg.indexOf(", ") + 2);
					System.out.println("Attempting to send message to: " + to);
					//make sure it worked
					boolean success = false;

					//iterate through and if name matches, then send the message
					for (int i = 0; i < server.clients.size() ; i ++)
					{
						cThread t = (cThread)server.clients.get(i);
						if (t.nick.equals(to))
						{
							t.send("PrivateReceive "+ nick+": " + message);
							success = true;
							break;
						}
					}

					//if it didn't work at this point then tell sender that the message didn't work
					if (!success)
					{
						send("Error: Couldn't send to this nickname.");
					}       		
				}
				//At this point, if no other case is true, just send the message back.. shouldn't reall do this
				else
				{
					send(msg);
				}
			}
		}
		catch (SocketException e)
		{
			//Catch broken, disconnect on break
			if (connected)
			{
				try 
				{        			
					connected = false;
					int k = server.clients.indexOf(this);
					server.clients.remove(this);
					sendList();
					out.close();
					in.close();
					clientSocket.close();
					return;
				}
				catch (Exception d)
				{
					return;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	//run the listener
	public void run() 
	{
		listen();
	}

	boolean login(String msg)
	{
		//handle login request from client	
		if (connected)
		{
			out.println("Already Connected!");
			return true;
		}
		//make sure the user doesn't already exist
		boolean exists = false;
		//System.out.println("Login" + msg.substring(5, msg.length()));
		//check all users
		for (int i = 0;i<server.clients.size();i++)
		{
			if (server.clients.get(i) != null)
			{
				//test all users for same name
				System.out.println(msg.substring(7, msg.length()));
				cThread temp = (cThread)server.clients.get(i);
				if ((temp.nick).equals(msg.substring(7, msg.length())))
				{
					exists = true;
					break;
				}

			}
		}

		//if exists then TRY AGAIIN!!!!
		if (exists)
		{
			out.println("NewNick");
		}
		else
		{
			connected = true;		
			nick = msg.substring(7,msg.length());
			currentRoom = "common";
			sendList();
			sendRoomList();
		}
		
		send("Receive " + "Joined Room: " + currentRoom);
		return true;
	}
	
	//sendRoomList()
	void sendRoomList(){

		String roomList = "";
		System.out.println(server.clients.size());
		if (server.clients.size() == 0)
		{
			return;
		}
		
		//add users to list
		for (int i = 0;i<server.clients.size();i++)
		{
			cThread temp = (cThread)server.clients.get(i); 
			if (server.clients.get(i) != null && !roomList.contains(temp.currentRoom))
			{
				if (connected)
				{
					roomList =temp.currentRoom + ", " + roomList  ;
				}
			}
		}
		
		roomList = "RoomList " + roomList.substring(0,roomList.length()-1)+";";
		
		//send out list to all
		for (int i = 0; i < server.clients.size() ; i ++)
		{
			cThread t = (cThread)server.clients.get(i);
			if (t.connected)
			{
				//t.send(list);
				t.send(roomList);
			}
		}
	}

	//Send the list of connected users to the client so that it can build the connected clients 
	void sendList()
	{
		//list of all the users
		String list = "";

		System.out.println(server.clients.size());
		if (server.clients.size() == 0)
		{
			return;
		}
		
		//add users to list
		for (int i = 0;i<server.clients.size();i++)
		{
			cThread temp = (cThread)server.clients.get(i); 
			if (server.clients.get(i) != null)
			{
				if (connected)
				{
					list =temp.nick + "," + list  ;
				}
			}
		}
		
		
		//Set up to send to client "List "
		list = "List " +list.substring(0,list.length() -1) +";";
		
		//Send List to all 

		
		//send out list to all
		for (int i = 0; i < server.clients.size() ; i ++)
		{
			cThread t = (cThread)server.clients.get(i);
			if (t.connected)
			{
				t.send(list);
				//t.send(roomList);
			}
		}
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
}