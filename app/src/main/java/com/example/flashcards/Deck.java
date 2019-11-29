package com.example.flashcards;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String deckName;
    private String Uuid;
    private ArrayList<Flashcard> flashcards = new ArrayList<>();;

    public Deck() {
        // Default constructor required for Firebase
    }

    public Deck(String name) {

        this.deckName = name;
        this.flashcards  = new ArrayList<>();
        //flashcards.clear();
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

    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ArrayList<Flashcard> f){ this.flashcards = f;}

}
