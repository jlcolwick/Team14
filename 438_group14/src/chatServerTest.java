import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class chatServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			 Socket skt = new Socket("localHost", 10000);
	         System.out.print("Server has connected!\n");
	         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
	         while(!in.ready()){}
	         System.out.println(in.readLine());
	         in.close();
	         //out.close();
	         skt.close();
			
		} catch (UnknownHostException e) {
			System.out.println("Server Not Found");
		} catch (IOException e) {
			System.out.println("IO error");
		}
		
	}

}
