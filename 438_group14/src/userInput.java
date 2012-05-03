
//parses a message on client side before allowing it to be sent.

import java.io.*;

//I/O from GUI
public class userInput extends Thread
{
	public void run()
	{
		BufferedReader kin = new BufferedReader(new InputStreamReader(System.in));		
		while(true)
		{
			//if logout?
			if (client.logout)
			{
				return;
			}
			
			//breaks though for now
			try
			{
				String command = kin.readLine();
				if (command.equals("Logout"))
				{
					client.send(command);
					
					String response = client.read();
					client.logout = true;
					return;
				}
				else
				{
					client.send(command);
				}
			}
			catch (Exception e)
			{
				
			}
		}
	}
}