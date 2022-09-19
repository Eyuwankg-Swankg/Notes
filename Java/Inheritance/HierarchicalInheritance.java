package Inheritance;
import java.util.*;
import java.lang.*;

/*
 * Hierarchical Inheritance
    
 Class Cars is the parent of Class Skoda and BMW

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
        return "Make : " + this.make + "\n" + "Parent Companty : " + this.parentCompany + "\n" +
                "Head Quarters : " + this.headQuarters + "\n" +
                "Established Year : " + this.establishedYear + "\n";
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
        return super.getString() +
                "Model : " + this.model + "\n" +
                "Year : " + this.year + "\n" +
                "Engine Specs : " + this.engine + "\n" +
                "Price : " + this.cost + "\n" +
                "Mileage(kmpl) : " + this.mileage + "\n";
    }

}

// child or inheritaed class
class MINI extends Cars {

    protected String model;
    protected String engine;
    protected double mileage;
    protected int year;
    protected int cost;

    public MINI(String make, String parentCompany, String headQuarters, int establishedYear, int year, int cost,
            String model, String engine, double mileage) {
        // calls constructor of parent class along with parameters;
        super(make, parentCompany, headQuarters, establishedYear);
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.mileage = mileage;
        this.cost = cost;
    }

    // method overriding - runtime polymorphism
    @Override
    public String getString() {
        // caling parent method
        return super.getString() +
                "Model : " + this.model + "\n" +
                "Year : " + this.year + "\n" +
                "Engine Specs : " + this.engine + "\n" +
                "Price : " + this.cost + "\n" +
                "Mileage(kmpl) : " + this.mileage + "\n";
    }

}

class HierarchicalInheritance {

    public static void main(String args[]) {
        Skoda skoda = new Skoda("SKODA", "Volkswagen group", "Mlada Boleslav, Czechia", 1895, 2020, 3000000,
                "Octavia VRS 230", "2.0 TSI", 11.2);

        MINI mini = new MINI("MINI", "The BMW Group", " BMW's Plant Oxford in Cowley, England", 1969, 2020, 4200000,
                "COUNTRYMAN", "2.0", 14.3);

        System.out.println(skoda.getString());
        System.out.println(mini.getString());

    }
}
