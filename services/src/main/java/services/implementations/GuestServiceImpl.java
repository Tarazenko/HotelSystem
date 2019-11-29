package services.implementations;

import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.AttendanceRepository;
import repository.GuestRepository;
import repository.PassportRepository;
import repository.ReservationRepository;
import repository.implementations.AttendanceRepositoryImpl;
import repository.implementations.GuestRepositoryImpl;
import repository.implementations.PassportRepositoryImpl;
import repository.implementations.ReservationRepositoryImpl;
import services.GuestService;
import services.dto.GuestDTO;

import java.util.ArrayList;
import java.util.List;


public class GuestServiceImpl implements GuestService {
    Logger logger = LoggerFactory.getLogger(GuestServiceImpl.class);

    private GuestRepository guestRepository = new GuestRepositoryImpl();
    private AttendanceRepository attendanceRepository =new AttendanceRepositoryImpl();
    private PassportRepository passportRepository = new PassportRepositoryImpl() ;
    private ReservationRepository reservationRepository =new ReservationRepositoryImpl();

    public GuestServiceImpl() {

    }

    @Override
    public void create(GuestDTO guestDTO) {
        Guest guest = fromDTO(guestDTO);
        guestRepository.create(guest);
        Passport passport = guest.getPassport();
        passport.setId(guestRepository.getByPhone(guest.getPhoneNumber()).getId());
        passportRepository.create(passport);
    }

    private Guest fromDTO(GuestDTO guestDTO) {
        Guest guest = new Guest();
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        Passport passport = new Passport();
        passport.setNumber(guestDTO.getPassportNumber());
        passport.setFirstName(guestDTO.getFirstName());
        passport.setSecondName(guestDTO.getSecondName());
        passport.setThirdName(guestDTO.getThirdName());
        guest.setPassport(passport);
        return guest;
    }

    private GuestDTO toDTO(Guest guest) {
        if(guest != null) {
            GuestDTO guestDTO = new GuestDTO();
            guestDTO.setBill(guest.getBill());
            guestDTO.setPhoneNumber(guest.getPhoneNumber());
            List<Reservation> reservations = reservationRepository.getByGuest(guest.getId());
            List<Integer> numbers = new ArrayList<>();
            if (reservations != null) {
                for (Reservation reservation : reservations)
                    numbers.add(reservation.getGuest().getId());
                guestDTO.setRoomNumbers(numbers);
            }
            guestDTO.setFirstName(guest.getPassport().getFirstName());
            guestDTO.setSecondName(guest.getPassport().getSecondName());
            guestDTO.setThirdName(guest.getPassport().getThirdName());
            guestDTO.setPassportNumber(guest.getPassport().getNumber());
            List<Attendance> attendances = guestRepository.getAttendances(guest.getId());
            List<String> attendanceNames = new ArrayList<>();
            List<Double> attendanceCosts = new ArrayList<>();
            for (Attendance attendance : attendances) {
                attendanceNames.add(attendance.getName());
                attendanceCosts.add(attendance.getCost());
            }
            guestDTO.setAttendanceCost(attendanceCosts);
            guestDTO.setAttendanceName(attendanceNames);
            return guestDTO;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return guestRepository.delete(id);
    }

    @Override
    public void checkOutGuest(Guest guest) {
    }

    @Override
    public Guest getGuestByPassport(Passport passport) {
        return null;
    }

    @Override
    public int getId(String phone) {
        if(guestRepository.getByPhone(phone) == null){
            return  -1;
        }
        return guestRepository.getByPhone(phone).getId();
    }

    @Override
    public GuestDTO getById(int id) {
        return toDTO(guestRepository.getById(id));
    }

    @Override
    public List<GuestDTO> getAll() {
        logger.trace("ddddd");
        List<GuestDTO> guestsDTOs = new ArrayList<>();
        for (Guest guest : guestRepository.getAll()) {
            guestsDTOs.add(toDTO(guest));
        }
        return guestsDTOs;
    }

    @Override
    public boolean addAttendance(int guestId, String attendanceName) {
        boolean isOk = false;
        Attendance attendance = attendanceRepository.getByName(attendanceName);
        Guest guest = guestRepository.getById(guestId);
        if (attendance != null && guest != null) {
            List<Attendance> attendances = guestRepository.getAttendances(guestId);
            boolean isWas = false;
            for (Attendance element : attendances) {
                if (element.getName().equals(attendanceName)) {
                    isWas = true;
                    break;
                }
            }
            if (isWas) {
                isOk = guestRepository.updateBill(guestId, guest.getBill() + attendance.getCost()) &&
                        guestRepository.updateAmountAttendance(guestId, attendance.getId(),
                                guestRepository.getAttendanceAmount(guestId, attendance.getId()) + 1);
            } else {
                isOk = guestRepository.updateBill(guestId, guest.getBill() + attendance.getCost()) &&
                        guestRepository.addAttendance(guestId, attendanceName);
            }
        }
        return isOk;
    }

    @Override
    public List<Attendance> getAttendances(int guestId) {
        return guestRepository.getAttendances(guestId);
    }

    @Override
    public List<Guest> getByName(String firstName, String secondName) {
        return guestRepository.getByName(firstName, secondName);
    }
}
