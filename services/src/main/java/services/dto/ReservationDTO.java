package services.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private int roomId;
    private int guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public  ReservationDTO(){
    }

    public ReservationDTO(int roomId, int guestId, LocalDate checkInDate, LocalDate checkOutDate) {
        this.roomId = roomId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "roomId=" + roomId +
                ", guestId=" + guestId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
