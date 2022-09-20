import java.util.*;
import java.lang.*;

/*
 * when there is multiple methods with same name and with different parameters is called Method Overloading
 * Functions can be overloaded by change in the number of arguments or/and a change 
 * in the type of arguments.
 * 
 */


class Calculator {

    // same method name 'add' with different no of arguments,return type,order of arguments
    // method to add 2 numbers
    int add(int a, int b) {
        return a + b;
    }

    // overloaded method to add 3 numbers
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class MethodOverloading {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        System.out.println("2 No's added : "+calculator.add(5,5));
        System.out.println("3 No's added : "+calculator.add(5,5,5));

    }
}