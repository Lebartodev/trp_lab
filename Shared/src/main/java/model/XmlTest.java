package main.java.model;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XmlTest {
    public static void main(String[] args) {
        marshallExample();
    }

    public static void marshallExample() {
        try {
            MovieItem movieItem = MovieItem.newBuilder().id(1).name("name").genreId(2).year(2).budget(123).description("ass").build();
            //CategoryItem categoryItem = CategoryItem.newBuilder().id(1).name("name").build();

            File file = new File("Shared/src/main/java/model/file.xml");
            File xsd = new File("Shared/src/main/java/model/movie_schema.xsd");
            if(!file.exists()){
                file.createNewFile();
            }
            JAXBContext context = JAXBContext.newInstance(MovieItem.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(movieItem, file);

            System.out.println(validateXMLByXSD(file,xsd));
        } catch (JAXBException exception) {
            System.out.println("marshallExample threw JAXBException");
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateXMLByXSD(File xml, File xsd) throws SAXException, IOException {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
