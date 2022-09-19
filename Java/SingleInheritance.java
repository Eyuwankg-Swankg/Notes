import java.util.*;
import java.lang.*;

/*
 * Single Inheritance
 * A class have only one parent class
 */

// parent or base class
class Cars {

    // accesible only in this package and subclasses
    protected String parentCompany;
    protected String make;
    protected String headQuarters;
    protected int establishedYear;

    public Cars(String make, String parentCompany, String headQuarters, int establishedYear) {
        this.make = make;
        this.headQuarters = headQuarters;
        this.establishedYear = establishedYear;
        this.parentCompany = parentCompany;
    }

    public String getString() {
        return this.make + " " + this.headQuarters + " " + this.establishedYear + " " + this.parentCompany + "\n";
    }

}

// child or inheritaed class
class Skoda extends Cars {

    protected String model;
    protected String engine;
    protected double mileage;
    protected int year;
    protected int cost;

    public Skoda(String make, String parentCompany, String headQuarters, int establishedYear, int year, int cost,
            String model, String engine, double mileage) {
        // calls constructor of parent class along with parameters;
        super(make, parentCompany, headQuarters, establishedYear);
        this.model = model;
        this.engine = engine;
        this.mileage = mileage;
        this.cost = cost;
    }

    // method overriding - runtime polymorphism
    @Override
    public String getString() {
        // caling parent method
        return super.getString() + this.model +" "+ this.engine+" "+ this.cost+" "+ this.mileage;
    }

}

class SingleInheritance {

    public static void main(String args[]) {

        Skoda skoda = new Skoda("SKODA", "Volkswagen group", "Mlada Boleslav, Czechia", 1895, 2020, 3000000,
                "Octavia VRS 230", "2.0 TSI", 11.2);
        System.out.println(skoda.getString());
    }
}
