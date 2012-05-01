import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class chatRoomThread extends Thread {

	Socket socket = null;
	ArrayList<Socket> others = new ArrayList<Socket>();
	BufferedReader in = null;
	PrintWriter out = null;
	String msg = null;
	
	public chatRoomThread(Socket skt) throws IOException{
		this("Chat Room Thread", skt);
	}
	
	public chatRoomThread(String name, Socket skt) throws IOException{
		super(name);
		socket = skt;
	}
	
	public void run(){
		System.out.print("New Chat Room Created");
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			for(;;){
				if(in.readLine()!= null){
					out.println(in.readLine());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
