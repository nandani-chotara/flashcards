package com.example.flashcards;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeckRepository {
    private static final String TAG = "DeckRepository";
    private static DeckRepository instance;
    private DatabaseReference databaseReference;
    private ArrayList<Deck> decks = new ArrayList<>();
    private List<DataLoadedListener> dataLoadedListeners = new ArrayList<>();

    public interface DataLoadedListener{
        void onDataLoaded();
    }

    public static DeckRepository getInstance() {
        Log.i(" Deck get insts", "in get Instance");
        if (instance == null) {
            instance = new DeckRepository();
        }
        return instance;
    }

    private DeckRepository(){
        Log.i("Deck Repo", "in deck repo");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("deck");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                decks.clear();
                for (DataSnapshot deckSnapshot : dataSnapshot.getChildren()) {
                    Deck deck = deckSnapshot.getValue(Deck.class);
                    deck.setUuid(deckSnapshot.getKey());
                    decks.add(deck);
                }

                Log.i("Deck data loaded size", String.valueOf(DeckRepository.this.dataLoadedListeners.size()));
                DeckRepository.this.dataLoadedListeners.forEach(x -> x.onDataLoaded());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void addDeck(Deck deck) {
        databaseReference.push().setValue(deck);
    }

    public void addDataLoadedListener(DataLoadedListener dataLoadedListener) {
        this.dataLoadedListeners.add(dataLoadedListener);
    }

    public void removeDeck(Deck deck) {

        databaseReference.child(deck.getUuid()).removeValue();
    }
}
