import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class chatServerTest {
	
	Scanner user;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		try {
			 Socket skt = new Socket("localHost", 9001);
	         System.out.print("Server has connected!\n");
	         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
	         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
	         String msg = new String();
	         msg = in.readLine();
         	 skt = new Socket("localHost", Integer.parseInt(msg));
         	 System.out.println("Connected to Chatroom on Socket: " + skt.getPort());
	         out = new PrintWriter(skt.getOutputStream(), true);
	         in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
	         do{
	        	 System.out.print("Type message here: ");
	        	 msg = user.nextLine();
		         out.println(msg);
		         System.out.print("Sending string: '" + msg + "'\n");
		         while(!in.ready()){}
	         	 System.out.println(in.readLine());
	         
	         }while(!msg.equalsIgnoreCase("Quit"));
	         System.out.println("Chat Room Closed");
	         in.close();
	         out.close();
	         skt.close();
			
		} catch (UnknownHostException e) {
			System.out.println("Server Not Found");
		} catch (IOException e) {
			System.out.println("IO error");
		}
		
	}

}
