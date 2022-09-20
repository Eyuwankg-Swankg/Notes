import java.util.*;
import java.lang.*;

/*
 * when there is multiple constructors with same name and with different parameters is called Constructor Overloading
 * Constructor can be overloaded by change in the number of arguments or/and a change 
 * in the type of arguments.
 * 
 */

class Calculator {

    protected int a=2;
    protected int b=5;
    protected int c=10;
    Calculator(){
    }
    Calculator(int a,int b){
        this.a=a;
        this.b=b;
    }
    Calculator(int a,int b,int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    int add() {
        return this.a + this.b + this.c;
    }
}

public class ConstructorOverloading {
    
    public static void main(String[] args) {

        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator(10,12);
        Calculator calculator3 = new Calculator(30,40);

        System.out.println("Constructor 1 : "+calculator1.add());
        System.out.println("Constructor 2 : "+calculator2.add());
        System.out.println("Constructor 3 : "+calculator3.add());
    }

}
