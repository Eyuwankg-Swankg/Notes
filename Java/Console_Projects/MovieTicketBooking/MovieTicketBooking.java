import java.util.*;

class Owner {

    static Scanner sc = new Scanner(System.in);

    void getChoice() {

        System.out.println("1.Add Movie\n2.Show All Movies\n3.Delete Movie\n4.Exit");
        System.out.println("Enter Your Choice: ");
        int choice = Owner.sc.nextInt();

        switch (choice) {

            case 1:
                System.out.print("Movie Name : ");
                String mvname = Owner.sc.next();
                System.out.print("Runtime Name : ");
                int mvruntime = Owner.sc.nextInt();
                System.out.print("Price : ");
                int mvprice = Owner.sc.nextInt();

                Movies movie = new Movies(mvname, mvruntime, mvprice);
                movie.addMovie();

                getChoice();

                break;
            case 2:
                Movies getAllMovies = new Movies();
                getAllMovies.showAllMovies();
                getChoice();
                break;
            case 3:
                Movies deleteMovie = new Movies();
                deleteMovie.deleteMovie();
                getChoice();
                break;
            case 4:
                break;
            default:
                getChoice();
                break;
        }

    }
}

class Ticket {

    public int ticketId;
    static int iDs = 1;
    public int movieId;
    public String movieName;
    public int runtime;
    public int price;
    public int[] bookedSeats;

    public Ticket(int[] seatBooking, Movies movie) {
        this.ticketId = Ticket.iDs++;
        this.bookedSeats = seatBooking;
        this.movieId = movie.movieId;
        this.movieName = movie.movieName;
        this.runtime = movie.runtime;
        this.price = movie.price * seatBooking.length;
    }

    public void showTicket(){

            System.out.println("Ticket ID : " + this.ticketId);
            System.out.println("Movie ID : " + this.movieId);
            System.out.println("Movie Name : " + this.movieName);
            System.out.println("Runtime : " + this.runtime);
            System.out.println("Total Price : " + this.price);
            System.out.print("Booked Seats : ");
            for(int i=0;i<this.bookedSeats.length;i++){
                System.out.print(this.bookedSeats[i]);
                if(i!=this.bookedSeats.length-1)
                System.out.print(", ");
            }
            System.out.println("\n");
    
    }
}

class Movies {

    static Scanner sc = new Scanner(System.in);

    public int movieId;
    static int iDs = 1;
    static ArrayList<Movies> movies = new ArrayList<Movies>();
    public String movieName;
    public int runtime;
    public int price;
    public boolean[][] seats;

    Movies() {

    }

    Movies(String movieName, int runtime, int price) {
        this.movieId = Movies.iDs++;
        this.movieName = movieName;
        this.runtime = runtime;
        this.price = price;
        this.seats = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                this.seats[i][j] = false;
        }
    }

    public void addMovie() {
        Movies.movies.add(this);
        System.out.println(this.movieName + " Added!");
    }

    public void showAllMovies() {

        System.out.println("--------All Movie Shows---------");
        for (int i = 0; i < Movies.movies.size(); i++) {
            Movies movie = Movies.movies.get(i);
            System.out.println("Name : " + movie.movieName);
            System.out.println("Id : " + movie.movieId);
            System.out.println("Runtime : " + movie.runtime);
            System.out.println("Price : " + movie.price);
            System.out.println();
        }
        System.out.println("-------------------------------");

    }

    public void deleteMovie() {
        System.out.print("Enter Id of Movie : ");
        int movieId = sc.nextInt();
        Movies.movies.remove(movieId - 1);
    }

    public void showSeats() {
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < 10; j++) {
                System.out.print(this.seats[i][j] ? "B " : "_ ");
            }
            System.out.println();
        }
    }

    public void cancelBookedSeats(int[] bookedSeats){
        for(int i=0;i<bookedSeats.length;i++){
            int seatNo=bookedSeats[i];
            this.seats[seatNo / 10][seatNo % 10] = false;
        }
    }


    public int[] bookTicket() {

        System.out.print("Enter no of tickets to book :  ");
        int noOfSeat = Movies.sc.nextInt();
        int[] seatBookings = new int[noOfSeat];

        for (int i = 0; i < noOfSeat; i++) {
            this.showSeats();
            System.out.println("Enter SeatNo (" + (i + 1) + ") :  ");
            int seatNo = sc.nextInt() - 1;
            this.seats[seatNo / 10][seatNo % 10] = true;
            seatBookings[i] = seatNo;
        }
        this.showSeats();
        return seatBookings;
    }

}

class Customer {

    static ArrayList<Ticket> MyTickets = new ArrayList<Ticket>();
    static Scanner sc = new Scanner(System.in);

    void getChoice() {

        System.out.println("1.Show All Movie\n2.Book Ticket\n3.Cancel Ticket\n4.Show My Bookings\n5.Exit");
        System.out.println("Enter Your Choice: ");
        int choice = Customer.sc.nextInt();

        switch (choice) {

            case 1: 
                Movies movies = new Movies();
                movies.showAllMovies();
                getChoice();
                break;

            case 2: 
                Movies getAllMovies = new Movies();
                getAllMovies.showAllMovies();
                System.out.println("Enter Movie ID : ");
                int movieId = Customer.sc.nextInt();
                Movies movie = Movies.movies.get(movieId-1);
                int[] bookings = movie.bookTicket();
                MyTickets.add(new Ticket(bookings, movie));
                getChoice();
                break;

            case 3: // cancel ticket
                System.out.print("\nEnter Ticket ID : ");
                int ticketId=Customer.sc.nextInt();
                Movies moviesObj=Movies.movies.get(MyTickets.get(ticketId-1).movieId);
                moviesObj.cancelBookedSeats(MyTickets.get(ticketId-1).bookedSeats);
                MyTickets.remove(ticketId-1);
                getChoice();
                break;

            case 4:
                System.out.println("--------All Ticket Bookings---------\n");
                for (int i = 0; i < MyTickets.size(); i++) {
                    MyTickets.get(i).showTicket();
                }
                System.out.println("\n-------------------------------");
                getChoice();
                break;

            case 5:
                break;

            default:
                getChoice();
                break;

        }

    }

}

class MovieTicketBooking {

    static Scanner sc = new Scanner(System.in);

    static void getChoice() {
        System.out.println("Movie Ticket Booking System\n1.Owner\n2.Customer\n3.Exit");
        System.out.println("Enter Your Choice: ");
        int choice = MovieTicketBooking.sc.nextInt();

        switch (choice) {

            case 1:
                System.out.println("Owner Panel\n");
                System.out.print("Enter Password : ");
                String password = sc.next();
                if (password.equals("1234")) {
                    Owner owner = new Owner();
                    owner.getChoice();
                } else 
                    System.out.println("Wrong Password!\n");
                MovieTicketBooking.getChoice();
                break;
            case 2:
                Customer customer = new Customer();
                customer.getChoice();
                MovieTicketBooking.getChoice();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");

        }
    }

    public static void main(String[] args) {

        MovieTicketBooking.getChoice();

    }

}
