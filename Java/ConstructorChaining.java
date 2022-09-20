class Add {
    private int addedValue = 0;

    Add(int a, int b, int c, int d) {
        this(a, b, c);
        this.addedValue += d;
    }

    Add(int a, int b, int c) {
        this(a, b);
        this.addedValue += c;
    }

    Add(int a, int b) {
        this(a);
        this.addedValue += b;
    }

    Add(int a) {
        this.addedValue += a;
    }

    public int getAddedValue() {
        return this.addedValue;
    }
}

public class ConstructorChaining {

    public static void main(String args[]) {

        Add add = new Add(10, 20, 30, 40);
        System.out.println("Added Value : " + add.getAddedValue());
    }
}
