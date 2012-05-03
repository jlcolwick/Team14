//Server passes LIST then names of other users in room
//POST from Message
//PrivateReceive 
import java.util.*;
import javax.swing.*;


public class readFromServer extends Thread
{

	client c;

	//constructor
	readFromServer(client cc)
	{
		c = cc;
	}

	public void run()
	{
		String s;

		while (true)
		{
			//to client.logout = true;
			//handle System.exit() from other end
			if (client.logout)
			{
				return;
			}

			//Get socket
			s = client.read();
			//An ine list
			if (s.startsWith("List"))
			{

				//client.mainText.setText(client.mainText.getText() + "\n" + "Connected as " + client.nick + " to room: " + c.currentRoom + ".");
				//client.mainText.setCaretPosition(client.mainText.getText().length());
				

				//c.setTitle("Simple Java Chat - " + c.nick + " - Connected to " + c.server);

				//connected = true;

				client.connected = true;

				client.list.clear();

				String nextNick = "";

				//tokenize, and get user names
				StringTokenizer st = new StringTokenizer(s.substring(5,s.length()),", ");

				String temp = null;
				while(st.hasMoreTokens())
				{
					temp = st.nextToken();
					//build list of users
					client.list.addElement(replace(temp,";",""));
				}

				//print out names
				System.out.print("List updated: New names: ");
				for (int i = 0; i < client.list.size();i++)
				{
					System.out.print(client.list.get(i) + " ");
				}
				System.out.println();
			}
			//RECEIVE message in..
			else if (s.startsWith("Recieve"))
			{
				s = s.replaceFirst(c.currentRoom, "");
				client.mainText.setText(client.mainText.getText() + "\n" + s.substring(8,s.length()));
				//update place of cursor
				client.mainText.setCaretPosition(client.mainText.getText().length());
			}
			//This is the whisper function S starts with PrivateReceive
			else if (s.startsWith("PrivateReceive"))
			{
				client.mainText.setText(client.mainText.getText() + "\n" + "Whisper: " + s.substring(14,s.length()));
				client.mainText.setCaretPosition(client.mainText.getText().length());
			}
			//Set up new nickname..first was used
			else if (s.startsWith("NewNick"))

			{   
				client.mainText.setText("");
				String newnick =  JOptionPane.showInputDialog(null, "Someone changed their name:");
				client.connected = false;
				//enable log back in.. disable send
				client.jMenuItem1.setEnabled(true);
				client.jMenuItem2.setEnabled(false);


				if (newnick != null)
				{
					//Update nickname and relogin
					client.nick = newnick;
					client.jMenuItem1.setEnabled(false);
					client.jMenuItem2.setEnabled(true);
					client.send("Login: "+newnick);
				}
			}

			else if (s.startsWith("RoomList"))
			{
				client.roomList.clear();
				System.out.println(s);
				//tokenize, and get rooms
				StringTokenizer st = new StringTokenizer(s.substring(9,s.length()),",");

				String temp = null;
				while(st.hasMoreTokens())
				{
					
					temp = st.nextToken();
					//build list of users
					if(temp != null);
					client.roomList.addElement(replace(temp,";", ""));
				}
				
			}
		}		

	}
	//Stolen from google.  
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