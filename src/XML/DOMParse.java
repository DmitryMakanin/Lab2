package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import Stock.Controller.Salad;
import Vegetables.Vegetable;
import XML.exceptions.XML_EXCEPTION_DOMException;

/**
 * DOM Parser class
 * @author DMITRY
 *
 */
public class DOMParse {
	static Salad salad;
	
	/**
	 * starts DOM parsing
	 * @param inputFile
	 * @throws XML_EXCEPTION_DOMException
	 */
	public static void start(File inputFile) throws XML_EXCEPTION_DOMException {
		System.out.println("\n=== DOM Parse ===");
		Salad salad = new Salad();
		System.out.println( parseXMLAndAddVegetablesInSalad(inputFile, salad) );
		System.out.println("=== DOM Parse ===");
	}
	
	/**
	 * parses XML and adds vegetables to salad
	 * @param inputFile File
	 * @param salad Salad
	 * @return
	 * @throws XML_EXCEPTION_DOMException
	 */
	public static Salad parseXMLAndAddVegetablesInSalad(File inputFile, Salad salad) throws XML_EXCEPTION_DOMException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        
	        NodeList nList = doc.getElementsByTagName("vegetable");
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	           Node nNode = nList.item(temp);
	           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) nNode;
	              String name = eElement.getElementsByTagName("name").item(0).getTextContent();
	              String country = eElement.getElementsByTagName("country").item(0).getTextContent();
	              String date = eElement.getElementsByTagName("date").item(0).getTextContent();
	              String calories = eElement.getElementsByTagName("calories").item(0).getTextContent();
	              String type = eElement.getElementsByTagName("type").item(0).getTextContent();
	              salad.addVegetable( new Vegetable(name, country, date, Integer.parseInt(calories), type.toLowerCase() ) );
	           }
	        }
	        
		} catch (IOException ex) {
            throw new XML_EXCEPTION_DOMException("IO error", ex.getCause());
        } catch (ParserConfigurationException ex) {
            throw new XML_EXCEPTION_DOMException("Parser conf error", ex.getCause());
        } catch (SAXException ex) {
            throw new XML_EXCEPTION_DOMException("SAX error", ex.getCause());
        }
		
		return salad;
	}
	
	public static void addVegetable(Vegetable veg, File file) throws XML_EXCEPTION_DOMException {
		
		try {
			
			if ( file == null ) {
				throw new IllegalArgumentException();
			}
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(file);
		    doc.getDocumentElement().normalize();
	    
	        Element root = doc.getDocumentElement();
	        Element newElem = doc.createElement("vegetable");
	        
	        Element newType = doc.createElement("type");
	        Text newTypeValue = doc.createTextNode( veg.getType( veg.type ) );
	        newType.appendChild(newTypeValue);
	        
	        Element newName = doc.createElement("name");
	        Text newNameValue = (Text) doc.createTextNode( veg.name );
	        newName.appendChild(newNameValue);
	        
	        Element newCalories = doc.createElement("calories");
	        Text newCaloriesValue = (Text) doc.createTextNode( String.valueOf(veg.calories) );
	        newCalories.appendChild(newCaloriesValue);
	        
	        Element newCountry = doc.createElement("country");
	        Text newCountryValue = (Text) doc.createTextNode( veg.country );
	        newCountry.appendChild(newCountryValue);
	        
	        Element newDate = doc.createElement("date");
	        Text newDateValue = (Text) doc.createTextNode( veg.getDateString() );
	        newDate.appendChild(newDateValue);
	        
	        newElem.appendChild(newType);
	        newElem.appendChild(newName);
	        newElem.appendChild(newCalories);
	        newElem.appendChild(newCountry);
	        newElem.appendChild(newDate);
	        
	        root.appendChild(newElem);
	        
	        Source domSource = new DOMSource(doc); 
	        Result fileResult = new StreamResult(file);
	        TransformerFactory factory = TransformerFactory.newInstance();
	        Transformer transformer = factory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "xml");
	        transformer.transform(domSource, fileResult);
		} catch (IOException ex) {
	        throw new XML_EXCEPTION_DOMException("IO error", ex.getCause());
	    } catch (ParserConfigurationException ex) {
	        throw new XML_EXCEPTION_DOMException("Parser conf error", ex.getCause());
	    } catch (SAXException ex) {
	        throw new XML_EXCEPTION_DOMException("SAX error", ex.getCause());
	    } catch (TransformerConfigurationException ex) {
	    	throw new XML_EXCEPTION_DOMException("Transformer Configuration error", ex.getCause());
	    } catch (TransformerException ex) {
	    	throw new XML_EXCEPTION_DOMException("Transformer exception", ex.getCause());
	    }
	}
	
	
	
}
