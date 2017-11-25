import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MainServer extends Thread {
    Socket socket;
    private static ConcurrentLinkedQueue<AtomicInteger> lockedObjects = new ConcurrentLinkedQueue<>();

    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("server is started");

            while (true) {
                new MainServer(server.accept());
            }
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }

    public MainServer(Socket socket) {
        this.socket = socket;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Object inputAction = inputStream.readObject();


            }

            socket.close();
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }
}
