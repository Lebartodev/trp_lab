import model.data.request.RequestExit;
import org.w3c.dom.Document;
import util.MarshallerUtil;
import util.XmlReceiver;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class ServerThread extends Thread {

    private Socket socket;
    private DataObject dataObject;
    private int id;
    private Map<Integer, Client> clientMap;

    public ServerThread(Socket socket, DataObject dataObject, int id, Map<Integer, Client> clientMap) {
        this.socket = socket;
        this.dataObject = dataObject;
        this.id = id;
        this.clientMap = clientMap;
        this.start();
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            clientMap.put(id, new Client(socket, outputStream, inputStream));
            while (true) {
                Document document = XmlReceiver.receive(inputStream);

                Object inputAction = MarshallerUtil.unmarshallAction(document);

                if (inputAction instanceof RequestExit) {
                    Operations.serializeModel(dataObject);
                    clientMap.remove(id);
                    socket.close();
                    break;
                } else {
                    RequestHandler.handleRequest(inputAction, dataObject, outputStream, clientMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
