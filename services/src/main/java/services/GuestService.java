package services;

import entity.*;
import services.dto.GuestDTO;

import java.util.List;

public interface GuestService<T> {
    void create(GuestDTO guest);

    boolean delete(int id);

    void checkOutGuest(Guest guest);

    Guest getGuestByPassport(Passport passport);

    int getId(String phone);

    GuestDTO getById(int id);

    List<GuestDTO> getAll();

    boolean addAttendance(int guestId, String name);

    List<Attendance> getAttendances(int guestId);

    List<Guest> getByName(String firstName, String secondName);
}
