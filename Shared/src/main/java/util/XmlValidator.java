package util;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;

public class XmlValidator {

    public static boolean validate(Document document) {

        for (SchemaPath schemaPath : SchemaPath.values()) {
            try {
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                        .newSchema(schemaPath.getFile())
                        .newValidator()
                        .validate(new DOMSource(document));
                return true;
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }
        return false;

    }

}
