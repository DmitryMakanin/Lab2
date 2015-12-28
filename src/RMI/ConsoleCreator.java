package RMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Checker.LengthParameterChecker;
import Checker.NameParameterChecker;
import Checker.ParameterChecker;
import Vegetables.Vegetable;

/**
 * Console creator class, that creates objects
 * @author DMITRY
 *
 */
public class ConsoleCreator {
    private BufferedReader input;

    /**
     * constructor
     * @param input
     */
    public ConsoleCreator(BufferedReader input) {
        this.input = input;
    }

    /**
     * this method helps to create vegetables
     * @return new vegetable
     * @throws IOException
     */
    public Vegetable createVegetable() throws IOException {
    	System.out.println("CREATING VEGETABLE");
    	
        System.out.println("Enter vegetable's name: ");
        String name = input.readLine();
        System.out.println("Enter vegetable's country: ");
        String country = input.readLine();
        System.out.println("Enter vegetable's date (ex. dd.MM.yy): ");
        String date = input.readLine();
        System.out.println("Enter vegetable's calories: ");
        String calories = input.readLine();
        System.out.println("Enter vegetable's type: ");
        String type = input.readLine();
        
        Vegetable vegetable = new Vegetable(name, country, date, Integer.parseInt(calories), type);
        
        return vegetable;
    }

    /**
     * helps to create length checker
     * @return new ParameterChecker
     * @throws IOException
     */
	public ParameterChecker createLengthChecker() throws IOException {
		System.out.println("Enter max length of searching checker (input 'no' for not using this checker): ");
		String length = input.readLine();
		if ( length.compareTo("no") == 0 ) {
			length = "999";
		}
		String nnn = length;
		System.out.println(nnn);
		return new LengthParameterChecker( Integer.parseInt( length ) );
	}

	/**
	 * helps to create name checker
	 * @return new ParameterChecker
	 * @throws IOException
	 */
	public ParameterChecker createNameChecker() throws IOException {
		System.out.println("Enter string that vegetable name must contain (input 'no' for not using this checker): ");
		String line = input.readLine();
		if ( line == "no" ) {
			line = "";
		}
		return new NameParameterChecker( line );
	}

	/**
	 * returns list of prepared vegetables, that helps to test application
	 * @return List<Vegetable>
	 */
	public List<Vegetable> getListOfPreparedVegetables() {
		List<Vegetable> lst = new ArrayList<Vegetable>();
		
		lst.add( new Vegetable("carrot minskaya", "blr", "20.01.2015", 100, "carrot") );
		lst.add( new Vegetable("carrot vitebskaya", "blr", "20.01.2015", 105, "carrot") );
		lst.add( new Vegetable("carrot mogilevskaya", "blr", "20.01.2015", 120, "carrot") );
		lst.add( new Vegetable("carrot minskaya2", "blr", "20.01.2015", 200, "carrot") );
		lst.add( new Vegetable("carrot minskaya3", "blr", "20.01.2015", 500, "carrot") );
		lst.add( new Vegetable("carrot minskaya4456456456464564564564", "blr", "20.01.2015", 400, "carrot") );
		lst.add( new Vegetable("carrot minskaya5", "blr", "20.01.2015", 5100, "carrot") );
		lst.add( new Vegetable("carrot minskaya6", "blr", "20.01.2015", 1400, "carrot") );
		lst.add( new Vegetable("carrot minskaya7", "blr", "20.01.2015", 1500, "carrot") );
		
		return lst;
	}
}
