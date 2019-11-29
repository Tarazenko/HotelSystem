package repository;

import entity.Guest;
import entity.Reservation;
import entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends Repository<Reservation> {
    Reservation getByCheckInDate(String date);

    Reservation getByCheckOutDate(String date);

    List<Reservation> getByGuest(int guestId);

    List<Reservation> getByRoom(int roomId);

    int getId(Reservation reservation);
}
