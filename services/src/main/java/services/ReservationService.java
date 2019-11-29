package services;

import entity.Reservation;
import entity.Room;
import services.dto.ReservationDTO;

import java.util.List;

public interface ReservationService extends Service<ReservationDTO> {
    List<Reservation> getByRoom(int roomId);

    List<Reservation> getByGuest(int guestId);

    int getId(ReservationDTO reservationDTO);
}
