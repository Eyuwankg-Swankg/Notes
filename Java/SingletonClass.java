class Singleton {
    private static Singleton instance = null;
    public int a = 10;

    // private constructor cant be accesses outside the class
    private Singleton() {
    }

    // to get instace or object reference 
    // declaring as satic so that it can be accessed using class name
    static public Singleton getInstace() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }
}

public class SingletonClass {

    public static void main(String args[]) {
        // we can call the instance static method inside the class and get the object
        // instace of the class
        Singleton singleton = Singleton.getInstace();
        System.out.println(singleton.a);
    }

}
