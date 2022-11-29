public class Reservation {

    private String airline;
    private int flightNumber;
    private String origin;
    private String destination;
    private int departureTime;
    private int arrivalTime;
    private int distance;
    private int price;
    private int seats;
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;

    public Reservation() {



    }

    public Reservation(String airline, int flightNumber, String origin, String destination,
            int departureTime, int arrivalTime, int distance, int price, int seats, int year,
            int month, int day, int dayOfWeek) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.distance = distance;
        this.price = price;
        this.seats = seats;
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }



    public void print() {
        System.out.println("airline = " + this.airline);
        System.out.println("flight number = " + this.flightNumber);
        System.out.println("origin = " + this.origin);
        System.out.println("destination = " + this.destination);
        System.out.println("departure time = " + this.departureTime);
        System.out.println("arrival time = " + this.arrivalTime);
        System.out.println("distance = " + this.distance);
        System.out.println("price = " + this.price);
        System.out.println("seats = " + this.seats);
        System.out.println("year = " + this.year);
        System.out.println("month = " + this.month);
        System.out.println("day = " + this.day);
        System.out.println("day of week = " + this.dayOfWeek);
        System.out.println();
    }


}