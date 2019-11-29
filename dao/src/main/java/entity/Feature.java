package entity;

public class Feature {
    private int featureId;
    private String featureName;
    private double cost;

    public Feature(){
    }

    public Feature(int featureId, String featureName, double cost) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.cost = cost;
    }

    public int getFeatureId() {
        return featureId;
    }


    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "featureId=" + featureId +
                ", featureName='" + featureName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
