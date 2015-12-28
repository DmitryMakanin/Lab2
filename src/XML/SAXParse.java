package XML;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Stock.Controller.Salad;
import Vegetables.Vegetable;
import XML.exceptions.XML_EXCEPTION_SAXException;

/**
 * SAX Parser class
 * @author DMITRY
 *
 */

public class SAXParse {
	 static Salad salad;
	 
	 /**
	  * starts parsing
	  * @param inputFile
	  * @throws XML_EXCEPTION_SAXException
	  */
	 public static void start(File inputFile) throws XML_EXCEPTION_SAXException {
		System.out.println("\n=== SAX Parse ===");
		salad = new Salad();
		parse(inputFile);
		
		System.out.println("=== SAX Parse ===");
	 }
	 
	 /**
	  * 
	  * @param inputFile
	  * @throws XML_EXCEPTION_SAXException
	  */
	 public static void parse(File inputFile) throws XML_EXCEPTION_SAXException  {
		 try {
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
			 salad = new Salad();
			 UserHandler userhandler = new UserHandler(salad);
			 saxParser.parse(inputFile, userhandler);
			 System.out.println(salad);
		 } catch (ParserConfigurationException ex) {
			 throw new XML_EXCEPTION_SAXException("Parser conf error", ex.getCause());
		 } catch (SAXException ex) {
			 throw new XML_EXCEPTION_SAXException("SAX error", ex.getCause());
		 } catch (IOException ex) {
			 throw new XML_EXCEPTION_SAXException("IO error", ex.getCause());
		 }
	 }
	
}
/**
 * Handler for SAXParser
 * @author DMITRY
 *
 */
class UserHandler extends DefaultHandler {
	static boolean bName = false;
	static boolean bCalories = false;
	static boolean bCountry = false;
	static boolean bDate = false;
	static boolean bType = false;
	
	Vegetable vegetable;	
	Salad salad;
	/**
	 * constructs handler
	 * @param salad
	 */
	public UserHandler(Salad salad) {
		super();
		this.salad = salad;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("vegetable")) {
			vegetable = new Vegetable();  
		} else if (qName.equalsIgnoreCase("name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("calories")) {
			bCalories = true;
		} else if (qName.equalsIgnoreCase("country")) {
			bCountry = true;
		} else if (qName.equalsIgnoreCase("date")) {
			bDate = true;
		} else if (qName.equalsIgnoreCase("type")) {
			bType = true;
		}
	}

	   @Override
	   public void endElement(String uri, String localName, String qName) throws SAXException {
	      if (qName.equalsIgnoreCase("vegetable")) {
	         salad.addVegetable( vegetable );
	      }
	   }

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bName) {
			vegetable.setName( new String(ch, start, length) );
			bName = false;
		} else if (bCalories) {
			vegetable.setCalories( Integer.parseInt( new String(ch, start, length) ) );
			bCalories = false;
		} else if (bCountry) {
			vegetable.setCountry( new String(ch, start, length) );
			bCountry = false;
		} else if (bDate) {
			try {
				vegetable.setDateOfManufacture( new SimpleDateFormat("dd.MM.yy").parse( new String(ch, start, length) ) );
			} catch (ParseException e) {
				e.printStackTrace();
			}
			bDate = false;
		} else if (bType) {
			vegetable.setType( new String(ch, start, length) );
			bType = false;
		}
	}
}
