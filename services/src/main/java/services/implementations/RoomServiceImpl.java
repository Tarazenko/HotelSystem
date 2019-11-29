package services.implementations;

import entity.Feature;
import entity.Room;
import repository.RoomRepository;
import repository.implementations.RoomRepositoryImpl;
import services.RoomService;
import services.dto.FeatureNamesDTO;
import services.dto.RoomDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository ;

    public RoomServiceImpl() {
      roomRepository = new RoomRepositoryImpl();
    }


    @Override
    public void removeRoom(int id) {
        roomRepository.delete(id);
    }

    @Override
    public void createRoom(RoomDTO roomDTO) {
        Room room = toRoom(roomDTO);
        roomRepository.create(room);
        room.setRoomId(roomRepository.getByNumber(room.getRoomNumber()).getRoomId());
        for(Feature feature: room.getFeatures()){
            addFeature(room.getRoomId(), feature.getFeatureName());
        }
    }

    private Room toRoom(RoomDTO roomDTO){
        Room room = new Room();
        room.setRoomNumber(roomDTO.getNumber());
        List<Feature> features = new ArrayList<>();
        for(String featureName: roomDTO.getFeatures()){
            Feature feature = new Feature();
            feature.setFeatureName(featureName);
            features.add(feature);
        }
        room.setFeature(features);
        return room;
    }

    private List<RoomDTO> convertRoomList(List<Room> rooms){
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for(Room room: rooms) {
            roomDTOs.add(fromRoom(room));
        }
        return roomDTOs;
    }

    private RoomDTO fromRoom(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setNumber(room.getRoomNumber());
        List<Double> costs = new ArrayList<>();
        List<String> featureNames = new ArrayList<>();
        for(Feature feature: room.getFeatures()){
            featureNames.add(feature.getFeatureName());
            costs.add(feature.getCost());
        }
        roomDTO.setFeatureNames(featureNames);
        roomDTO.setFeatureCosts(costs);
        return roomDTO;
    }

    @Override
    public RoomDTO getRoomById(int id) {
        return fromRoom(roomRepository.getById(id));
    }

    @Override
    public RoomDTO getRoomByNumber(int number) {
        Room room = roomRepository.getByNumber(number);
        return fromRoom(room);
    }

    @Override
    public List<RoomDTO> getAll() {
        return convertRoomList(roomRepository.getAll());
    }

    @Override
    public List<RoomDTO> findRoomByParams(FeatureNamesDTO features, LocalDate dateIn, LocalDate dateOut) {
        List<Room> rooms = roomRepository.getAll();
        List<Room> result = new ArrayList<>();
        for (Room room: rooms) {
            List<Feature> roomFeatures = roomRepository.getRoomFeatures(room.getRoomId());
            List<java.lang.String> featuresNames = new ArrayList<>();
            for(Feature feature: roomFeatures){
                featuresNames.add(feature.getFeatureName());
            }
            boolean isHave = true;
            for(String feature: features.getNames()){
                if(!featuresNames.contains(feature)) {
                    isHave = false;
                    break;
                }
            }
            if(isHave && isFree(room, dateIn, dateOut))
                result.add(room);
        }
        return convertRoomList(result);
    }

    @Override
    public boolean addFeature(int roomId, String featureName) {
        return roomRepository.addFeature(roomId, featureName);
    }

    @Override
    public double getRoomCost(int roomId) {
        double cost = 0;
        for (Feature feature : roomRepository.getRoomFeatures(roomId)) {
            cost += feature.getCost();
        }
        return cost;
    }

    @Override
    public boolean isFree(Room room, LocalDate dateIn, LocalDate dateOut) {
        return roomRepository.isFree(room, dateIn, dateOut);
    }

    @Override
    public List<Feature> getRoomFeatures(int roomId) {
        return roomRepository.getRoomFeatures(roomId);
    }

    @Override
    public int getId(int roomNumber) {
        return roomRepository.getByNumber(roomNumber).getRoomId();
    }

}
