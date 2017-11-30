package main.java;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private Socket socket;

    public static void main(String args[]) {
        try {
            DataObject dataObject = Operations.deserealizeModel();
            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("server is started");

            while (true) {
                new ServerThread(server.accept(), dataObject);
            }
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }

}