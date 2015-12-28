package Stock.Controller;

import java.util.Comparator;

import Vegetables.Vegetable;

public class VegetableComparatorByCalories implements Comparator<Vegetable> {

	@Override
	public int compare(Vegetable arg0, Vegetable arg1) {
		return Integer.compare( arg0.calories, arg1.calories );
	}
	

}
