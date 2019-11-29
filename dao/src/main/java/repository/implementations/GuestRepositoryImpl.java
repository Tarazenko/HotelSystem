package repository.implementations;

import entity.Attendance;
import entity.Guest;
import entity.Passport;
import repository.ConnectionPool;
import repository.GuestRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryImpl implements GuestRepository {

    //private ConnectionPool connectionPool = new ConnectionPool();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public GuestRepositoryImpl() {
    }

    @Override
    public boolean create(Guest guest) {
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO `guests` (`phone`, `bill`) " +
                            "VALUES " + "(?, ?);");
            preparedStatement.setString(1, guest.getPhoneNumber());
            preparedStatement.setDouble(2, guest.getBill());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public Guest getByPhone(String phone) {
        Guest guest = null;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT `guest_id`, `phone`, " +
                            " `bill` " +
                            "FROM guests WHERE phone = ?");
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                guest = takeGuestInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    @Override
    public List<Guest> getByName(String firstName, String secondName) {
        List<Guest> guests = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM guests " +
                            "JOIN passport ON passport.first_name = ? AND passport.second_name = ?" +
                            " AND guests.guest_id = passport.passport_id;");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                guests.add(takeGuestWithPassport(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    @Override
    public Guest getByPassport(Passport passport) {
        Guest guest = null;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM guests " +
                            "JOIN passport ON guest_id = ? AND guests.guest_id = passport.passport_id;");
            preparedStatement.setInt(1, passport.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                guest = takeGuestWithPassport(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    @Override
    public boolean addAttendance(int guestId, String name) {
        //todo do check how many attendances were order
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(
                    "INSERT  INTO attendance_has_guests (attendance_id, guest_id) " +
                            "SELECT a.attendance_id, g.guest_id " +
                            "FROM attendance a, guests g " +
                            "WHERE g.guest_id = ? " +
                            "AND a.attendance_id = " +
                            "(SELECT attendance_id FROM attendance " +
                            "WHERE name LIKE ?)");
            preparedStatement.setInt(1, guestId);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public List<Attendance> getAttendances(int guestId) {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT attendance.attendance_id, attendance.name, attendance.cost " +
                            "FROM hotel.attendance_has_guests\n" +
                            "JOIN guests ON guests.guest_id = ? AND guests.guest_id = attendance_has_guests.guest_id \n" +
                            "JOIN attendance ON attendance.attendance_id = attendance_has_guests.attendance_id;");
            preparedStatement.setInt(1, guestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                attendances.add(takeAttendanceInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    @Override
    public boolean updateBill(int id, double newBill) {
        boolean isUpdate = false;
        if (getById(id) != null) {
            try (Connection connection = connectionPool.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE guests SET bill = ? WHERE guest_id = ?");
                preparedStatement.setDouble(1, newBill);
                preparedStatement.setInt(2, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean updateAmountAttendance(int guestId, int attendanceId, int newAmount) {
        boolean isUpdate = false;
        if (getById(guestId) != null) {
            try (Connection connection = connectionPool.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE attendance_has_guests SET amount = ? " +
                                "WHERE guest_id = ? AND attendance_id = ? ");
                preparedStatement.setInt(1, newAmount);
                preparedStatement.setInt(2, guestId);
                preparedStatement.setInt(3, attendanceId);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public int getAttendanceAmount(int guestId, int attendanceId) {
        int amount = -1;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT amount " +
                            "FROM hotel.attendance_has_guests " +
                            " WHERE attendance_id = ? AND guest_id = ? \n");
            preparedStatement.setInt(1, attendanceId);
            preparedStatement.setInt(2, guestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    private Attendance takeAttendanceInformation(ResultSet resultSet) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setId(resultSet.getInt(1));
        attendance.setName(resultSet.getString(2));
        attendance.setCost(resultSet.getDouble(3));
        return attendance;
    }

    @Override
    public boolean update(int gouestId) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;
        if (getById(id) != null) {
            try (Connection connection = connectionPool.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM guests WHERE guest_id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public Guest getById(int id) {
        Guest guest = null;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM guests " +
                            "JOIN passport ON guest_id = ? AND guests.guest_id = passport.passport_id;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                guest = takeGuestWithPassport(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    @Override
    public List<Guest> getAll() {
        List<Guest> guests = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM guests\n" +
                            "JOIN passport ON guests.guest_id = passport.passport_id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                guests.add(takeGuestWithPassport(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    private Guest takeGuestInformation(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        Passport passport = new Passport();
        guest.setId(resultSet.getInt(1));
        guest.setPhoneNumber(resultSet.getString(2));
        guest.setBill(resultSet.getDouble(3));
        return guest;
    }

    private Guest takeGuestWithPassport(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        Passport passport = new Passport();
        guest.setId(resultSet.getInt(1));
        guest.setPhoneNumber(resultSet.getString(2));
        guest.setBill(resultSet.getDouble(3));
        passport.setId(resultSet.getInt(4));
        passport.setFirstName(resultSet.getString(5));
        passport.setSecondName(resultSet.getString(6));
        passport.setThirdName(resultSet.getString(7));
        passport.setNumber(resultSet.getString(8));
        guest.setPassport(passport);
        List<Attendance> attendances = new ArrayList<>();
        attendances = getAttendances(guest.getId());
        guest.setAttendances(attendances);
        return guest;
    }
}
