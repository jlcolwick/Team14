import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class loginServiceThread extends Thread{

	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	
	public loginServiceThread(Socket skt) throws IOException{
		this("Login Serice", skt);
	}
	
	public loginServiceThread(String name, Socket skt) throws IOException{
		super(name);
		socket = skt;
	}
	
	public void run(){
		try {
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
