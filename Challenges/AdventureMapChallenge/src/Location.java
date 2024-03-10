import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {

    private final String name;
    private final String description;
    private final Map<String, String> nextPlaces = new HashMap<>();

    public Location(String name, String description) {

        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getNextPlaces() {
        return nextPlaces;
    }

    public void addNextPlaces(String placeStr){
        List<String> places = List.of(placeStr.split(";"));
        for(String place : places){
            nextPlaces.putIfAbsent(place.split(":")[0], place.split(":")[1]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: " + name + "\nDescription: " + description + "\n");
        for(String key : nextPlaces.keySet()){
            sb.append(key +  " -> " + nextPlaces.get(key) + "; ");
        }
        return sb.toString();
    }
}
