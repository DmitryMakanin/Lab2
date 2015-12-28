package XML.exceptions;


/**
 * DOM exception
 * @author DMITRY
 * @extends XML_EXCEPTION_ParseException
 */
@SuppressWarnings("serial")
public class XML_EXCEPTION_DOMException extends XML_EXCEPTION_ParseException {
	public XML_EXCEPTION_DOMException(String s, Throwable cause) {
		super(s, cause);
	}
}