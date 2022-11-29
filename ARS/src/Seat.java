/**
 * Write a description of class Seat here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Seat {
    private String position;
    private boolean available;

    public Seat(String pos) {
        this.position = pos;
        this.available = true;
    }

    public String getPosition() {
        return this.position;
    }

    public void setReservation(String pos) {
        this.position = pos;
        this.available = false;
    }

    public boolean getSeatAvailable() {
        if (this.available) {
            return true;
        } else {
            return false;
        }
    }
}