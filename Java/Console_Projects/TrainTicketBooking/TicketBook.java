import java.util.*;

class Ticket {
    char fromStation;
    char toStation;
    int PNRNumber;
    int noOfPassengers;
    static int PNR = 100;
    boolean isCancelled;
    boolean isWaitingList;
    int[][] seatMap;

    Ticket(char fromStation, char toStation, int[][] seatMap, int noOfPassengers, boolean isWaitingList) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.noOfPassengers = noOfPassengers;
        this.seatMap = seatMap;
        this.PNRNumber = PNR++;
        this.isWaitingList = isWaitingList;
        this.isCancelled = false;
    }

    void viewTicket() {
        System.out.println("\n*********** Your Ticket *********");
        System.out.println(" PNR Number : " + this.PNRNumber);
        System.out.println(" Ticket Confirmed : " + (this.isWaitingList ? "No" : "Yes"));
        System.out.println(" From : " + Character.toUpperCase(this.fromStation));
        System.out.println(" To : " + Character.toUpperCase(this.toStation));
        System.out.println(" No fo Passengers : " + this.noOfPassengers);
        System.out.println(" Ticket Cancelled : " + (this.isCancelled ? "Yes" : "No"));
        if (this.isWaitingList == false) {
            System.out.print(" Seat No : ");
            for (int i = 0; i <= this.toStation - this.fromStation; i++) {
                for (int j = 0; j < this.noOfPassengers; j++) {
                    System.out.print(seatMap[i][j]);
                    System.out.print((char) (this.fromStation + i));
                    if (i == this.toStation - this.fromStation && j == this.noOfPassengers)
                        System.out.println();
                    else
                        System.out.print(",");
                }
            }
            System.out.println("\n*****************************");

        }
    }

}

public class TicketBook {
    static Scanner scanner;
    static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    static ArrayList<Ticket> waitingList = new ArrayList<Ticket>();
    static boolean[][] occupancyChart = new boolean[8][10];

    static void init() {
        scanner = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++)
                occupancyChart[i][j] = false;
        }
    }

    static void printOccupancy() {

        System.out.println("\n  Occupancy Chart");
        System.out.println("\n  A B C D E ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < 10; j += 2) {
                System.out.print((occupancyChart[i][j] || occupancyChart[i][j + 1]) ? " B" : " -");
            }
            System.out.println("\n");
        }
        System.out.println("\n -------------------------");
    }

    static int getIndex(char station) {
        switch (station) {
            case 'a':
                return 0;
            case 'b':
                return 2;
            case 'c':
                return 4;
            case 'd':
                return 6;
            case 'e':
                return 8;
        }
        return 0;
    }

    static void viewTicket() {

        System.out.print("Enter PNR No : ");

        int pnrNo = scanner.nextInt();

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).PNRNumber == pnrNo) {
                tickets.get(i).viewTicket();
                return;
            }
        }

        for (int i = 0; i < waitingList.size(); i++) {
            if (waitingList.get(i).PNRNumber == pnrNo) {
                waitingList.get(i).viewTicket();
                return;
            }
        }

        System.out.println("\n Invalid PNR No");
        viewTicket();
    }

    static boolean checkWaitingListAvailability() {
        if (waitingList.size() > 1) {
            return false;
        }
        return true;
    }

    static boolean checkSeatAvailability(char fromStation, char toStation, int noOfPassengers) {

        for (int i = fromStation - 97; i <= toStation - 97; i++) {
            int availableSeats = 0;

            for (int j = 0; j < 8; j++) {

                if (i == (fromStation - 97)) {
                    int tempIndex = getIndex(fromStation) + 1;
                    if (occupancyChart[j][tempIndex] == false)
                        availableSeats++;
                } else if (i == toStation - 97) {
                    int tempIndex = getIndex(toStation);
                    if (occupancyChart[j][tempIndex] == false)
                        availableSeats++;
                } else {
                    int tempIndex = getIndex((char) (i + 97));
                    if (occupancyChart[j][tempIndex] == false && occupancyChart[j][tempIndex + 1] == false)
                        availableSeats++;
                }
            }
            if (noOfPassengers > availableSeats)
                return false;
        }
        return true;
    }

    static int[][] assignSeats(char fromStation, char toStation, int noOfPassengers) {

        int[][] seatMap = new int[toStation - fromStation + 1][noOfPassengers];
        int fromIndex = getIndex(fromStation) + 1, toIndex = getIndex(toStation);
        int index = 0;
        for (int i = fromStation - 97; i <= toStation - 97; i++, index++) {
            int bookedSeatCount = 0;
            for (int j = 0; j < 8; j++) {

                if (i == (fromStation - 97)) {
                    if (occupancyChart[j][fromIndex] == false) {
                        occupancyChart[j][fromIndex] = true;
                        try {
                            seatMap[index][bookedSeatCount] = j + 1;
                        } catch (Exception e) {
                            System.out.println(i + " :");
                        }
                        bookedSeatCount++;
                    }
                } else if (i == (toStation - 97)) {
                    if (occupancyChart[j][toIndex] == false) {
                        occupancyChart[j][toIndex] = true;
                        seatMap[index][bookedSeatCount] = j + 1;
                        bookedSeatCount++;
                    }
                } else {
                    int tempIndex = getIndex((char) (i + 97));
                    if (occupancyChart[j][tempIndex] == false && occupancyChart[j][tempIndex + 1] == false) {
                        occupancyChart[j][tempIndex] = true;
                        occupancyChart[j][tempIndex + 1] = true;
                        seatMap[index][bookedSeatCount] = j + 1;
                        bookedSeatCount++;
                    }
                }
                if (bookedSeatCount == noOfPassengers)
                    break;
            }
        }
        return seatMap;
    }

    static void bookTicket() {

        System.out.print("Enter From Station : ");
        char fromStation = Character.toLowerCase(scanner.next().charAt(0));
        System.out.print("Enter To Station : ");
        char toStation = Character.toLowerCase(scanner.next().charAt(0));
        System.out.print("Enter No of Passengers (<9) : ");
        int noOfPassengers = scanner.nextInt();
        int[][] seatMap = new int[toStation - fromStation + 1][noOfPassengers];
        boolean isAvailable = checkSeatAvailability(fromStation, toStation, noOfPassengers);
        if (isAvailable) {
            seatMap = assignSeats(fromStation, toStation, noOfPassengers);
            tickets.add(
                    new Ticket(fromStation, toStation, seatMap, noOfPassengers, false));
            System.out.println("\nTicket Booked Successfully!!! ");
        } else {
            System.out.println("\nNo Seats Available");
            boolean isWaitingAvailable = checkWaitingListAvailability();
            if (isWaitingAvailable) {
                waitingList.add(
                        new Ticket(fromStation, toStation, seatMap, noOfPassengers, true));
                System.out.println("\nPNR No : " + waitingList.get(waitingList.size() - 1).PNRNumber);
                System.out.println("\nAdded to Waiting List");
            } else {
                System.out.println("\nWaiting List is Full");
            }
        }
    }
    
    static void cancelTicket() {

        System.out.print("Enter PNR No : ");

        int pnrNo = scanner.nextInt();
        boolean inValidPNR = true;
        Ticket cancellationTicket = tickets.get(0);

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).PNRNumber == pnrNo) {
                cancellationTicket = tickets.get(i);
                inValidPNR = false;
            }
        }
        if (inValidPNR) {
            System.out.println("\n Invalid PNR No");
            cancelTicket();
        } else {
            // for (int i = cancellationTicket.fromStation - 65; i <= cancellationTicket.toStation - 65; i++) {
            //     for (int j = 0; j < cancellationTicket.seatNo.length; j++) {
            //         occupancyChart[cancellationTicket.seatNo[j] - 1][i] = false;
            //     }
            // }
            // cancellationTicket.isCancelled = true;
            // System.out.println(pnrNo + " Ticket Cancelled !!!");

            // checkWaitingList();
        }

    }

    static void getChoice() {
        System.out.println("\n1.Book Ticket\n2.Cancel Ticket\n3.Print Occupancy Chart\n4.View Ticket\n5.Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                bookTicket();
                getChoice();
                break;
            case 2:
            cancelTicket();
            getChoice();
            break;
            case 3:
                printOccupancy();
                getChoice();
                break;
            case 4:
                viewTicket();
                getChoice();
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid Choice! Try Again");
                getChoice();
                break;
        }
    }

    public static void main(String args[]) {
        init();
        getChoice();
    }
}