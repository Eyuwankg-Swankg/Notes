import java.lang.*;
import java.util.*;

class Copy {
    protected int a;
    protected int b;

    Copy(int a, int b) {
        this.a = a;
        this.b = b;
    }

    // Copy Constructor
    // have a paramater that is a object of the class
    Copy(Copy c) {
        this.a = c.a;
        this.b = c.b;
    }

    public int add() {
        return this.a + this.b;
    }
}

public class CopyConstructor {
    public static void main(String[] args) {

        // Insted of passing these values again we can use the copy constructor to
        // create a new
        // object with these values
        // calling the original constructor
        Copy copy = new Copy(10, 20);

        // calling the copy constructor with the copy object
        // this will create a new object 'c1' with paramter values of 'copy' object
        Copy c1 = new Copy(copy);

        // this dosent call copy constructor
        // this is just assigning the reference of 'copy' object to 'c2' object
        // it is same as 'copy' object
        Copy c2 = copy;
    }
}
