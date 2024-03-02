import java.util.*;

public class Main {

    public static final Integer STRAIGHT_FLUSH_POINTS = 1600000;
    public static final Integer FOUR_OF_A_KIND_POINTS = 1400000;
    public static final Integer FULL_HOUSE_POINTS = 1200000;
    public static final Integer FLUSH_POINTS = 1000000;
    public static final Integer STRAIGHT_POINTS = 800000;
    public static final Integer THREE_OF_A_KIND_POINTS = 600000;
    public static final Integer TWO_PAIR_POINTS = 400000;
    public static final Integer ONE_PAIR_POINTS = 200000;

    public static void main(String[] args) {

        List<Card> standardCardDeck = Card.getStandardCardDeck();
        Collections.shuffle(standardCardDeck);
        int cursor = 0;
        int score;
        List<Integer> scoreBoard = new ArrayList<>();

//        List<Card> cards = Arrays.asList(
//                Card.getNumericCard(Card.Suit.CLUB, 2),
//                Card.getNumericCard(Card.Suit.HEART, 10),
//                Card.getNumericCard(Card.Suit.SPADE, 5),
//                Card.getNumericCard(Card.Suit.CLUB, 8),
//                Card.getNumericCard(Card.Suit.CLUB,10));
//        Card.printCards(cards);
//        Collections.sort(cards, Comparator.comparing(Card::rank));
//        Collections.reverse(cards);
//        System.out.println(isOnePair(cards));
//        System.out.println(calculateOnePairRank(cards));

        for(int i = 0; i < 6; i++){
            List<Card> cards = standardCardDeck.subList(cursor, cursor + 5);
            System.out.printf("\nPlayer %d Cards: ", i + 1);
            Card.printCards(cards);

            Collections.sort(cards, Comparator.comparing(Card::rank));
            Collections.reverse(cards);
            String type;

            if(isStraightFlush(cards)){
                score = calculateStandardRank(cards, STRAIGHT_FLUSH_POINTS);
                type = "Straight Flush";
            } else if(isFourOfAKind(cards)){
                score = calculateFourOfAKindRank(cards);
                type = "Four Of A Kind";
            } else if(isFullHouse(cards)){
                score = calculateFullHouseRank(cards);
                type = "Full House";
            } else if(isFlush(cards)){
                score = calculateStandardRank(cards,FLUSH_POINTS);
                type = "Flush";
            } else if(isStraight(cards)){
                score = calculateStandardRank(cards, STRAIGHT_POINTS);
                type = "Straight";
            } else if(isThreeOfAKind(cards)){
                score = calculateThreeOfAKindRank(cards);
                type = "Three Of A Kind";
            } else if(isTwoPairs(cards)){
                score = calculateTwoPairsRank(cards);
                type = "Two Pairs";
            } else if(isOnePair(cards)){
                score = calculateOnePairRank(cards);
                type = "One Pair";
            } else {
                score = calculateStandardRank(cards, 0);
                type = "High Card";
            }
            scoreBoard.add(score);
            System.out.printf("This is a %s for player %d, got %d points", type, i + 1, score);
            System.out.println();
            cursor += 5;
        }
        System.out.println();
        int value = Collections.max(scoreBoard);
        System.out.printf("Player %d won", scoreBoard.indexOf(value) + 1);
    }

    private static boolean isOnePair(List<Card> sortedCards){
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        if(faceMap.size() == 4){
            int count = 0;
            for(String key : faceMap.keySet()){
                if(faceMap.get(key) == 2){
                    count++;
                }
            }
            return count == 1;
        }
        return false;
    }

    private static int calculateOnePairRank(List<Card> sortedCards){
        int points = ONE_PAIR_POINTS;
        Map<String, Integer> map = findSameValues(sortedCards, false);
        List<String> restOfCards = new ArrayList<>();
        for(String key : map.keySet()){
            if(map.get(key) == 2){
                points += (10000 * Card.getCardRank(key));
            } else {
                restOfCards.add(key);
            }
        }
        for(int i = 0; i < restOfCards.size() - 1; i++){
            for(int j = 1; j < restOfCards.size(); j++){
                if(Card.getCardRank(restOfCards.get(i)) < Card.getCardRank(restOfCards.get(j))){
                    Collections.swap(restOfCards, i, j);
                }
            }
        }
        return points + Card.getCardRank(restOfCards.get(0)) * 1000
                + Card.getCardRank(restOfCards.get(1)) * 100
                + Card.getCardRank(restOfCards.get(2)) * 10;
    }

    private static boolean isTwoPairs(List<Card> sortedCards){
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        if(faceMap.size() == 3){
            int count = 0;
            for(String key : faceMap.keySet()){
                if(faceMap.get(key) == 2){
                    count++;
                }
            }
            return count == 2;
        }
        return false;
    }

    private static int calculateTwoPairsRank(List<Card> sortedCards){
        int points = TWO_PAIR_POINTS;
        Map<String, Integer> map = findSameValues(sortedCards, false);
        List<String> pairCards = new ArrayList<>();
        List<String> restOfCards = new ArrayList<>();
        for(String key : map.keySet()){
            if(map.get(key) == 2){
                pairCards.add(key);
            } else {
                restOfCards.add(key);
            }
        }
        if(Card.getCardRank(pairCards.get(0)) < Card.getCardRank(pairCards.get(1))){
            Collections.swap(pairCards, 0, 1);
        }
        return points + Card.getCardRank(pairCards.get(0)) * 10000
                + Card.getCardRank(pairCards.get(1)) * 1000
                + Card.getCardRank(restOfCards.get(0)) * 100;
    }

    private static boolean isThreeOfAKind(List<Card> sortedCards){
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        if(faceMap.size() == 3){
            for(String key : faceMap.keySet()){
                if(faceMap.get(key) == 3){
                    return true;
                }
            }
        }
        return false;
    }

    private static int calculateThreeOfAKindRank(List<Card> sortedCards){
        int points = THREE_OF_A_KIND_POINTS;
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        List<String> restOfCards = new ArrayList<>();
        for(String key : faceMap.keySet()){
            if(faceMap.get(key) == 3){
                points += (10000 * Card.getCardRank(key));
            } else {
                restOfCards.add(key);
            }
        }
        if(Card.getCardRank(restOfCards.get(0)) < Card.getCardRank(restOfCards.get(1))){
            Collections.swap(restOfCards, 0,1);
        }
        return points + Card.getCardRank(restOfCards.get(0)) * 1000 + Card.getCardRank(restOfCards.get(1)) * 100;
    }

    private static boolean isStraight(List<Card> sortedCards){
        Map<String, Integer> suitMap = findSameValues(sortedCards, true);
        return suitMap.size() != 1 ? isSequentialRank(sortedCards) : false;
    }
    private static boolean isFlush(List<Card> sortedCards){
        Map<String, Integer> suitMap = findSameValues(sortedCards, true);
        return suitMap.size() == 1 ? !isSequentialRank(sortedCards) : false;
    }

    private static boolean isFullHouse(List<Card> sortedCards){
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        if(faceMap.size() == 2){
            int count = 0;
            for(String key : faceMap.keySet()){
                if(faceMap.get(key) == 3 || faceMap.get(key) == 2){
                    count++;
                }
            }
            return count == 2;
        }
        return false;
    }

    private static int calculateFullHouseRank(List<Card> sortedCards){
        int points = FULL_HOUSE_POINTS;
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        for(String key : faceMap.keySet()){
            if(faceMap.get(key) == 3){
                points += (10000 * Card.getCardRank(key));
            } else {
                points += (1000 * Card.getCardRank(key));
            }
        }
        return points;
    }

    private static boolean isFourOfAKind(List<Card> sortedCards){
        Map<String, Integer> faceMap = findSameValues(sortedCards, false);
        if(faceMap.size() == 2){
            for(String key : faceMap.keySet()){
                if(faceMap.get(key) == 4){
                    return true;
                }
            }
        }
        return false;
    }

    private static int calculateFourOfAKindRank(List<Card> sortedCards){
        int points = FOUR_OF_A_KIND_POINTS;
        Map<String, Integer> map = findSameValues(sortedCards, false);
        for(String key : map.keySet()){
            if(map.get(key) == 4){
                points += (10000 * Card.getCardRank(key));
            }
            else {
                points += (1000 * Card.getCardRank(key));
            }
        }
        return points;

    }

    private static boolean isStraightFlush(List<Card> sortedCards){
        Map<String, Integer> suitMap = findSameValues(sortedCards, true);
        return suitMap.size() == 1 ? isSequentialRank(sortedCards) : false;
    }

    private static int calculateStandardRank(List<Card> sortedCards, int initialPoint){
        int points = initialPoint;
        int coef = 10000;
        for(Card sortedCard : sortedCards){
            points += (sortedCard.rank() * coef);
            coef /= 10;
        }
        return points;
    }

    private static boolean isSequentialRank(List<Card> sortedCards){
        List<String> cardFaceRankOrder =
            List.of("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A");
        List<String> faces = new ArrayList<>();
        for(Card sortedCard : sortedCards){
            faces.add(sortedCard.face());
        }
        if(Collections.indexOfSubList(cardFaceRankOrder, faces) == -1){
            Collections.rotate(faces, 1);
            return Collections.indexOfSubList(cardFaceRankOrder, faces) != -1;
        }
        return true;
    }

    private static Map<String, Integer> findSameValues(List<Card> sortedCards, boolean isCharacter){
        Map<String, Integer> resultMap = new HashMap<>();
        for(Card sortedCard : sortedCards){
            String value = isCharacter ? String.valueOf(sortedCard.suit().getCharacter()) : sortedCard.face();

            if(resultMap.containsKey(value)){
                resultMap.put(value, resultMap.get(value) + 1);
            } else {
                resultMap.put(value, 1);
            }
        }
        return resultMap;
    }
}