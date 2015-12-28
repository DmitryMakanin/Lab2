package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidate {

    public static boolean validate(File xmlFile, File xsdFile) {
        if (xmlFile == null) {
            throw new IllegalArgumentException("Illegal value of xmlFileName");
        }

        if (xsdFile == null) {
            throw new IllegalArgumentException("Illegal value of xsdFileName");
        }

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            StreamSource xsdSources = new StreamSource( xsdFile );
            Schema schema = schemaFactory.newSchema(xsdSources);
            Validator validator = schema.newValidator();
            validator.validate( new StreamSource( xmlFile ) );
        } catch (SAXException | IOException e) {
        	e.printStackTrace();
            return false;
        }

        return true;
    }
}
