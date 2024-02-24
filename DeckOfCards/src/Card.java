import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Card(Suit suit, String face, int rank) {

    public enum Suit {
        CLUB(9827),
        DIAMOND(9830),
        HEART(9829),
        SPADE(9824);

        private int asciiChar;

        private Suit(int asciiChar){
            this.asciiChar = asciiChar;
        }
    }

    public static Card getNumericCard(Suit suit, int number){
        return new Card(suit, String.valueOf(number), 1);
    }

    public static Card getFaceCard(Suit suit, String face){
        return new Card(suit,face, 2);
    }

    public static List<Card> getStandardDeck(){
        List<Card> standardDeck = new ArrayList<>();
        List<String> faces = Arrays.asList("J", "Q", "K", "A");
        for(Suit suit : Suit.values()){
            for(int i = 2; i < 11; i++){
                standardDeck.add(getNumericCard(suit, i));
            }
            for(String face : faces){
                standardDeck.add(getFaceCard(suit, face));
            }
        }
        return standardDeck;
    }

    public static void printDeck(String description, List<Card> cards, int rowCount){
        int row = cards.size() / rowCount;
        row = cards.size() % rowCount != 0 ? row + 1 : row;
        int i = 0;
        System.out.println(description);
        for(Card card : cards){
            System.out.print(card);
            i++;
            if(i == row){
                System.out.println();
                i = 0;
            }
        }
    }

    public static void printDeck(List<Card> cards){
        printDeck("Current Deck", cards, 4);
    }

    @Override
    public String toString() {
        return face + (char)suit.asciiChar + String.format("(%d)", rank);
    }
}
