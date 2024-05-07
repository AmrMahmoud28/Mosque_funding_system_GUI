import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Waiting for clients...");
            int countClient = 1;

            while (true) {
                Socket incoming = serverSocket.accept();
                System.out.println("Spawning client: " + countClient);
                Thread clientThread = new Thread(new ThreadedClientHandler(incoming));
                clientThread.start();
                countClient++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

class ThreadedClientHandler implements Runnable {

    private final Socket clientSocket;

    public ThreadedClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream())){
            String clientMessage = (String) inputStream.readObject();
            
            synchronized (Server.class){
                System.out.println(clientMessage);
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

}
