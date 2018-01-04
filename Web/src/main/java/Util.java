import org.w3c.dom.Document;
import util.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Util {
    public static Document marshall(Object o, Class<?> c) {
        Document document = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.newDocument();

            marshaller.marshal(o, document);
        } catch (JAXBException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Object unmarshall(Document document,Class<?> c) {
        Object obj = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            obj = jaxbUnmarshaller.unmarshal(document);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
