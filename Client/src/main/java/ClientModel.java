package main.java;


import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import main.java.base.Model;

import java.io.*;
import java.net.Socket;

public class ClientModel extends Model {
    private PublishSubject<ActionData> actionDataPublishSubject = PublishSubject.create();
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket s;

    @Override
    public PublishSubject<ActionData> getPublisher() {
        return actionDataPublishSubject;
    }

    @Override
    public Single<? super ActionData> send(ActionData command) {
        return Single.just(command).doOnSubscribe(disposable -> checkConnection())
                .map(this::sendAction)
                .map(actionData -> readAction());
    }

    private void checkConnection() {
        if (!isConnected())
            connect();
    }

    private boolean isConnected() {

        return s != null && s.isConnected();
    }

    private ActionData sendAction(ActionData actionData) throws IOException {
        out.writeObject(actionData);
        out.flush();
        return actionData;
    }

    private ActionData readAction() throws IOException, ClassNotFoundException {
        return (ActionData) in.readObject();
    }

    private void connect() {
        try {
            s = new Socket("localhost", 3128);

            out = new ObjectOutputStream(s.getOutputStream());

            in = new ObjectInputStream(s.getInputStream());
            initSubscription();

        } catch (Exception e) {
            System.out.println("init error: " + e);
        }


    }

    private void initSubscription() throws IOException, ClassNotFoundException {
        while (!s.isOutputShutdown()) {
            ActionData actionData = (ActionData) in.readObject();
            actionDataPublishSubject.onNext(actionData);
        }
    }
}
