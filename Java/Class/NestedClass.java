import java.util.*;

/*
 * Nested Class is a class inside another class 
 * whose object is created using outer class object
 * nested class can acces the outer class varibales even private varaibles
 */

// Outer class
class Outer {

    private String brand;

    Outer(String brand) {
        this.brand = brand;
    }

    // Nested Class
    class Nested {

        private String model;
        private int year;

        Nested(String model, int year) {
            this.model = model;
            this.year = year;
        }

        // accessing outer class memebers
        // while accessing outer class memebers we dont need instance
        // since the object of inner class or nested class is created using instance of
        // outer class
        // it will get value of outer class variables from that instace
        public String getSpec() {
            return brand + " " + this.model + " " + this.year;
        }

    }

}

public class NestedClass {

    public static void main(String args[]) {

        // creating object for outer class
        Outer outer = new Outer("Mahindra");

        // using that object to create object for nested class
        // this outer instance variable will allows us to refer that instace value
        Outer.Nested nested = outer.new Nested("XUV 7oo", 2020);

        System.out.println(nested.getSpec());// Mahindra XUV 7OO 2020

        // creating object for outer class
        Outer outer1 = new Outer("Tata");

        // using that object to create object for nested class
        // this outer1 instance variable will allows us to refer that instace value
        Outer.Nested nested1 = outer1.new Nested("Safari GOLD", 2022);

        System.out.println(nested1.getSpec()); // Tata Safari GOLD

        // declaring directly instance of nested class
        Outer.Nested nested2 = new Outer("Morris Garage").new Nested("Hector PLUS", 2021);

        System.out.println(nested2.getSpec()); // Morris Garage Hector PLUS 2021
    }

}