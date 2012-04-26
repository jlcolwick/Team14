import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class loginServerThread extends Thread{

	protected ServerSocket loginSocket = null;
	protected Socket socket = null;

	protected BufferedReader in = null;
	protected PrintWriter out = null;
	
	protected InetAddress  address = null;
	
	public loginServerThread() throws IOException{
		this("Login Server");
	}
	
	public loginServerThread(String name) throws IOException{
		super(name);
		loginSocket = new ServerSocket(9000);

	}
	
	public void run(){
		System.out.println("Server Started");
		System.out.println("Server is running on port: " + loginSocket.getLocalPort());
		
		for(;;){
			try {
				socket = loginSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
		        while(!in.ready()){};
				System.out.println(in.readLine());
				System.out.println("Sending return");
				out.println("Successful");
				out.flush();
		        in.close();
		        socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
