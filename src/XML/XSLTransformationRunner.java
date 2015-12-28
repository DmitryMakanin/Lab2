package XML;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransformationRunner {

	public static void main(String[] args) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer(new StreamSource("xsl.xsl"));
			transformer.transform(new StreamSource("xml.xml"), new StreamResult("table.html"));
			System.out.println("Transform  complete");
		} catch(TransformerException e) {
			System.err.println("Impossible transform file: " + e);
		}
	}

}
