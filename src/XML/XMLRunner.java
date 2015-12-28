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
		 * ������������ ������ 2
		 * XML-������� DOM, StaX. java-����������, ����������� � XML ���� �������������� ���������
		 */

		boolean validate = XMLValidate.validate(inputXmlFile, inputXsdFile);
		if ( validate ) {
			System.out.println("��������� ������ �������");
		} else {
			System.out.println("��������� ������ �����");
			return;
		}
		
		Vegetable veg = new Vegetable("Carrot red long22", "��������22", "01.11.2115", 9200, "carrot");
		DOMParse.addVegetable(veg, inputXmlFile);
		// StAX - ������ � IDEA.
	
		/*
		 * ������������ ������ 3. �������������, ���������������.
		 */
		
		MarshallerClass marshaller = new MarshallerClass();
		
		Salad salad = new Salad();
		salad.addVegetable( new Vegetable("Carrot red long", "��������", "01.11.2015", 3200, "carrot") );
		salad.addVegetable( new Vegetable("Cucumber long", "Russia", "06.11.2015", 150, "cucumber") );
		salad.addVegetable( new Vegetable("SourCream", "Belarus", "30.11.2015", 900, "sourcream") );
		
        System.out.println( salad );
        System.out.println("marshalling...");
        marshaller.marshall( salad, "xml_marshalling.xml");
        System.out.println("unmarshalling...");
        salad = marshaller.unmarshall("xml_marshalling.xml");
        System.out.println( salad );
		
        /*
         * ������������ ������ 4.1
         * html xsl
         */
        
        // � ��������� �������
		
		//DOMParse.start(inputXmlFile);
		//StAXParse.start(inputXmlFile);
		//SAXParse.start(inputXmlFile);
	}	
}