import java.lang.*;
import java.util.*;
/*
    Abstract Class
    'abstract' keyword is used in class and methods 
    abstract class provides a class outline structure without the implementations with variables
    abstract classes have abstract methods
    abstract classes must be redeinfed by extends in another subclass
    There can be no object of an abstract class. That is, an abstract class can not be directly instantiated 
    with the new operator.
 */
abstract class Square {
    
    abstract public void area(double side);

    abstract public void perimeter(double side);
}

class AbstractClass extends Square {

    public void area(double side) {
        double area = side * side;
        System.out.println("Area : " + area);
    }

    public void perimeter(double side) {
        double perimeter = 4 * side;
        System.out.println("Perimeter : " + perimeter);
    }

    public static void main(String args[]) {
        
        AbstractClass abstractClass = new AbstractClass();
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Side : ");
        int side = sc.nextInt();
        abstractClass.area(side);
        abstractClass.perimeter(side);

    }

}
