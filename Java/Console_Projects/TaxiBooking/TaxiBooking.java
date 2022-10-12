import java.util.*;

class Taxi {
    // A->B->C->D->E->F
    char currentPoint = 'A';
    ArrayList<Integer> bookingId = new ArrayList<Integer>();
    ArrayList<Integer> customerId = new ArrayList<Integer>();
    ArrayList<Character> pickupPoint = new ArrayList<Character>();
    ArrayList<Character> dropPoint = new ArrayList<Character>();
    ArrayList<Integer> pickupTime = new ArrayList<Integer>();
    int totalEarnings = 0;
    int totalDistanceTravelled = 0;
    boolean isFree = true;
    int id;

    Taxi(int id) {
        this.id = id;
    }

    void assign(int bookingId, int customerId, char pickupPoint, char dropPoint, int pickupTime) {
        this.bookingId.add(bookingId);
        this.customerId.add(customerId);
        this.pickupPoint.add(pickupPoint);
        this.dropPoint.add(dropPoint);
        this.pickupTime.add(pickupTime);
        this.isFree = false;
        this.currentPoint = dropPoint;
        this.totalEarnings = (Math.abs(pickupTime - dropPoint) - 5) * 10 + 100;
        this.totalDistanceTravelled = Math.abs(pickupTime - dropPoint)*15;
    }

}

class TaxiRide extends Thread {
    Taxi t;

    TaxiRide(Taxi taxi) {
        this.t = taxi;
    }

    public void run() {

        t.isFree = false;
        try {
            System.out.println("Taxi " + t.id + " Assigned");
            Thread.sleep(3000);
            // Thread.sleep(6000000);
            t.isFree = true;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class TaxiBooking {
    static ArrayList<Taxi> taxis = new ArrayList<Taxi>();
    static int bookingID = 1;

    TaxiBooking() {
        for (int i = 1; i < 5; i++)
            taxis.add(new Taxi(i));
    }

    static void Start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Choice \n1.Book Taxi\n2.Taxi Details\n3.Taxi Status\n4.Exit");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                taxiBook();
                Start();
                return;
            case 2:
                taxiDetails();
                Start();
                return;
            case 3:
                taxiStatus();
                Start();
                return;
            case 4:
                return;
            default:
                System.out.println("Invalid Choice!");
                Start();
                return;
        }


    }
    static void assignTaxi(int customerID,char pickupPoint,char dropPoint,int pickupTime,Taxi t){

        t.assign(bookingID,customerID,pickupPoint, dropPoint, pickupTime);
        bookingID++;
        TaxiRide taxiRide=new TaxiRide(t);
        taxiRide.start();
        try {Thread.sleep(1000);}
        catch(Exception e) {System.out.println(e);}
        return;

    }
    static void taxiBook() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n Customer ID : ");
        int customerID = sc.nextInt();
        System.out.println("\n Pick Up Point : ");
        char pickupPoint = Character.toUpperCase(sc.next().charAt(0));
        System.out.println("\n Drop Point : ");
        char dropPoint = Character.toUpperCase(sc.next().charAt(0));
        System.out.println("\n Pick Up Time : ");
        int pickupTime = sc.nextInt();

        boolean rejected = true;
        Taxi assigningTaxi = taxis.get(0);
        for (Taxi t : taxis) {
            if (t.isFree && t.currentPoint == pickupPoint) {
                assignTaxi( customerID, pickupPoint, dropPoint, pickupTime,t);
                return;
            }
            if (rejected && t.isFree) {
                rejected = false;
                assigningTaxi = t;
            }
        }
        if (rejected){
            System.out.print("\n No Taxis Availiable Now");
            return;
        }

        int minimumDistance = Math.abs(assigningTaxi.currentPoint - pickupPoint);
        int minimumEarnings = assigningTaxi.totalEarnings;

        for (Taxi t : taxis) {
            if (t.isFree) {
                int tempDistance = Math.abs(t.currentPoint - pickupPoint);
                if (minimumDistance > tempDistance) {
                    minimumDistance = tempDistance;
                    minimumEarnings = t.totalEarnings;
                    assigningTaxi = t;
                } else if (minimumDistance == tempDistance) {
                    if (minimumEarnings > t.totalEarnings) {
                        minimumEarnings = t.totalEarnings;
                        assigningTaxi = t;
                    }
                }
            }
        }
        assignTaxi(customerID, pickupPoint, dropPoint, pickupTime,assigningTaxi);
    }

    static void taxiDetails() {
        System.out.println("******** Taxi Details *********\n");
        for (Taxi t : taxis) {
            System.out.println("\nID : " + t.id + "\nCurrent Point : " + t.currentPoint + "\nTotal Earnings : "
                    + t.totalEarnings + "\nAvailable : " + (t.isFree ? "Yes" : "No"));
        }
        System.out.println("\n******************************");
    }

    static void taxiStatus() {
        System.out.println("******** Taxi Status *********\n");
        for (Taxi t : taxis) {
            System.out.print(t.id);
            System.out.println(t.isFree ? " is Available" : " is Not Available");
        }
        System.out.println("\n******************************");
    }

    public static void main(String[] args) {
        TaxiBooking taxiStart = new TaxiBooking();
        taxiStart.Start();
    }

}
