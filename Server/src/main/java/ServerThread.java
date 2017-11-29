package main.java;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

public class ServerThread extends Thread  {

    private Socket socket;
    private Collection<Integer> counter;

    public ServerThread(Socket socket, Collection<Integer> counter){
        this.socket = socket;
        this.counter = counter;
        this.start();
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
            incCounter(counter);
            System.out.println(counter.size());
            Thread.sleep(10000);
            System.out.println(counter.size());
            socket.close();
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }

    public synchronized void incCounter(Collection<Integer> counter){
        counter.add(counter.size());
    }
}
