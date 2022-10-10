package Package;

public class Cars {
    public String make;
    public String founder;
    //private memebers are not accessible across package and only accessible inside the same class
    private int year;
    //protected members are not accessible across package and accessible only inside the same package
    protected double sharePrice;

    public Cars(String make, String founder, int year, double sharePrice) {
        this.make = make;
        this.founder = founder;
        this.year = year;
        this.sharePrice = sharePrice;

    }
    // getters to get protected members
    public double getSharePrice() {
        return this.sharePrice;
    }
    // getters to get private members
    public int getYear() {
        return this.year;
    }
}
