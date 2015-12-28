package Serialize;

import java.io.IOException;

import Stock.Controller.Salad;
import Vegetables.Vegetable;

/**
 * SerializeMain class
 * @author DMITRY
 *
 */
public class SerializeMain {

	/**
	 * main method that shows an example how to write and read objects from files
	 * @param args
	 */
	public static void main(String[] args) {
		Salad salad = new Salad();
		salad.addVegetable( new Vegetable("Carrot red long", "Беларусь", "01.11.2015", 3200, "carrot") );
		salad.addVegetable( new Vegetable("Cucumber long", "Russia", "06.11.2015", 150, "cucumber") );
		salad.addVegetable( new Vegetable("SourCream", "Belarus", "30.11.2015", 900, "sourcream") );
		
		salad.showVegetables();
		
		try {
			SerializeConnector.write("salad.txt", salad);
			salad.showVegetables();
		} catch ( IOException e ) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		
		try {
			Salad anotherSalad = (Salad)SerializeConnector.read("salad.txt");
			anotherSalad.showVegetables();
		} catch (IOException ex) {
			System.out.println("IOEXception in input");
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException in input");
			ex.printStackTrace();
		}
	}
}
