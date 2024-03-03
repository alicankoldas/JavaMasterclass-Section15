import java.util.NavigableSet;
import java.util.TreeSet;

public class Theatre {

    private String theatreName;
    private Integer seatsInRow;
    private NavigableSet<Seat> seats = new TreeSet<>();

    public Theatre(String theatreName, Integer numberOfRows,
                   Integer numberOfSeatsTotal) {
        if(numberOfRows < 1 || numberOfRows > 26){
            System.out.println("Number of rows should be between 1 and 26");
        } else {
            this.theatreName = theatreName;
            this.seatsInRow = numberOfSeatsTotal % numberOfRows == 0
                    ? numberOfSeatsTotal / numberOfRows
                    : numberOfSeatsTotal / numberOfRows + 1;
            this.seats = addSeats(numberOfSeatsTotal);

        }
    }

    private NavigableSet<Seat> addSeats(Integer numberOfSeatsTotal){
        char firstRowChar = 'A';
        int firstSeatNumber = 1;
        for(int i = 0; i < numberOfSeatsTotal; i++) {
            seats.add(new Seat(firstRowChar, firstSeatNumber));
            if(firstSeatNumber == seatsInRow){
                firstRowChar++;
                firstSeatNumber = 1;
            } else {
                firstSeatNumber++;
            }

        }
        return seats;
    }

    public void printSeatMap(){
        int counter = 1;
        for(Seat seat : seats){
            if(seat.isReserved){
                System.out.print((seat + "*"). replaceAll("\\s+","") + " ");
            }else {
                System.out.print(seat);
            }

            if(counter == seatsInRow){
                System.out.println();
                counter = 1;
            } else{
                counter++;
            }
        }
    }

    public boolean reserveSeat(Character character, Integer seatNumber){
        Seat requestedSeat = new Seat(character, seatNumber);
        if(seats.contains(requestedSeat)){
            Seat seat = seats.floor(new Seat(character, seatNumber));
            if(!seat.isReserved){
                seat.isReserved = true;
                return true;
            } else{
                System.out.printf("Seat %s is already reserved%n", seat);
                return false;
            }
        } else {
            System.out.printf("Seat %s doesn't exist%n", requestedSeat);
            return false;
        }
    }

    public class Seat  implements Comparable<Seat>{
        private Character rowCharacter;
        private Integer seatNumber;
        private boolean isReserved;


        public Seat(Character rowCharacter, Integer seatNumber) {
            this(rowCharacter, seatNumber, false);
        }

        public Seat(Character rowCharacter, Integer seatNumber, boolean isReserved) {
            this.rowCharacter = rowCharacter;
            this.seatNumber = seatNumber;
            this.isReserved = isReserved;
        }

        @Override
        public String toString() {
            String reserved = isReserved ? "*" : "";
            return "%c%03d ".formatted(rowCharacter, seatNumber);
        }

        @Override
        public int compareTo(Seat seat) {
            if(this.rowCharacter.compareTo(seat.rowCharacter) == 0){
                return this.seatNumber.compareTo(seat.seatNumber);
            }
            return this.rowCharacter.compareTo(seat.rowCharacter);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(obj instanceof  Seat){
                Seat seat = (Seat)obj;
                if(seat.rowCharacter.equals(this.rowCharacter)){
                    return seat.seatNumber.equals(this.seatNumber);
                }
                return false;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 33* rowCharacter.hashCode() + seatNumber.hashCode();
        }
    }
}
