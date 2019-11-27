package com.example.flashcards;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String deckName;
    private String Uuid;

    public Deck() {
        // Default constructor required for Firebase
    }

    public Deck(String name) {
        this.deckName = name;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String name) {
        this.deckName = name;
    }

    public String getUuid() {
        return Uuid;
    }
    public void setUuid(String uuid) {
        Uuid = uuid;
    }
}
