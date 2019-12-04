package com.example.flashcards;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckRepository {
    private static final String TAG = "DeckRepository";
    private static DeckRepository instance;
    private DatabaseReference databaseReference;
    private List<Deck> decks = Collections.emptyList();
    public List<DataLoadedListener> dataLoadedListeners = new ArrayList<>();


    protected DeckRepository(){

        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("decks");
        //init();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Deck> deckList = new ArrayList<>();
                for (DataSnapshot deckListSnapshot : dataSnapshot.getChildren()) {
                    Deck deck = deckListSnapshot.getValue(Deck.class);
                    deck.setUuid(deckListSnapshot.getKey());
                    deckList.add(deck);
                }

                //Log.i("Deck data loaded size", String.valueOf(DeckRepository.this.dataLoadedListeners.size()));
                for (DataLoadedListener dataLoadedListener : DeckRepository.this.dataLoadedListeners) {
                    dataLoadedListener.onDataLoaded();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled" + databaseError.getMessage());
            }
        });
    }
    /*public static DeckRepository getInstance() {
        Log.i(" Deck get insts", "in get Instance");
        if (instance == null) {
            instance = new DeckRepository();
        }
        return instance;
    }*/

    /*protected void init(){
        //Log.i("Deck Repo", "in deck repo");

    }*/

    public static DeckRepository getInstance() {
        if (instance == null) {
            instance = new DeckRepository();
        }
        return instance;
    }

    /*public DeckRepository(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        init();
    }*/

    public List<Deck> getDecks() {
        return decks;
    }

    public void addDeck(String name, String desp, String color) {
        Deck deck = new Deck(name, desp, color);
        databaseReference.push().setValue(deck);
    }

    public void addDataLoadedListener(DataLoadedListener dataLoadedListener) {
        this.dataLoadedListeners.add(dataLoadedListener);
    }

    public void removeDeck(Deck deck) {

        databaseReference.child(deck.getUuid()).removeValue();
    }

    public void updateDeck(String key, Deck deck){
        databaseReference.child(key).setValue(deck);
    }

    public void addCardstoDeck(String key, Flashcard flashcard){
        for(Deck deck: decks){
            if(deck.getUuid().equals(key)){
                Log.i("deck name", deck.getDeckName());
                //deck.setFlashcards(new ArrayList<>(Flashcard));
                deck.addFlashcard(flashcard);
            }
        }
    }

    public ArrayList<Flashcard> getCardOfDeck(String key){
        for(Deck deck: decks){
            if(deck.getUuid().equals(key)){

                return deck.getFlashcards();
            }
        }
        return null;
    }

    public Deck getDeck(String key){
        for(Deck deck : decks){
            if(deck.getUuid().equals(key)){

                return deck;
            }
        }
        return null;
    }

    public interface DataLoadedListener{
        void onDataLoaded();
    }
}
