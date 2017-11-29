package main.java;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MainServer {
    private Socket socket;
    private static ConcurrentLinkedQueue<AtomicInteger> lockedObjects = new ConcurrentLinkedQueue<>();
    private static Collection<Integer> counter = new ArrayList<>();

    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("server is started");

            while (true) {
                new ServerThread(server.accept(), counter);
            }
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }
}
