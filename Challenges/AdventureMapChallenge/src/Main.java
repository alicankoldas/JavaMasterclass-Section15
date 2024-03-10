import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, Location> route = new HashMap<>();
        fillMap(route);
        Location currentLocation = route.get("Road");
        userPrompt(currentLocation);

        while(true){
            String value = scanner.nextLine();
            if(value.toUpperCase().equals("Q"))
                break;

            Set<String> nextPlaces = currentLocation.getNextPlaces().keySet();
            if(nextPlaces.contains(value.toUpperCase())){
                currentLocation = route.get(
                        currentLocation.getNextPlaces().get(value.toUpperCase()).replace(" ", ""));
            } else {
                System.out.println("Wrong!");
            }
            userPrompt(currentLocation);
        }


    }

    private static void userPrompt(Location currentLocation){

        String playerPrompter = """
        *** You're standing %s ***
            From here, you can see:
            %s
        Select your Compass Direction (Q to quit) >>
        """;

        String currentLocationDescription = currentLocation.getDescription();
        StringBuilder sb2 = new StringBuilder();
        Map<String, String> possibleNextPlaces = currentLocation.getNextPlaces();
        for(String key : possibleNextPlaces.keySet()){
            sb2.append("\u2022" + " A " +  possibleNextPlaces.get(key).toLowerCase() + " to the "+
                    Directions.valueOf(key).getDirection() +  " (" + Directions.valueOf(key) + ")\n\t");
        }
        sb2.replace(sb2.lastIndexOf("\n"),sb2.lastIndexOf("\n") + 1, "");
        System.out.printf(playerPrompter + "\n", currentLocationDescription, sb2.toString());

    }

    private static void fillMap(Map<String, Location> route){
        Location road = new Location("Road","at the end");
        road.addNextPlaces("W:Hill;E:Well House;S:Valley;N:Forest;");
        route.putIfAbsent(road.getName(), road);
        Location hill = new Location("Hill","on top of hill with a view in all directions");
        hill.addNextPlaces("N:Forest;E:Road;");
        route.putIfAbsent(hill.getName(), hill);
        Location wellHouse = new Location("WellHouse","inside a well house for a small spring");
        wellHouse.addNextPlaces("W:Road;N:Lake;S:Stream;");
        route.putIfAbsent(wellHouse.getName(), wellHouse);
        Location valley = new Location("Valley","in a forest valley beside a tumbling stream");
        valley.addNextPlaces("N:Road;W:Hill;E:Stream;");
        route.putIfAbsent(valley.getName(), valley);
        Location forest = new Location("Forest","at the edge of a thick dark forest");
        forest.addNextPlaces("S:Road;E:Lake;");
        route.putIfAbsent(forest.getName(), forest);
        Location lake = new Location("Lake","by an alpine lake surrounded by wildflowers");
        lake.addNextPlaces("W:Forest;S:Well House;");
        route.putIfAbsent(lake.getName(), lake);
        Location stream = new Location("Stream","near a stream with a rocky bed");
        stream.addNextPlaces("W:Valley;N:Well House;");
        route.putIfAbsent(stream.getName(), stream);
    }

}