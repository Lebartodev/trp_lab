
import base.Model;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import util.MarshallerUtil;
import util.XmlReceiver;
import util.XmlSender;
import util.XmlValidator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.net.Socket;

public class ClientModel extends Model {
    private PublishSubject<Object> actionDataPublishSubject = PublishSubject.create();
    private Socket s;

    @Override
    public PublishSubject<Object> getPublisher() {
        return actionDataPublishSubject;
    }

    ClientModel() {
        checkConnection();
    }

    @Override
    public Single<? super Object> send(Document command) {
        return Single.just(command).doOnSubscribe(disposable -> checkConnection())
                .map(this::sendAction)
                .flatMap(actionData -> readAction());
    }

    private void checkConnection() {
        if (!isConnected())
            connect();
    }

    private boolean isConnected() {

        return s != null && s.isConnected();
    }

    private Document sendAction(Document actionData) {
        try {
            XmlSender.send(actionData, s.getOutputStream());
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actionData;
    }

    private Single<Object> readAction() {
        return Single.create(singleEmitter -> getPublisher().subscribe(actionData -> singleEmitter.onSuccess(actionData)));
    }

    private void connect() {
        try {
            s = new Socket("localhost", 3128);

            initSubscription();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initSubscription() {
        new Thread(() -> {
            while (true) {
                try {
                    Document actionData = XmlReceiver.receive(s.getInputStream());
                    if (XmlValidator.validate(actionData)) {
                        Object inputAction = MarshallerUtil.unmarshallAction(actionData);
                        actionDataPublishSubject.onNext(inputAction);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerConfigurationException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
