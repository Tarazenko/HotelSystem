package repository;

import entity.Attendance;
import entity.Guest;
import entity.Passport;

import java.util.List;

public interface GuestRepository extends Repository<Guest>{
    Guest getByPhone(String phone);
    List<Guest> getByName(String firstName, String secondName);
    Guest getByPassport(Passport passport);
    boolean addAttendance(int guestId, String name);
    List<Attendance> getAttendances(int guestId);
    boolean updateBill(int id, double newBill);
    boolean updateAmountAttendance(int guestId, int attendanceId, int newAmount);
    int getAttendanceAmount(int guestId, int attendanceId);
}
