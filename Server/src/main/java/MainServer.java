import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class MainServer {

    public static void main(String args[]) {
        try {
            DataObject dataObject = DBMethods.getModel();
            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));
            int id = 0;
            Map<Integer, Client> clientMap = new HashMap<>();

            System.out.println("server is started");

            while (true) {
                id++;
                new ServerThread(server.accept(), dataObject, id, clientMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
