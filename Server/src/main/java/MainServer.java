package main.java;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MainServer extends Thread {
    private Socket socket;
    private static ConcurrentLinkedQueue<AtomicInteger> lockedObjects = new ConcurrentLinkedQueue<>();
    private static int counter = 0;

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
}
