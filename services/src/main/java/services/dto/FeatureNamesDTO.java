package services.dto;

import java.util.List;

public class FeatureNamesDTO {

    private List<String> names;

    public FeatureNamesDTO(){
    }

    public FeatureNamesDTO(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "FeatureNamesDTO{" +
                "names=" + names +
                '}';
    }
}
