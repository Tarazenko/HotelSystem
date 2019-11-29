package repository.implementations;


import entity.Reservation;

import repository.ConnectionPool;
import repository.ReservationRepository;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {


    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public ReservationRepositoryImpl() {
    }

    @Override
    public Reservation getByCheckInDate(String date) {
        return null;
    }

    @Override
    public Reservation getByCheckOutDate(String date) {
        return null;
    }

    @Override
    public List<Reservation> getByGuest(int guestId) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reservation \n" +
                            "JOIN guests ON guests.guest_id = reservation.guest_id \n" +
                            "WHERE guests.guest_id = ?");
            preparedStatement.setInt(1, guestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservations.add(takeReservationInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> getByRoom(int roomId) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reservation \n" +
                            "JOIN rooms ON rooms.room_id = reservation.room_id \n" +
                            "WHERE rooms.room_id = ?");
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservations.add(takeReservationInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public int getId(Reservation reservation) {
        int id = -1;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT reservation_id FROM reservation \n" +
                            "WHERE room_id = ? AND " +
                            "guest_id = ? AND " +
                            "checkin_date = ? AND " +
                            "checkout_date = ?");
            preparedStatement.setInt(1, reservation.getRoom().getRoomId());
            preparedStatement.setInt(2, reservation.getGuest().getId());
            preparedStatement.setObject(3, reservation.getCheckInDate());
            preparedStatement.setObject(4, reservation.getCheckOutDate());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  id;
    }

    private Reservation takeReservationInformation(ResultSet resultSet) throws SQLException {
        GuestRepositoryImpl guestRepository = new GuestRepositoryImpl();
        RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();
        Reservation reservation = new Reservation();
        reservation.setReservationId(resultSet.getInt(1));
        reservation.setCheckInDate(resultSet.getObject(2, LocalDate.class));
        reservation.setCheckOutDate(resultSet.getObject(3, LocalDate.class));
        reservation.setGuest(guestRepository.getById(resultSet.getInt(4)));
        reservation.setRoom((roomRepository.getById(resultSet.getInt(5))));
        return reservation;
    }

    @Override
    public boolean create(Reservation reservation) {
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO `reservation` (`checkin_date`, `checkout_date`," +
                            "`guest_id`, `room_id`)" +
                            "VALUES " + "(?, ?, ?, ?);");
            preparedStatement.setObject(1, reservation.getCheckInDate());
            preparedStatement.setObject(2, reservation.getCheckOutDate());
            preparedStatement.setInt(3, reservation.getGuest().getId());
            preparedStatement.setInt(4, reservation.getRoom().getRoomId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;
        if (getById(id) != null) {
            try (Connection connection = connectionPool.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM reservation WHERE reservation_id = ? ");
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
    public Reservation getById(int id) {
        Reservation reservation = null;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reservation " +
                            "WHERE reservation_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservation = takeReservationInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reservation ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservations.add(takeReservationInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
