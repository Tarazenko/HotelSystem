package repository.implementations;

import entity.Feature;
import entity.Room;
import repository.ConnectionPool;
import repository.FeatureRepository;
import repository.constants.ConnectionData;

import java.sql.*;
import java.util.List;

public class FeatureRepositoryImpl implements FeatureRepository {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public FeatureRepositoryImpl() throws SQLException {

    }

    @Override
    public List<Feature> getFeaturesByRoom(Room room) {
        return null;
    }

    @Override
    public Feature getFeatureByName() {
        return null;
    }

    @Override
    public boolean create(Feature feature) {
        PreparedStatement preparedStatement = null;
        boolean result = true;
        try (Connection connection = connectionPool.getConnection()){
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO `features` (`name`, `cost`) " +
                            "VALUES " + "(?, ?);");
            preparedStatement.setString(1, feature.getFeatureName());
            preparedStatement.setDouble(2, feature.getCost());
            preparedStatement.execute();
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
        return false;
    }

    @Override
    public Feature getById(int id) {
        return null;
    }

    @Override
    public List<Feature> getAll() {
        return null;
    }
}
