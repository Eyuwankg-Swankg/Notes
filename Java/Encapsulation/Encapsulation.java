import java.util.*;

/*
 * Encapsulation
    
 Encapsulation can be achieved by declaring all the variables in a class as private and 
 writing public getter and setter methods to get and set values of the variables.

 */

class Cars {
    // private methods are accessible only inside the class
    private String make;
    private String model;
    private String engine;
    private double cost;

    // setters method to set the value
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // getters method to get the value
    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getEngine() {
        return this.engine;
    }

    public double getCost() {
        return this.cost;
    }

}

public class Encapsulation {

    public static void main(String[] args) {

        Cars cars = new Cars();

        // setting the values by calling setters methods
        cars.setMake("Volkswagen");
        cars.setModel("POLO GTI");
        cars.setEngine("1.8 TSI");
        cars.setCost(2200000);

        // getting the values by calling getters methods
        System.out.println(" Make : " + cars.getMake() + "\n Model : " + cars.getModel() + "\n Engine Specs : "
                + cars.getEngine() + "\n Price : " + cars.getCost());
    }
}
