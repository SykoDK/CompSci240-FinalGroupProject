import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    private String firstName;
    private String lastName;
    private String departFlightNumber;
    private String destination;
    private String departDepartureTime;
    private String departArrivalTime;
    private String arrivalTime;
    private String departureDate;
    private String returnFlightNumber;
    private String origin;
    private String returnDepartureTime;
    private String returnArrivalTime;
    private String returnTime;
    private String returnDate;
    private String email;
    private String seat;

    public Reservation() {

    }

    public void print() {
        System.out.println("flight number = " + this.departFlightNumber);
        System.out.println("origin = " + this.origin);
        System.out.println("destination = " + this.destination);
        System.out.println("departure time = " + this.departDepartureTime);
        System.out.println("arrival time = " + this.departArrivalTime);

        System.out.println("departure date = " + this.departureDate);
        System.out.println("arrival date = " + this.returnDate);
        System.out.println();
    }

    public void setSeatType(String chosenSeat) {
        this.seat = chosenSeat;
    }
    public void setNumPassengers(String chosenNumPassenger) {
        this.seat = chosenNumPassenger;
    }

    public void setDepartureDate(String text) {
        this.departureDate = text;
    }

    public void setReturnDate(String chosenReturnDate) {
        this.returnDate = chosenReturnDate;;
    }
    public void setDepartureAirport(String chosenDepartureAirport) {
        this.origin = chosenDepartureAirport;
    }

    public void setArrivalAirport(String chosenArrivalAirport) {
        this.destination = chosenArrivalAirport;
    }

    public void setDepartDepartureTime(String departDepartureTimeStr) {
        this.departDepartureTime = departDepartureTimeStr;
    }

    public void setDepartArrivalTime(String departArrivalTimeStr) {
        this.departArrivalTime = departArrivalTimeStr;
    }
    public void setReturnDepartureTime(String returnDepartureTimeStr) {
        this.returnDepartureTime = returnDepartureTimeStr;
    }
    public void setReturnArrivalTime(String returnArrivalTimeStr) {
        this.returnArrivalTime = returnArrivalTimeStr;
    }
    public void setReturnDepartureTimeString (String returnDepartureTimeStr) { this.returnDepartureTime = returnDepartureTimeStr; }
    public void setReturnArrivalTimeString (String returnArrivalTimeStr) { this.returnArrivalTime = returnArrivalTimeStr; }

    public void setDepartureFlightNumber(String chosenDeparuFlightNumber) {
        this.departFlightNumber = chosenDeparuFlightNumber;
    }

    public void setReturnFlightNumber(String chosenReturnFlightNumber) {
        this.returnFlightNumber = chosenReturnFlightNumber;
    }

    public void setFirstName(String reservationFirstName) {
        this.firstName = reservationFirstName;
    }
    public void setLastName(String reservationLastName) {
        this.lastName = reservationLastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartureDate() {
        System.out.println("Departure Date: " + this.departureDate);
        return this.departureDate;
    }

    public String getReturnDate() {
        System.out.println("Return Date: " + this.returnDate);
        return this.returnDate;
    }

    public String getDepartureAirport() {
        System.out.println("Departure Airport: " + this.origin);
        return this.origin;
    }

    public String getArrivalAirport() {
        System.out.println("Arrival Airport: " + this.destination);
        return this.destination;
    }

    public String getDepartureFlightNumber() {
        System.out.println("Departure Flight Number: " + this.departFlightNumber);
        return this.departFlightNumber;
    }

    public String getReturnFlightNumber() {
        System.out.println("Return Flight Number: " + this.returnFlightNumber);
        return this.returnFlightNumber;
    }

    public String getDepartDepartureTime() {
        System.out.println("Depart Departure Time: " + this.departDepartureTime);
        return this.departDepartureTime;
    }

    public String getDepartArrivalTime() {
        System.out.println("Depart Arrival Time: " + this.departArrivalTime);
        return this.departArrivalTime;
    }

    public String getReturnDepartureTime() {
        System.out.println("Return Departure Time: " + this.returnDepartureTime);
        return this.returnDepartureTime;
    }

    public String getReturnArrivalTime() {
        System.out.println("Return Arrival Time: " + this.returnArrivalTime);
        return this.returnArrivalTime;
    }

    public String getSeatType() {
        System.out.println("Seat Type: " + this.seat);
        return this.seat;
    }

    public String getFirstName() {
        System.out.println("First Name: " + this.firstName);
        return this.firstName;
    }

    public String getLastName() {
        System.out.println("Last Name: " + this.lastName);
        return this.lastName;
    }

    public String getOrigin() {
        System.out.println("Origin: " + this.origin);
        return origin;
    }

    public String getDestination() {
        System.out.println("Destination: " + this.destination);
        return destination;
    }

    public String getEmail() {
        System.out.println("Email: " + this.email);
        return this.email;
    }

  }