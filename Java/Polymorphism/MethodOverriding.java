import java.util.*;
import java.lang.*;

/*
 * When a class inherts another class and uses the same method name present in parent it is called method overriding
 * when a method is overridien we have to use keyword '@Override' and mention in the inherited class where the same method name is used
 * we can call the method in parent class using 'super' keyword -> super.methodName()
 * 
 * methodoveriding only works with type of function defiition like access modifier,name,return type,no of arguments and its types and order
 *  
 */

class Calculator {

    public int add(int a, int b) {

        return a + b;
    }

}

class AddNumbers extends Calculator {

    /* Override methods should have same signature,name,return type,arguments */
    @Override
    public int add(int a, int b) {
        return super.add(a, b) + 30;
    }

    /*
     * This is method overloading, since its arguments changed and dosent have same
     * signature as parent
     * this does not override the parent class method
     */
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

class MethodOverriding {

    public static void main(String[] args) {

        AddNumbers add = new AddNumbers();
        // This calls override method
        System.out.println("Override Method : " + add.add(10, 20));
        // This calls overloaded method
        System.out.println("Overloaded Method : " + add.add(100, 200, 1000));
    }
}