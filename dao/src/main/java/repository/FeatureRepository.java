package repository;

import entity.Feature;
import entity.Room;

import java.util.List;

public interface FeatureRepository extends Repository<Feature>{
    List<Feature> getFeaturesByRoom(Room room);
    Feature getFeatureByName();
}
