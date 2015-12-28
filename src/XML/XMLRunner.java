package XML;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import Stock.Controller.Salad;
import Vegetables.Vegetable;
import Marshall.MarshallerClass;
import XML.exceptions.XML_EXCEPTION_ParseException;
/**
 * XMLRunner
 * Runs XML parsing
 * @author DMITRY
 *
 */
public class XMLRunner {
	/**
	 * main function
	 * @param args
	 * @throws XML_EXCEPTION_ParseException
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws XML_EXCEPTION_ParseException, JAXBException, FileNotFoundException {
		File inputXmlFile = new File("xml.xml");
		File inputXsdFile = new File("xml.xsd");
		
		/*
		 * Лабораторная работа 2
		 * XML-парсеры DOM, StaX. java-приложение, выполняющее в XML файл дополнительных элементов
		 */

		boolean validate = XMLValidate.validate(inputXmlFile, inputXsdFile);
		if ( validate ) {
			System.out.println("Валидация прошла успешно");
		} else {
			System.out.println("Валидация прошла плохо");
			return;
		}
		
		Vegetable veg = new Vegetable("Carrot red long22", "Беларусь22", "01.11.2115", 9200, "carrot");
		DOMParse.addVegetable(veg, inputXmlFile);
		// StAX - парсер в IDEA.
	
		/*
		 * Лабораторная работа 3. Маршаллизация, демаршаллизация.
		 */
		
		MarshallerClass marshaller = new MarshallerClass();
		
		Salad salad = new Salad();
		salad.addVegetable( new Vegetable("Carrot red long", "Беларусь", "01.11.2015", 3200, "carrot") );
		salad.addVegetable( new Vegetable("Cucumber long", "Russia", "06.11.2015", 150, "cucumber") );
		salad.addVegetable( new Vegetable("SourCream", "Belarus", "30.11.2015", 900, "sourcream") );
		
        System.out.println( salad );
        System.out.println("marshalling...");
        marshaller.marshall( salad, "xml_marshalling.xml");
        System.out.println("unmarshalling...");
        salad = marshaller.unmarshall("xml_marshalling.xml");
        System.out.println( salad );
		
        /*
         * Лабораторная работа 4.1
         * html xsl
         */
        
        // в отдельном раннере
		
		//DOMParse.start(inputXmlFile);
		//StAXParse.start(inputXmlFile);
		//SAXParse.start(inputXmlFile);
	}	
}