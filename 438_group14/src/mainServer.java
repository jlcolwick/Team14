import java.io.IOException;


public class mainServer {
    public static void main(String[] args) throws IOException {
        //new loginServerThread().start();
        new chatServerThread().start();
    }
}
