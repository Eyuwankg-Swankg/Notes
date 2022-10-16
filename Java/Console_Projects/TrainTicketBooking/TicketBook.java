import java.util.*;

class Ticket {
    char fromStation;
    char toStation;
    int PNRNumber;
    int noOfPassengers;
    static int PNR = 100;
    boolean isCancelled;
    boolean isWaitingList;
    int[] seatMap;

    Ticket(char fromStation, char toStation, int[] seatMap, int noOfPassengers, boolean isWaitingList) {
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
            for(int i=0;i<noOfPassengers;i++){
                System.out.print(seatMap[i]);
                if(i<noOfPassengers-1){
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
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
    
    // TODO: REMOVE AT FINAL
    static void secretOccupancy(){
            System.out.println("\n   A  B  C  D  E  ");
            for (int i = 0; i < 8; i++) {
                System.out.print(i + 1+" ");
                for (int j = 0; j < 10; j += 2) {
                    System.out.print((occupancyChart[i][j]) ? " B" : " -");
                    System.out.print((occupancyChart[i][j + 1]) ? "B" : "-");
                }
                System.out.println("\n");
            }
            System.out.println("\n -------------------------");
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
    static int getWaitingListIndex(int pnr){
        for(int i=0;i<waitingList.size();i++){
            if(waitingList.get(i).PNRNumber==pnr)   
                return i;
        }
        return -1;
    }
    static boolean checkWaitingListAvailability() {
        if (waitingList.size() > 1) {
            return false;
        }
        return true;
    }
    static int[] assignSeats(char fromStation, char toStation, int noOfPassengers) {

        int[] seatMap = new int[noOfPassengers];
        int availableSeats=0;
        int fromStationIndex=getIndex(fromStation),toStationIndex=getIndex(toStation);
        for(int i=0;i<8;i++){
            int availableSeatInStations=0;
            for(int j=fromStationIndex;j<=toStationIndex;j+=2){
                if(j==fromStationIndex){
                    if(occupancyChart[i][j+1]==false)
                        availableSeatInStations++;
                }else if(j==toStationIndex){
                    if(occupancyChart[i][j]==false)
                        availableSeatInStations++;
                }else{
                    if(occupancyChart[i][j]==false && occupancyChart[i][j+1]==false)
                        availableSeatInStations++;  
                }
            }
            if(availableSeatInStations==((toStation-fromStation)+1)){
                seatMap[availableSeats]=i+1;
                availableSeats++;
            }
            if(availableSeats==noOfPassengers)
                break;
        }
        if(availableSeats==noOfPassengers){

            for(int i=0;i<seatMap.length;i++){
                for(int j=fromStationIndex+1;j<=toStationIndex;j++) 
                    occupancyChart[seatMap[i]-1][j]=true;
            }
            return seatMap;
        }
        return new int[]{-1};
    }

    static void bookTicket() {

        System.out.print("Enter From Station : ");
        char fromStation = Character.toLowerCase(scanner.next().charAt(0));
        System.out.print("Enter To Station : ");
        char toStation = Character.toLowerCase(scanner.next().charAt(0));
        System.out.print("Enter No of Passengers (<9) : ");
        int noOfPassengers = scanner.nextInt();
        int[] seatMap = assignSeats(fromStation, toStation, noOfPassengers);
        if (seatMap[0]==-1) {
            System.out.println("\nNo Seats Available");
            boolean isWaitingAvailable = checkWaitingListAvailability();
            if (isWaitingAvailable) {
                waitingList.add(
                        new Ticket(fromStation, toStation, new int[noOfPassengers], noOfPassengers, true));
                System.out.println("\nPNR No : " + waitingList.get(waitingList.size() - 1).PNRNumber);
                System.out.println("\nAdded to Waiting List");
            } else {
                System.out.println("\nWaiting List is Full");
            }
        }else{
            tickets.add(
                    new Ticket(fromStation, toStation, seatMap, noOfPassengers, false));
            System.out.println("\nTicket Booked Successfully!!! ");
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
        if(inValidPNR){
            for (int i = 0; i < waitingList.size(); i++) {
                if (waitingList.get(i).PNRNumber == pnrNo) {
                    cancellationTicket = waitingList.get(i);
                    inValidPNR = false;
                }
            }
        }
        if(cancellationTicket.isCancelled){
            System.out.println("Already Cancelled!!!");
            return;
        }
        if (inValidPNR) {
            System.out.println("\n Invalid PNR No");
            cancelTicket();
        } else {
            if(cancellationTicket.isWaitingList){
                int index=getWaitingListIndex(cancellationTicket.PNRNumber);    
                if(index!=-1){  
                    cancellationTicket.isCancelled=true;
                    tickets.add(waitingList.get(index));
                    waitingList.remove(index);
                    System.out.println(pnrNo + " Ticket Cancelled !!!");
                }
                return;
            }
            int fromStationIndex=getIndex(cancellationTicket.fromStation),toStationIndex=getIndex(cancellationTicket.toStation);
            for(int i=0;i<cancellationTicket.noOfPassengers;i++){
                for(int j=fromStationIndex+1;j<=toStationIndex;j++) 
                    occupancyChart[cancellationTicket.seatMap[i]-1][j]=false;
            }
            cancellationTicket.isCancelled=true;
            System.out.println(pnrNo + " Ticket Cancelled !!!\n");

            // Check Wailting list for allocation
            Vector<Integer> confirmedIndex=new Vector<Integer>();
            for(int i=0;i<waitingList.size();i++){
                Ticket waitingTicket=waitingList.get(i);
                int[] seatMap = assignSeats(waitingTicket.fromStation, waitingTicket.toStation, waitingTicket.noOfPassengers);
                if(seatMap[0]!=-1){
                    waitingTicket.seatMap=seatMap;
                    waitingTicket.isWaitingList=false;
                    confirmedIndex.add(i);
                    System.out.println("Waiting List PNR" + waitingTicket.PNRNumber + " Allocated");
                }
            }
            for(int index:confirmedIndex){
                tickets.add(waitingList.get(index));
                waitingList.remove(index);
            }

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
            // TODO: CHECK AND REMOVE AFTER TESTING
            case 6:
                secretOccupancy();
                getChoice();
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