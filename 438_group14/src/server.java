
import java.io.*;
import java.net.*;
import java.util.*;


//Initializes a basic server for testing.  Real server more complex.

class server
{
	//What  port?
	
	final static int SERVER_SOCKET_PORT = 9999;
	
	
	//Vector to hold all clients
	static Vector clients;
	//To socketclientSocket is single inthread
	static Socket clientSocket;
	
	
	public static void main(String args[])
	{
		//duh
		clients = new Vector();
		
		clientSocket = null;
		
		//server socket
		ServerSocket serverSocket = null;
		
		try
		{
			//port should be constant defined at top.
			serverSocket = new ServerSocket(SERVER_SOCKET_PORT);
		}
		catch(IOException e)
		{
			System.out.println("IO "+e);
		}
		
		//Leave running until disabled
		while (true)
		{
			try
			{
				//If get accept
				clientSocket = serverSocket.accept();
				//create Thread from client socket
				cThread s = new cThread(clientSocket);
				//add cThread to vector.  Start thread.
				clients.add(s);
				s.start();
			}
			catch (IOException e)
			{
				System.out.println("IOaccept "+e);
				
			}
		}	
	}
}