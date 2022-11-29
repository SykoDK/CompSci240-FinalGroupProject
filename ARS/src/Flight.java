import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Write a description of class Flight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Flight {
    private int flightNumber;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int duration;
    private ArrayList<Passenger> passengerList;
    private static int maxSeats;
    private static int maxRows;
    private static int maxAisles;
    private Seat[][] seats;

    public Flight() {
        maxSeats  = 40;
        maxAisles = 2;
        maxRows   = 20;

        seats = new Seat[maxRows][maxAisles];
        for (int row = 0; row < maxRows; row++) {
            for (int aisle = 0; aisle < maxAisles; aisle++)
            {
                if (aisle == 0) {
                    Seat seat = new Seat(row+"A");
                    System.out.println(seat);
                    seats[row][aisle] = seat;
                } else if (aisle == 1) {
                    Seat seat = new Seat(row+"B");
                    seats[row][aisle] = seat;
                }
            }
        }
    }

    public void setFlightNumber(int flightNum) {
        this.flightNumber = flightNum;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departureTime = departTime;
    }

    public void setArrivalTime(LocalTime arrTime) {
        this.arrivalTime = arrTime;
    }

    public void setFlightDuration(int dur) {
        this.duration = dur;
    }

    public void printSeats() {
        for (int row = 0; row < maxRows; row++) {
            for (int aisle = 0; aisle < maxAisles; aisle++) {
                System.out.println("Seat at row, aisle: " + row + " " + aisle + " " +seats[row][aisle].getPosition());
            }
        }
    }

    public void addPassenger(Passenger p) {
        passengerList.add(p);
    }
}