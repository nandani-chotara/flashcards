package com.example.flashcards.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String deckName;
    private String Uuid;
    private String description;
    private String deckColor;
    private ArrayList<Flashcard> flashcards = new ArrayList<>();

    public Deck() {
        // Default constructor required for Firebase
    }

    public String getDescription() {
        return description;
    }

    public String getDeckColor() {
        return deckColor;
    }

    public void setDeckColor(String deckColor) {
        this.deckColor = deckColor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Deck(String name, String description, String color) {
        this.deckColor = color;
        this.deckName = name;
        this.description = description;
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
