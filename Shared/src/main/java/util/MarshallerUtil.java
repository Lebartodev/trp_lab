package util;

import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MarshallerUtil {
    public static Document marshallAction(Object o, Class<?> c) {
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

    public static Object unmarshallAction(Document document) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
        Object obj = jaxbUnmarshaller.unmarshal(document);

        return obj;
    }
}
