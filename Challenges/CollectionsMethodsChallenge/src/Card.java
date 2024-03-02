import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, Integer rank) {

    public enum Suit{
        SPADE,
        CLUB,
        HEART,
        DIAMOND;

        public char getCharacter(){
            return new char[]{9824, 9827, 9829, 9830}[this.ordinal()];
        }
    }

    public static Card getNumericCard(Suit suit, Integer number){
        if(number >= 2 && number <= 10){
            return new Card(suit, String.valueOf(number), number -2);
        }
        return null;
    }

    public static Card getFaceCard(Suit suit, String face){
        int index = "JQKA".indexOf(face);
        if(index != -1){
            return new Card(suit, face, index + 9);
        }
        return null;
    }

    public static List<Card> getStandardCardDeck(){
        List<Card> standardDeck = new ArrayList<>();
        List<String> faces = List.of("J","Q","K","A");
        for(Suit suit : Suit.values()){
            for(int i = 2; i <= 10; i++){
                standardDeck.add(getNumericCard(suit, i));
            }
            for(String face : faces){
                standardDeck.add(getFaceCard(suit, face));
            }
        }
        return standardDeck;
    }

    public static void printStandardDeck(List<Card> standardDeck, int row){
        int numberOfElements =
                standardDeck.size() % row == 0 ?
                        standardDeck.size() / row : (standardDeck.size() / row) + 1;
        int counter = 0;
        for(Card card : standardDeck){
            System.out.print(card);
            counter++;
            if(counter == numberOfElements){
                counter = 0;
                System.out.println();
            }
        }
    }

    public static void printStandardDeck(List<Card> standardDeck){
        printStandardDeck(standardDeck, 4);
    }

    public static void printCards(List<Card> cards){
        for(Card card : cards){
            System.out.print(card);
        }
        System.out.println();
    }

    public static int getCardRank(String face){
        int index = "JQKA".indexOf(face);
        return index != -1 ? index + 9 : Integer.parseInt(face) - 2;
    }

    @Override
    public String toString() {
        return String.format("%s%c(%d) ", face, suit.getCharacter(), rank);
    }
}
