import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class chatServerThread extends Thread{
	
	protected ServerSocket chatSocket = null;
	protected Socket socket = null;
	
	protected BufferedReader in = null;
	protected PrintWriter out = null;
	
	protected InetAddress address = null;
	
	public chatServerThread() throws IOException{
		this("Chat Server");
	}
	
	public chatServerThread(String name) throws IOException{
		super(name);
		chatSocket = new ServerSocket(10000);
	}

	public void run(){
		System.out.println("Chat Server Started");
		System.out.println("Chat Server is running on port: " + chatSocket.getLocalPort());
		
		for(;;){
			try {
				socket = chatSocket.accept();
				System.out.print("New Connection to Chat Server");
				new chatRoomThread(socket).run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
