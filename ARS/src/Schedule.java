import java.time.LocalDate;

public class Schedule
{
    private LocalDate date;
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private String airline;
    private int flightNumber;
    private String origin;
    private String destination;
    private int departureTime;
    private int distance;
    private int arrivalTime;
    
    /**
     * Constructor for objects of class Schedule
     */
    public Schedule(LocalDate d, String airln, int flightNum, String orig, String dest,
                    int departTime, int dist, int arrTime) {
        this.date = d;
        this.airline = airln;
        this.flightNumber = flightNum;
        this.origin = orig;
        this.destination = dest;
        this.departureTime = departTime;
        this.distance = dist;
        this.arrivalTime = arrTime;
    }

    public void print() {
        System.out.println("Date = " + this.date);
        System.out.println("airline = " + this.airline);
        System.out.println("flight number = " + this.flightNumber);
        System.out.println("origin = " + this.origin);
        System.out.println("destination = " + this.destination);
        System.out.println("departure time = " + this.departureTime);
        System.out.println("distance = " + this.distance);
        System.out.println("arrival time = " + this.arrivalTime);
        System.out.println();
    }
        
    public int getYear() {
        return year;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public int getDayOfWeek() {
        return dayOfWeek;
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
        return departureTime;
    }

    public int getDistnace() {
        return distance;
    }
    
    public int getArrivalTime() {
        return arrivalTime;
        }
}
