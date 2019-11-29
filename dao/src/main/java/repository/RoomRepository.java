package repository;

import entity.Feature;
import entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends Repository<Room> {
    Room getByNumber(int number);
    boolean addFeature(int id, String featureName);
    List<Feature> getRoomFeatures(int id);
    boolean isFree(Room room, LocalDate date);
    boolean isFree(Room room, LocalDate dateIn, LocalDate dateOut);
}
