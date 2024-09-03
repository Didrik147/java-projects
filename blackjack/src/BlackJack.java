import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class BlackJack {
    private class Card {
        String value;
        String type;

        // Constructor
        Card(String value, String type){
            this.value = value;
            this.type = type;
        }
    }

    ArrayList<Card> deck;

    // Constructor
    BlackJack(){
        startGame();
    }

    public void startGame(){
        // Deck
        buildDeck();
    }

    public void buildDeck(){
        deck = new ArrayList<Card>();
    }
}
