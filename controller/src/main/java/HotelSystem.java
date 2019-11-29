import entity.Guest;
import entity.Passport;
import entity.Reservation;
import entity.Room;
import repository.GuestRepository;
import repository.ReservationRepository;
import repository.implementations.GuestRepositoryImpl;
import repository.implementations.ReservationRepositoryImpl;
import services.*;
import services.implementations.AttendanceServiceImpl;
import services.implementations.GuestServiceImpl;
import services.implementations.ReservationServiceImpl;
import services.implementations.RoomServiceImpl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HotelSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static GuestService guestService = new GuestServiceImpl();
    private static GuestRepository guestRepository = new GuestRepositoryImpl();
    private static RoomService roomService = new RoomServiceImpl();
    private static ReservationService reservationService = new ReservationServiceImpl();
    private static AttendanceService attendanceService = new AttendanceServiceImpl();
    private static ReservationRepository reservationRepository = new ReservationRepositoryImpl();


    public static void main(String[] args) {

       System.out.println(guestService.getAll());

       /* Room room = roomService.getRoomById(1);
        Guest guest = guestService.getById(10);
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setGuest(guest);
        reservation.setCheckInDate(LocalDate.parse("2018-11-10"));
        reservation.setCheckOutDate(LocalDate.parse("2018-11-12"));
        reservationService.create(reservation);*/

        //System.out.println(roomService.isFree(roomService.getRoomById(1),
         //       LocalDate.parse("2018-11-09")));

        //System.out.println(reservationRepository.getAll());
       // System.out.println(reservationService.getById(10));
       // System.out.println(guestService.getById(10));

        //System.out.println(roomService.getRoomCost(1));
        /* int ans = -1;
        while (ans != 0) {
            System.out.println("Choose action:" +
                    "\n1.Guest control" +
                    "\n2.Room control" +
                    "\n3.Reservstion control");
            ans = scanner.nextInt();
            switch (ans) {
                case 1:
                    guestMenu();
                    break;
                case 2:
                    roomMenu();
                    break;
                case 3:
                    reservationMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong number of operation.");
            }
        }*/
    }

    private static void reservationMenu() {
        System.out.println("Comming soon");
    }

    private static void roomMenu() {

    }

    private static void guestMenu() {
        int ans = -1;
        while (ans != 0) {
            System.out.println("Choose action:" +
                    "\n1.Get all guests" +
                    "\n2.Create guest" +
                    "\n3.Get information about guest" +
                    "\n4.Delete guest");
            ans = scanner.nextInt();
            switch (ans) {
                case 1:
                    getAllInformation();
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    getGuestInformation();
                    break;
                case 4:
                    deleteGuest();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong operation number");
            }
        }
    }

    private static void deleteGuest() {
        System.out.print("Input firstname: ");
        String firstName = scanner.next();
        System.out.print("Input secondname: ");
        String secondName = scanner.next();
        List<Guest> guests;
        guests = guestService.getByName(firstName, secondName);
        if (guests.size() == 1) {
            guestService.delete(guests.get(0).getId());
        }
        else {
            for (int i = 0; i < guests.size(); i++) {
                Guest guest = new Guest();
                guest = guests.get(i);
                System.out.println(i + 1 + ". " + guest.getPassport().getFirstName()
                        + ' ' + guest.getPassport().getSecondName() +
                        +' ' + guest.getPassport().getThirdName()
                        + ' ' + guest.getPhoneNumber());
            }
            System.out.print("Select guest to delete: ");
            int ans = scanner.nextInt();
            guestService.delete(guests.get(ans - 1).getId());
        }
        }

        private static void create () {
            System.out.println("Input guest information");
            System.out.print("Input firstname: ");
            String firstName = scanner.next();
            System.out.print("Input secondname: ");
            String secondName = scanner.next();
            System.out.print("Input thirdname: ");
            String thirdName = scanner.next();
            System.out.print("Input passport number: ");
            String number = scanner.next();
            Passport passport = new Passport();

        }

        private static void getAllInformation () {
            List<Guest> guests;
            guests = guestService.getAll();
            System.out.println(guests);
           /* for (int i = 0; i < guests.size(); i++) {
                Guest guest = new Guest();
                guest = guests.get(i);
                System.out.println(i + 1 + ". " + guest.getPassport().getFirstName()
                        + ' ' + guest.getPassport().getSecondName() +
                        +' ' + guest.getPassport().getThirdName()
                        + ' ' + guest.getPhoneNumber());
                System.out.println("Attendace: " + guest.getAttendances());
            }*/
        }

        private static void getGuestInformation () {
        }

        private static void startMenu () {
            System.out.println("Choose role...\n" +
                    "1.User\n" +
                    "2.Admin\n");
            int ans = scanner.nextInt();
            if (ans == 1) {
                userMenu();
            } else
                adminMenu();
        }

        private static void outputFoodOrder () {
            System.out.println("Output menu ...");
        }

        private static void outputServiceMenu () {
            System.out.println("Choose what do you want: \n" +
                    "List servises ...\n" +
                    "Guest choose servise ... \n" +
                    "Return to main menu\n");
        }

        private static void outputMainMenu () {
            System.out.println("Choose what do you want: \n" +
                    "1.List empty rooms; \n" +
                    "2.Check in room; \n" +
                    "3.Check out room; \n" +
                    "4.Attendance; \n" +
                    "5.????? Food order. \n");
        }

        private static void outputListRoomMenu () {
            System.out.println("Output list empty rooms...\n" +
                    "1.Input room number to book\n" +
                    "2.Return to menu");
        }

        private static void outputCheckInRoomMenu () {
            System.out.println("Input room number: ... read add to database\n" +
                    "Input info about youself: ... read info and add to database\n");
        }

        private static void outputCheckOutRoomMenu () {
            System.out.println("Input room number: ... read delete from database\n");
        }

        private static void userMenu () {
            int ans = -1;
            while (ans != 0) {
                outputMainMenu();
                ans = scanner.nextInt();
                if (ans == 1) {
                    outputListRoomMenu();
                    int ansList = scanner.nextInt();
                    if (ansList == 1) {
                        outputCheckInRoomMenu();
                        scanner.nextLine();
                        scanner.nextLine();
                    }
                }
                if (ans == 2) {
                    outputCheckInRoomMenu();
                    scanner.nextLine();
                    scanner.nextLine();
                }
                if (ans == 3) {
                    outputCheckOutRoomMenu();
                    scanner.nextLine();
                    scanner.nextLine();
                }
                if (ans == 4) {
                    outputServiceMenu();
                    scanner.nextLine();
                    scanner.nextLine();
                }
                if (ans == 5) {
                    outputFoodOrder();
                    scanner.nextLine();
                    scanner.nextLine();
                }
            }
        }

        private static void adminMenu () {
            int ans = -1;
            while (ans != 0) {
                System.out.println("Choose entity:\n" +
                        "1.Room\n" +
                        "2.Attendance\n");
                ans = scanner.nextInt();
                if (ans == 1) {
                    System.out.println("List rooms ..." +
                            "1.Add features.\n" +
                            "2.Dlete features.\n" +
                            "3.Delete room.\n");
                    ans = scanner.nextInt();
                    switch (ans) {
                        case 1:
                            System.out.println("Features list\n" +
                                    "Write numbers of features to add");
                            break;
                        case 2:
                            System.out.println("Features list of room\n" +
                                    "Write numbers of features to delete");
                            break;
                        case 3:
                            System.out.println("Room delete ....\n");
                    }
                } else if (ans == 2) {
                    System.out.println("List attendances...\n" +
                            "1.Add attendance.\n" +
                            "2.Delete attendance.\n");
                    ans = scanner.nextInt();
                    switch (ans) {
                        case 1:
                            System.out.println("Write info about attendance to add\n");
                            break;
                        case 2:
                            System.out.println("Attendance list\n" +
                                    "Write numbers of attendance to delete\n");
                            break;
                    }
                }
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }
