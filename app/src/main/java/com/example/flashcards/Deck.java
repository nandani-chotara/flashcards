package com.example.flashcards;

public class Deck {
    private String deckName;
    private String deckDescription;

    public Deck(String name) {
        deckName = name;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String name) {
        deckName = name;
    }

    public String getDeckDescription() {
        return deckDescription;
    }

    public void setDeckDescription(String description) {
       deckDescription = description;
    }
}
