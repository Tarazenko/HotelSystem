package services.implementations;

import entity.Guest;
import entity.Reservation;
import entity.Room;
import repository.ReservationRepository;
import repository.implementations.ReservationRepositoryImpl;
import services.ReservationService;
import services.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationServiceImpl() {
        reservationRepository = new ReservationRepositoryImpl();
    }
    @Override
    public boolean create(ReservationDTO reservationDTO) {
        return reservationRepository.create(fromDTO(reservationDTO));
    }

    private Reservation fromDTO(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        Room room = new Room();
        room.setRoomId(reservationDTO.getRoomId());
        reservation.setRoom(room);
        Guest guest = new Guest();
        guest.setId(reservationDTO.getGuestId());
        reservation.setGuest(guest);
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        return reservation;
    }

    @Override
    public boolean update(ReservationDTO entity) {
        return false;
    }

    @Override
    public boolean delete(ReservationDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return reservationRepository.delete(id);
    }

    @Override
    public ReservationDTO getById(int id) {
        return toDTO(reservationRepository.getById(id));
    }

    @Override
    public List<ReservationDTO> getAll() {
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for(Reservation  reservation: reservationRepository.getAll()){
            reservationDTOs.add(toDTO(reservation));
        }
        return reservationDTOs;
    }

    private ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setGuestId(reservation.getGuest().getId());
        reservationDTO.setRoomId(reservation.getRoom().getRoomId());
        return reservationDTO;
    }

    @Override
    public List<Reservation> getByRoom(int roomId) {
        return reservationRepository.getByRoom(roomId);
    }

    @Override
    public List<Reservation> getByGuest(int guestId) {
        return  reservationRepository.getByGuest(guestId);
    }

    @Override
    public int getId(ReservationDTO reservationDTO) {
        return reservationRepository.getId(fromDTO(reservationDTO));
    }
}
