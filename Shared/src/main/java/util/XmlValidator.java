package util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class XmlValidator {

    public static boolean validate(Document document) throws IOException {
        Schema schema = null;
        try {
            schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new Source[] {
                    new StreamSource(SchemaPath.CategorySchema.getFile()),
                    new StreamSource(SchemaPath.MovieSchema.getFile()),
                    new StreamSource(SchemaPath.RequestCreateCategorySchema.getFile()),
                    new StreamSource(SchemaPath.RequestCreateMovieSchema.getFile()),
                    new StreamSource(SchemaPath.RequestDeleteCategorySchema.getFile()),
                    new StreamSource(SchemaPath.RequestDeleteMovieSchema.getFile()),
                    new StreamSource(SchemaPath.RequestEndCategoryEditSchema.getFile()),
                    new StreamSource(SchemaPath.RequestEndMovieEditSchema.getFile()),
                    new StreamSource(SchemaPath.RequestExitSchema.getFile()),
                    new StreamSource(SchemaPath.RequestShowCategoriesSchema.getFile()),
                    new StreamSource(SchemaPath.RequestShowMovieListSchema.getFile()),
                    new StreamSource(SchemaPath.RequestShowMovieSchema.getFile()),
                    new StreamSource(SchemaPath.RequestStartCategoryEditSchema.getFile()),
                    new StreamSource(SchemaPath.RequestStartCreateMovieSchema.getFile()),
                    new StreamSource(SchemaPath.RequestStartMovieEditSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseExceptionSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseMovieEditedDataSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseShowCategoriesSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseShowMovieListSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseShowMovieSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseStartCategoryEditSchema.getFile()),
                    new StreamSource(SchemaPath.ResponseStartMovieEditSchema.getFile())
            });
        } catch (SAXException e) {
            e.printStackTrace();
        }

        Source source = new DOMSource(document); // xml
        Validator validator = schema.newValidator();
        try {
            validator.validate(source);
            return true;
        } catch (SAXException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
