package XML.exceptions;


/**
 * SAX Exception
 * @author DMITRY
 *
 */
@SuppressWarnings("serial")
public class XML_EXCEPTION_SAXException extends XML_EXCEPTION_ParseException {
	public XML_EXCEPTION_SAXException(String s, Throwable cause) {
		super(s, cause);
	}
}