import java.util.*;
import java.lang.*;

/*
 * Multilevel Inheritance
    
 A -> B -> C
 class B inherits class A and class C inherits class B

 */

// Parent Class of Skoda
class Cars {

    // accesible only in this package and subclasses
    protected String make;

    public Cars(String make) {
        this.make = make;
    }

    public String getString() {
        return "Make : " + this.make + "\n";
    }
}

// Parent Class to Octavia and Child Class to Cars
class Skoda extends Cars {

    protected String parentCompany;
    protected String headQuarters;
    protected int establishedYear;

    public Skoda(String make, String parentCompany, String headQuarters, int establishedYear) {
        // calls constructor of parent class along with parameters;
        super(make);
        this.parentCompany = parentCompany;
        this.headQuarters = headQuarters;
        this.establishedYear = establishedYear;
    }

    @Override
    public String getString() {

        return super.getString() +
                "Parent Companty : " + this.parentCompany + "\n" +
                "Head Quarters : " + this.headQuarters + "\n" +
                "Established Year : " + this.establishedYear + "\n";
    }
}

// Child class of Skoda
class Octavia extends Skoda {

    protected String model;
    protected String engine;
    protected double mileage;
    protected int year;
    protected int cost;

    public Octavia(int year, int cost,
            String model, String engine, double mileage, String make, String parentCompany, String headQuarters,
            int establishedYear) {
        // calls constructor of parent class along with parameters;
        super(make, parentCompany, headQuarters, establishedYear);
        this.model = model;
        this.engine = engine;
        this.mileage = mileage;
        this.cost = cost;
        this.year = year;
    }

    @Override
    public String getString() {

        return super.getString() +
                "Model : " + this.model + "\n" +
                "Year : " + this.year + "\n" +
                "Engine Specs : " + this.engine + "\n" +
                "Price : " + this.cost + "\n" +
                "Mileage(kmpl) : " + this.mileage + "\n";
    }

}

public class MultilevelInheritance {
    public static void main(String args[]) {

        Octavia octavia = new Octavia(2020, 3000000, "Octavia VRS 230", "2.0 TSI", 11.2, "SKODA", "Volkswagen Group","Mlada Boleslav, Czechia", 1895);
        System.out.println(octavia.getString());
    }
}
