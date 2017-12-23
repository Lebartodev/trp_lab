/*
package util;

import model.data.request.RequestShowCategories;
import model.data.request.RequestShowMovie;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static org.junit.Assert.*;

public class MarshallerUtilTest {

    @Test
    public void marshallAction() throws TransformerException {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(MarshallerUtil.marshallAction(new RequestShowMovie(),RequestShowMovie.class));
        StreamResult result = new StreamResult(System.out);
        t.transform(source, result);


    }

    @Test
    public void unmarshallAction() throws TransformerConfigurationException, JAXBException {

        RequestShowCategories  d = (RequestShowCategories) MarshallerUtil.unmarshallAction(MarshallerUtil.marshallAction(new RequestShowCategories(),RequestShowCategories.class));
    }
}*/
