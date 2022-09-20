class Singleton {
    static Singleton instance = null;
    public int a = 10;

    private Singleton() {
    }

    static public int  getInstace() {
        if (instance == null)
            instance = new Singleton();
        return 10;
    }
}

public class SingletonClass {

    public static void main(String args[]) {
        // we can call the instance static method inside the class and get the object
        // instace of the class
        int singleton = Singleton.getInstance();
        System.out.println(singleton);
    }

}
