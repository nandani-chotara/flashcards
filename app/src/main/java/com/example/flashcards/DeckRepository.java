package com.example.flashcards;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.flashcards.Models.Deck;
import com.example.flashcards.Models.Flashcard;
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


    public DeckRepository(){

        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("decks");
        //init();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Deck> deckList = new ArrayList<>();
                for (DataSnapshot deckListSnapshot : dataSnapshot.getChildren()) {
                    Deck deck = deckListSnapshot.getValue(Deck.class);
                    deck.setUuid(deckListSnapshot.getKey());
                    deckList.add(deck);
                }


                DeckRepository.this.decks = deckList;
                DeckRepository.this.dataLoadedListeners.forEach(x -> x.onDataLoaded());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled" + databaseError.getMessage());
            }
        });
    }


    public static DeckRepository getInstance() {
        if (instance == null) {
            instance = new DeckRepository();
        }
        return instance;
    }



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

    public void addFlashcard(String question, String answer, String key) {
        Flashcard flashcard = new Flashcard(question, answer);
        Log.i("Deck Repo",key);
        databaseReference.child(key).push().setValue(flashcard);
    }

    public interface DataLoadedListener{
        void onDataLoaded();
    }
}
