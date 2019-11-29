package entity;

import java.util.List;

public class Room {
    private int roomId;
    private int roomNumber;
    private List<Feature> features;

    public Room(){
    }

    public Room(int roomId, int roomNumber, List<Feature> features) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.features = features;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeature(List<Feature> features) {
        this.features = features;
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", features=" + features +
                '}';
    }
}
