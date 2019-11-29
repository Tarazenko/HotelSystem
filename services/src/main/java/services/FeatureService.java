package services;

import entity.Feature;

public interface FeatureService extends Service<Feature> {
    Feature getByName(String featureName);
}
