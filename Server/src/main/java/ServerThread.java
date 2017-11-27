package main.java;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread  {

    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            /*ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                ActionData inputAction = (ActionData) inputStream.readObject();

                ActionData outputAction = RequestHandler.handleRequest(inputAction);

                outputStream.writeObject(outputAction);

            }*/


            socket.close();
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }
}
