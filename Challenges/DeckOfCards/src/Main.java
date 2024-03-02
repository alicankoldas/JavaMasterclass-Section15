public class Main {
    public static void main(String[] args) {

        System.out.println("Deck Of Card Overview");

        System.out.println(Card.getNumericCard(Card.Suit.CLUB, 9));
        System.out.println(Card.getFaceCard(Card.Suit.SPADE, "A"));
        Card.printDeck("Standard Deck", Card.getStandardDeck(), 13);
        System.out.println();
        Card.printDeck(Card.getStandardDeck());
    }
}