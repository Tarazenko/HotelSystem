package services.implementations;

import entity.Feature;
import repository.FeatureRepository;
import repository.implementations.FeatureRepositoryImpl;
import services.FeatureService;

import java.sql.SQLException;
import java.util.List;

public class FeatureServiceImpl implements FeatureService {
    private FeatureRepository featureRepository;

    public FeatureServiceImpl() {
        try {
            featureRepository = new FeatureRepositoryImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Feature getByName(String featureName) {
        return null;
    }

    @Override
    public boolean create(Feature feature) {
        return featureRepository.create(feature);
    }

    @Override
    public boolean update(Feature entity) {
        return false;
    }

    @Override
    public boolean delete(Feature entity) {
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
