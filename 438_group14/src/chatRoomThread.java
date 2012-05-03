import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class chatRoomThread extends Thread {

	Socket socket = null;
	ArrayList<Socket> others = new ArrayList<Socket>();
	BufferedReader in = null;
	PrintWriter out = null;
	String msg = null;
	
	public chatRoomThread(ServerSocket skt) throws IOException{
		this("Chat Room Thread", skt.accept());
	}
	
	public chatRoomThread(String name, Socket skt) throws IOException{
		super(name);
		socket = skt;
	}
	
	public void run(){
		System.out.println("New Chat Room Created");
		String msg = new String();
		System.out.println("Chat Room is on socket: " + socket.getLocalPort());
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			do{
	        	while(!in.ready()){}
	        	msg = in.readLine();
				out.println("Recieved: " + msg); out.flush();
			}while(!msg.equalsIgnoreCase("Quit"));
			System.out.println("Chat room closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
