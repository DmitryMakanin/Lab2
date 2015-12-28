package Stock;

import Checker.LengthParameterChecker;
import Checker.NameParameterChecker;
import Checker.ParameterChecker;
import Checker.SaladChecker;
import Stock.Controller.Salad;
import Vegetables.Vegetable;

public class Stock {

	public static void main(String[] args) {
		Salad salad = new Salad();
		salad.addVegetable( new Vegetable("Carrot red long", "Беларусь", "01.11.2015", 3200, "carrot") );
		salad.addVegetable( new Vegetable("Cucumber long", "Russia", "06.11.2015", 150, "cucumber") );
		salad.addVegetable( new Vegetable("SourCream", "Belarus", "30.11.2015", 900, "sourcream") );
		
		salad.showVegetables();
		
		salad.sortByCalories();
		salad.showVegetables();
		
		System.out.println("\n\n===== CHECKING =====");
		System.out.println("We have searched vegetables with these parameters:");
		int MAX_LENGTH = 10;
		String MUST_CONTAINING_WORD = "our";
		System.out.println("Max Name Length : " + MAX_LENGTH + "\nMust Containing Word: " + MUST_CONTAINING_WORD + "\n=They are:");
		
		ParameterChecker lengthCheck = new LengthParameterChecker( MAX_LENGTH );
		ParameterChecker nameCheck = new NameParameterChecker( MUST_CONTAINING_WORD );
		
		SaladChecker saladCheck = new SaladChecker.Builder().lengthChecker(lengthCheck).nameChecker(nameCheck).build();
		salad.showVegetables(saladCheck);
	}
}
