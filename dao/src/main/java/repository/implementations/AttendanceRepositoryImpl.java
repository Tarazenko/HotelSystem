package repository.implementations;

import entity.Attendance;
import repository.AttendanceRepository;
import repository.ConnectionPool;
import repository.constants.ConnectionData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepositoryImpl implements AttendanceRepository {
   private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public AttendanceRepositoryImpl(){
    }

    @Override
    public Attendance getByName(String name) {
        Attendance attendance = null;
        try (Connection con = connectionPool.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT * FROM attendance WHERE name LIKE ? ");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                attendance = takeAttendanceInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    @Override
    public boolean create(Attendance entity) {
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()){
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO `attendance` (`name`, `cost`) " +
                            "VALUES " + "(?, ?);");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getCost());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(int attendanceId) {
        return false;
    }

    @Override
    public boolean delete(int attendanceId) {
        boolean isDeleted = false;
        if (getById(attendanceId) != null) {
            try (Connection con = connectionPool.getConnection()){
                PreparedStatement preparedStatement = con.prepareStatement(
                        "DELETE FROM attendance WHERE attendance_id = ?");
                preparedStatement.setInt(1, attendanceId);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public Attendance getById(int attendanceId) {
        Attendance attendance = null;
        try (Connection con = connectionPool.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT * FROM attendance WHERE attendance_id = ? ");
            preparedStatement.setInt(1, attendanceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                attendance = takeAttendanceInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    @Override
    public List<Attendance> getAll() {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection con = connectionPool.getConnection()){
            //PreparedStatement preparedStatement = connection.prepareStatement(
            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT * FROM attendance;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                attendances.add(takeAttendanceInformation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    private Attendance takeAttendanceInformation(ResultSet resultSet) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setId(resultSet.getInt(1));
        attendance.setName(resultSet.getString(2));
        attendance.setCost(resultSet.getDouble(3));
        return attendance;
    }
}
