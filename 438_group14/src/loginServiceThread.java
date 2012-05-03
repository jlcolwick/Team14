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
			String [] query = new String[2];
        	while(!in.ready()){};
        	query = in.readLine().split(":");
			System.out.println(query[0] + " " + query[1]);
			System.out.println("Sending return");
			out.println(new DBData().validateLogin(query[0], query[1]));
			out.flush();
        	in.close();
        	socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		}
		
}
