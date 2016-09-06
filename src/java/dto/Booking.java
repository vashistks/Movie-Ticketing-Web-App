package dto;
import java.io.Serializable;

public class Booking implements Serializable
{
   private int showId;
   private int noTickets;
   private double amount;
   private int bookingId;
   private int customerId;

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getNoTickets() {
        return noTickets;
    }

    public void setNoTickets(int noTickets) {
        this.noTickets = noTickets;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
   
   
}
