
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try (ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());) {

            String[] userData = (String[]) inputStream.readObject();

            synchronized (Server.class) {
                try {
                    String sql;
                    switch (Integer.parseInt(userData[0])) {
                        case 0:
                            sql = "SELECT * FROM admin WHERE admin_email = ? AND password = ?";
                            break;
                        case 1:
                            sql = "SELECT * FROM organization WHERE org_email = ? AND org_id = ?";
                            break;
                        default:
                            sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                            break;
                    }

                    try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28")) {
                        PreparedStatement pst = con.prepareStatement(sql);
                        
                        pst.setString(1, userData[1]);
                        pst.setString(2, userData[2]);
                        
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            String[] result = new String[4];
                            String userType = "Organization";
                            switch (Integer.parseInt(userData[0])) {
                                case 0:
                                    userType = "Admin";
                                case 1:
                                    result[0] = (rs.getString(2));
                                    result[1] = rs.getString(1);
                                    result[2] = rs.getString(3);
                                    result[3] = rs.getString(1);
                                    break;
                                default:
                                    result[0] = (rs.getString(3) + " " + rs.getString(4));
                                    result[1] = rs.getString(2);
                                    result[2] = rs.getString(6);
                                    result[3] = rs.getString(1);
                                    userType = "User";
                                    break;
                            }
                            System.out.println("  User Type: " + userType);
                            System.out.println("  User Name: " + result[0] + "\n");
                            outputStream.writeObject(result);
                        } else {
                            String[] errorMessage = {"Wrong", "Wrong Username or Password"};
                            outputStream.writeObject(errorMessage);
                        }
                    }
                } catch (HeadlessException | SQLException e) {
                    String[] errorMessage = {"Exception", e.getMessage()};
                    outputStream.writeObject(errorMessage);
                    System.out.println(e);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

}
