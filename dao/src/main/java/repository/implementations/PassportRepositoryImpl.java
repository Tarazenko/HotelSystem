package repository.implementations;

import entity.Passport;
import repository.ConnectionPool;
import repository.PassportRepository;

import java.sql.*;
import java.util.List;

public class PassportRepositoryImpl implements PassportRepository {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public PassportRepositoryImpl(){
    }

    @Override
    public Passport getByNumber(String number) {
        Passport passport = null;
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT `passport_id`, `first_name`, `second_name`," +
                            " `third_name`, `number`" +
                            "FROM passport WHERE number = ?");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passport = takePassportInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passport;
    }

    @Override
    public boolean changeNumber(int id) {
        return false;
    }

    @Override
    public boolean create(Passport entity){
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()){
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO `passport` (`passport_id`, `first_name`, " +
                            "`second_name`, `third_name`, `number`) " +
                            "VALUES " + "(?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getSecondName());
            preparedStatement.setString(4, entity.getThirdName());
            preparedStatement.setString(5, entity.getNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return  result;
    }

    @Override
    public boolean update(int passportId) {
        return false;
    }

    @Override
    public boolean delete(int passportId) {
        return false;
    }

    @Override
    public Passport getById(int id) {
        Passport passport = null;
        try (Connection connection = connectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT `passport_id`, `first_name`, `second_name`," +
                            " `third_name`, `number`" +
                            "FROM passport WHERE passport_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passport = takePassportInformation(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passport;
    }

    @Override
    public List<Passport> getAll() {
        return null;
    }

    private Passport takePassportInformation(ResultSet resultSet) throws SQLException {
        Passport passport = new Passport();
        passport.setId(resultSet.getInt(1));
        passport.setFirstName(resultSet.getString(2));
        passport.setSecondName(resultSet.getString(3));
        passport.setThirdName(resultSet.getString(4));
        passport.setNumber(resultSet.getString(5));
        return passport;
    }
}
