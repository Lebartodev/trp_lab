package util;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlSender {

    public static void send(Document tosend, OutputStream channel) throws TransformerConfigurationException, IOException {
        XmlOutputStream out = new XmlOutputStream(channel);

        StreamResult sr = new StreamResult(out);
        DOMSource ds = new DOMSource(tosend);
        Transformer tf = TransformerFactory.newInstance().newTransformer();

        try {
            tf.transform(ds, sr);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlSender.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.send();
    }
}
