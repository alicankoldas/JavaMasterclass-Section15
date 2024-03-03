package dev.lpa;

import dev.lpa.games.poker.PokerGame;

public class Main {
    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(4, 5);
        fiveCardDraw.startPlay();
    }
}