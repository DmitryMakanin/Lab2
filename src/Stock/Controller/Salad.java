package Stock.Controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Checker.SaladChecker;
import Vegetables.Vegetable;

@XmlRootElement
public class Salad implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "vegetable", required = true)
    CopyOnWriteArrayList<Vegetable> salad;

    public Salad() {
        salad = new CopyOnWriteArrayList<Vegetable>();
    }

    public CopyOnWriteArrayList<Vegetable> getSalad() {
        return salad;
    }

    public void addVegetable(Vegetable vegetable) {
        salad.add(vegetable);
    }

    public void removeVegetable(Vegetable vegetable) {
        salad.remove(vegetable);
    }

    public void showVegetables() {
        System.out.println("===== SHOW ALL VEGETABLES IN SALAD ===== ");
        int indexNumber = 1;
        for (Vegetable vegetable : this.getSalad() ) {
            System.out.println(indexNumber + ". " + vegetable.name + " (from " + vegetable.country +  ", manufactured on " + getDateString(vegetable.dateOfManufacture) + ", calories " + vegetable.calories + ", type \"" + vegetable.type + "\")");
            indexNumber++;
        }
    }

    @SuppressWarnings("deprecation")
    public static String getDateString(Date date) {
        return Integer.toString(date.getDate()) + "-" + Integer.toString(date.getMonth()) + "-" + Integer.toString(date.getYear() + 1900);
    }

    public void sortByCalories() {
        System.out.println("===== Sorting by calories............. =====");
        Collections.sort(this.getSalad(), new VegetableComparatorByCalories());
    }

    public void showVegetables(SaladChecker saladCheck) {
        if (saladCheck != null) {
            for (Vegetable vegetable : this.getSalad()) {
                if (saladCheck.checkVegetable(vegetable))
                    System.out.println(vegetable.name + " (from " + vegetable.country +  ", manufactured on " + getDateString(vegetable.dateOfManufacture) + ", calories " + vegetable.calories + ", type \"" + vegetable.type + "\")");
            }
        }
    }

    @Override
    public String toString() {

        System.out.println("+==== SHOW ALL VEGETABLES IN SALAD =====");

        int indexNumber = 1;
        for (Vegetable vegetable : this.getSalad() ) {
            System.out.println(indexNumber + ". " + vegetable.name + " (from " + vegetable.country +  ", manufactured on " + getDateString(vegetable.dateOfManufacture) + ", calories " + vegetable.calories + ", type \"" + vegetable.type + "\")");
            indexNumber++;
        }

        return "+==== SHOW ALL VEGETABLES IN SALAD =====";
    }
}
