package XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import Stock.Controller.Salad;
import Vegetables.Vegetable;
import XML.exceptions.XML_EXCEPTION_StAXException;

/**
 * StAX Parser class
 * @author DMITRY
 *
 */
public class StAXParse {
	static boolean bName = false;
	static boolean bCalories = false;
	static boolean bCountry = false;
	static boolean bDate = false;
	static boolean bType = false;

	static Vegetable vegetable;

	/**
	 * starts parsing
	 * @param inputFile
	 * @throws XML_EXCEPTION_StAXException
	 */
	public static void start(File inputFile) throws XML_EXCEPTION_StAXException {
		System.out.println("\n=== StAX Parse ===");
		Salad salad = new Salad();
		System.out.println( parseXMLAndAddVegetablesInSalad(inputFile, salad) );
		System.out.println("=== StAX Parse ===");
	}

	/**
	 * parses XML and adds vegetables to salad
	 * @param inputFile file with xml
	 * @param salad Salad
	 * @throws XML_EXCEPTION_StAXException
	 */
	public static Salad parseXMLAndAddVegetablesInSalad(File inputFile, Salad salad) throws XML_EXCEPTION_StAXException {
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader =
					factory.createXMLEventReader( new FileReader( inputFile ) );

			vegetable = new Vegetable();

			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();
				switch ( event.getEventType() ) {
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String qName = startElement.getName().getLocalPart();
					if (qName.equalsIgnoreCase("vegetable")) {

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
					break;
				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					if ( bName ) {
						String vegetableName = characters.getData();
						bName = false;
						vegetable.setName(vegetableName);
					} else if ( bCalories ) {
						bCalories = false;
						vegetable.setCalories( Integer.parseInt(characters.getData()) );
					} else if ( bCountry ) {
						bCountry = false;
						vegetable.setCountry( characters.getData() );
					} else if(bDate){
						try {
							vegetable.setDateOfManufacture( new SimpleDateFormat("dd.MM.yy").parse( characters.getData() ) );
						} catch (ParseException e) {
							e.printStackTrace();
						}
						bDate = false;
					} else if(bType) {
						bType = false;
						vegetable.setType( characters.getData() );
					}
					break;
				case  XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					if( endElement.getName().getLocalPart().equalsIgnoreCase("vegetable") ) {
						salad.addVegetable(vegetable);
						vegetable = new Vegetable();
					}
					break;
				}    
			}
		} catch (FileNotFoundException ex) {
			throw new XML_EXCEPTION_StAXException("File not found error", ex.getCause());
		} catch (XMLStreamException ex) {
			throw new XML_EXCEPTION_StAXException("XML stream error", ex.getCause());
		}

		return salad;
	}


	public static void addVegetable(Vegetable veg, File file) throws Exception {
		/*XMLInputFactory inpFactory = XMLInputFactory.newInstance();
		ArrayList<XMLEvent> events = new ArrayList<>();
		XMLEventReader reader = inpFactory.createXMLEventReader( new FileReader(file) );      
		while (reader.hasNext()) {
			events.add(reader.nextEvent());
		}
		reader.close();


		XMLOutputFactory outFactory = XMLOutputFactory.newInstance();     
		XMLEventWriter writer = outFactory.createXMLEventWriter( new FileWriter(file) );
		for (XMLEvent event : events) {
			if ( event.isEndElement() && "salad".equals(event.asEndElement().getName().getLocalPart()) ) {

				/*XMLEventFactory eventFactory = XMLEventFactory.newInstance();

				XMLEvent event_temp = eventFactory.createStartElement("", "", "document");
				writer.add(event_temp);*/
				/*writer.writeStartElement( new CharactersEvent() );

            	writer.writeStartElement("type");
            	writer.writeCharacters( veg.getType( veg.type ) );
            	writer.writeEndElement("type");

            	writer.writeStartElement("vegetable");
			}                
			writer.add(event);
		}
		writer.close();*/
	}

}
;