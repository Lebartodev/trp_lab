package main.java;

import main.java.model.data.request.RequestExit;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private DataObject dataObject;

    public ServerThread(Socket socket, DataObject dataObject) {
        this.socket = socket;
        this.dataObject = dataObject;
        this.start();
    }

    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                ActionData inputAction = (ActionData) inputStream.readObject();

                if (inputAction instanceof RequestExit) {
                    Operations.serializeModel(dataObject);
                    socket.close();
                }

                ActionData outputAction = RequestHandler.handleRequest(inputAction, dataObject);

                outputStream.writeObject(outputAction);
                outputStream.flush();

            }
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }
}
