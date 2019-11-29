package services.dto;

import java.util.List;

public class RoomDTO {

    private int number;
    private List<String> features;
    private List<Double> featureCosts;

    public RoomDTO(){

    }

    public RoomDTO(int number, List<String> features,List<Double> featureCosts) {
        this.number = number;
        this.features = features;
        this.featureCosts = featureCosts;
    }

    public List<Double> getFeatureCosts() {
        return featureCosts;
    }

    public void setFeatureCosts(List<Double> featureCosts) {
        this.featureCosts = featureCosts;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatureNames(List<String> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "number=" + number +
                ", features=" + features +
                ", featureCosts=" + featureCosts +
                '}';
    }
}
