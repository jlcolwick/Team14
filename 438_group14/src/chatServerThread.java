import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class chatServerThread extends Thread{
	
	protected ServerSocket chatSocket = null;
	protected ServerSocket socket = null;
	
	protected BufferedReader in = null;
	protected PrintWriter out = null;
	
	protected InetAddress address = null;
	
	public chatServerThread() throws IOException{
		this("Chat Server");
	}
	
	public chatServerThread(String name) throws IOException{
		super(name);
		chatSocket = new ServerSocket(9001);
	}

	public void run(){
		System.out.println("Chat Server Started");
		System.out.println("Chat Server is running on port: " + chatSocket.getLocalPort());
        PrintWriter out; 
        Socket skt;
		for(;;){
			try {
				System.out.println("Waiting for new connection...");
				skt = chatSocket.accept();
				socket = new ServerSocket(0);
				out = new PrintWriter(skt.getOutputStream(), true);
				System.out.println(socket.getLocalPort());
				out.println(socket.getLocalPort());
				System.out.println("New Connection to Chat Server");
				new chatRoomThread(socket).start();
				skt.close();
				skt = null;
				socket.close();
				skt = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Connection Complete");
		}
	}
}
