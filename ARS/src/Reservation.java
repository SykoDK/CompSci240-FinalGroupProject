import java.util.ArrayList;

public class Reservation {
    UI obj = new UI();
    private ArrayList<String> departFlightDetails;


    private String airline;
    private int flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int distance;
    private int price;
    private int seats;
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;

    public Reservation() {



    }

    public Reservation(int flightNumber, String departureTime, String arrivalTime, String flightTime, String departureDate, String arrivalDate, String airline, String origin, String destination) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        //this.flightTime = flightTime;
        //this.departureDate = departureDate;
        //this.arrivalDate = arrivalDate;

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
        System.out.println(departFlightDetails);
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

    public String setDepartureFlight(String s) {
        return this.airline + " " + this.flightNumber + " " + this.origin + " " + this.destination + " " + this.departureTime;
    }

    public String setReturnFlight(String s) {
        return this.airline + " " + this.flightNumber + " " + this.destination + " " + this.origin + " " + this.arrivalTime;
    }

    public int setNumPassengers(String chosenNumPassenger) {
        return this.seats;
    }

    public String setSeatType(String s) {
        return "Economy";
    }

    public String setDepartureDate(String text) {
        return this.year + "-" + this.month + "-" + this.day;
    }

    public String setReturnDate(String text) {
        return this.year + "-" + this.month + "-" + this.day;
    }
    public String setDepartureAirport(String chosenDepartureAirport) {
        return this.origin;
    }

    public String setArrivalAirport(String chosenArrivalAirport) {
        return this.destination;
    }

    public String setDepartureTime(String s) {
        return this.departureTime + "";
    }

    public String setArrivalTime(String s) {
        return this.arrivalTime + "";
    }

    public String setReturnDepartureTime(String s, DateTextField returnDateTextField) {
        return this.setReturnDate(returnDateTextField.getText()) + "";
    }

    public String setReturnArrivalTime(String s, DateTextField returnDateTextField) {
        return this.setReturnDate(returnDateTextField.getText()) + "";
    }

public String setDepartureFlightNumber(String s) {
        return this.flightNumber + "";
    }

    public String setReturnFlightNumber(String s) {
        return this.flightNumber + "";
    }

    public String getAirline() {
        return airline;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getDepartureTime() {
        return Integer.parseInt(null);
    }

    public int getArrivalTime() {
        return Integer.parseInt(arrivalTime);
    }

    public int getDistance() {
        return distance;
    }

    public int getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public void getDepartureFlightNumber() {
        System.out.println("Departure Flight Number: " + this.flightNumber);
    }

    public boolean getDepartureFlight() {
        System.out.println("Departure Flight: " + this.airline + " " + this.flightNumber + " " + this.origin + " " + this.destination + " " + this.departureTime);
        return true;
    }

    public String getReturnFlight() {
        System.out.println("Return Flight: " + this.airline + " " + this.flightNumber + " " + this.destination + " " + this.origin + " " + this.arrivalTime);
        return this.airline + " " + this.flightNumber + " " + this.destination + " " + this.origin + " " + this.arrivalTime;
    }

    public int getNumPassengers() {
        System.out.println("Number of Passengers: " + this.seats);
        return this.seats;
    }

    public String getSeatType() {
        System.out.println("Seat Type: Economy");
        return "Economy";
    }

    public String getDepartureDate() {
        System.out.println("Departure Date: " + this.year + "-" + this.month + "-" + this.day);
        return this.year + "-" + this.month + "-" + this.day;
    }

    public String getReturnDate() {
        System.out.println("Return Date: " + this.year + "-" + this.month + "-" + this.day);
        return this.year + "-" + this.month + "-" + this.day;
    }
}