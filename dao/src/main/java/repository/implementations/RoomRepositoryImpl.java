package repository.implementations;

import com.sun.xml.internal.fastinfoset.sax.Features;
import entity.Feature;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import repository.ConnectionPool;
import repository.RoomRepository;
import repository.constants.ConnectionData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    //private Connection connection;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public RoomRepositoryImpl(){

    //    connection = DriverManager.getConnection(ConnectionData.URL,
     //           ConnectionData.USER, ConnectionData.PASSWORD);
    }

    @Override
    public Room getByNumber(int number) {
        Room room = null;
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM rooms " +
                            "WHERE number = ?");
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = takeRoomInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public boolean addFeature(int id, String featureName) {
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try(Connection connection = connectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(
                    "INSERT  INTO rooms_has_features (room_id, feature_id) " +
                            "SELECT r.room_id, f.feature_id " +
                            "FROM rooms r, features f " +
                            "WHERE r.room_id = ? " +
                            "AND f.feature_id = " +
                            "(SELECT feature_id FROM features " +
                            "WHERE name LIKE ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, featureName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean isFree(Room room, LocalDate date) {
        boolean isFree = true;
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reservation \n" +
                            "JOIN rooms ON rooms.room_id = reservation.room_id \n" +
                            "WHERE rooms.room_id = ? " +
                            "AND checkin_date <= ? " +
                            "AND checkout_date >= ? ");
            preparedStatement.setInt(1,room.getRoomId());
            preparedStatement.setObject(2,date);
            preparedStatement.setObject(3,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                isFree = false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFree;
    }


    @Override
    public boolean isFree(Room room, LocalDate dateIn, LocalDate dateOut) {
        boolean isFree = true;
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reservation \n" +
                            "JOIN rooms ON rooms.room_id = reservation.room_id \n" +
                            "WHERE rooms.room_id = ? " +
                            "AND (checkin_date <= ? " +
                            "AND checkout_date >= ?) " +
                            "OR (checkout_date >= ? AND checkin_date <= ?) " +
                            "OR (checkout_date >= ? AND checkin_date <= ?) ");
            preparedStatement.setInt(1,room.getRoomId());
            preparedStatement.setObject(2,dateIn);
            preparedStatement.setObject(3,dateIn);
            preparedStatement.setObject(4,dateOut);
            preparedStatement.setObject(5,dateOut);
            preparedStatement.setObject(6,dateOut);
            preparedStatement.setObject(7,dateIn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                isFree = false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isFree;
    }

    @Override
    public List<Feature> getRoomFeatures(int roomId) {
        List<Feature> features = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT features.feature_id, features.name, features.cost FROM rooms\n" +
                            "JOIN rooms_has_features ON rooms.room_id = rooms_has_features.room_id\n" +
                            "JOIN features ON features.feature_id = rooms_has_features.feature_id\n" +
                            "WHERE rooms.room_id = ?");
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                features.add(takeFeatureInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return features;
    }

    private Feature takeFeatureInformation(ResultSet resultSet) throws SQLException {
        Feature feature = new Feature();
        feature.setFeatureId(resultSet.getInt(1));
        feature.setFeatureName(resultSet.getString(2));
        feature.setCost(resultSet.getDouble(3));
        return feature;
    }

    @Override
    public boolean create(Room entity) {
        Statement statement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()){
            statement = connection.createStatement();
            statement.execute("INSERT INTO `rooms` (`number`) " +
                    "VALUES " + "(" + entity.getRoomNumber() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return  result;
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
                        "DELETE FROM rooms WHERE room_id = ?");
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
    public Room getById(int id) {
        Room room = null;
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM rooms " +
                            "WHERE room_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = takeRoomInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    private Room takeRoomInformation(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setRoomId(resultSet.getInt(1));
        room.setRoomNumber(resultSet.getInt(2));
        room.setFeature(getRoomFeatures(room.getRoomId()));
        return  room;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM rooms");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rooms.add(takeRoomInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
