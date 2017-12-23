import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainServer {

    public static void main(String args[]) {
        try {
            DataObject dataObject = Operations.deserealizeModel();
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
            System.out.println("init error: " + e);
        }
    }

}
