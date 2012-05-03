import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class chatServerJoinTest {
	
	Scanner user;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		try {
			 Socket skt = new Socket("localHost", Integer.parseInt(user.nextLine()));
	         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
	         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
	         String msg = new String();
         	 System.out.println("Connected to Chatroom on Socket: " + skt.getPort());
	         do{
		         if(in.ready()){
		         System.out.println("Stop");
	         	 System.out.println(in.readLine());
		         }else{
		        	 System.out.println("Not Ready");
		         }
	         
	         }while(!msg.equalsIgnoreCase("Quit"));
	         System.out.println("Chat Room Closed");
	         in.close();
	         out.close();
	         skt.close();
			
		} catch (UnknownHostException e) {
			System.out.println("Server Not Found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO error");
		}
		
	}

}
