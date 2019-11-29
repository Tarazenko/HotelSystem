package services;

import entity.Feature;
import entity.Room;
import services.dto.FeatureNamesDTO;
import services.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    void removeRoom(int id);

    void createRoom(RoomDTO room);

    RoomDTO getRoomById(int id);

    RoomDTO getRoomByNumber(int number);

    List<RoomDTO> getAll();

    List<RoomDTO> findRoomByParams(FeatureNamesDTO features, LocalDate dateIn, LocalDate dateOut);

    boolean addFeature(int roomId, String featureName);

    double getRoomCost(int roomId);

    boolean isFree(Room room, LocalDate dateIn, LocalDate dateOut);

    List<Feature> getRoomFeatures(int roomId);

    int getId(int roomNumber);
}
