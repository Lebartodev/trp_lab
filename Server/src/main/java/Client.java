import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private OutputStream oos;
    private InputStream ois;

    public Client(Socket socket, OutputStream oos, InputStream ois) {
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        return oos;
    }

    public void setOutputStream(OutputStream oos) {
        this.oos = oos;
    }

    public InputStream getInputStream() {
        return ois;
    }

    public void setInputStream(InputStream ois) {
        this.ois = ois;
    }
}
