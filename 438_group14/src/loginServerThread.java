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
		System.out.println("Login Server Started");
		System.out.println("Login Server is running on port: " + loginSocket.getLocalPort());
		
		for(;;){
			try {
				socket = loginSocket.accept();
				System.out.println("New Connection to Login Server");
				new loginServiceThread(socket).run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
}
