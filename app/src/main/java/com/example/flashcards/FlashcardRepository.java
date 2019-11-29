package com.example.flashcards;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlashcardRepository {
    private static final String TAG = "FlashcardRepository";
    private static FlashcardRepository instance;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private static DeckRepository deckRepository;
    protected Map<String, Deck> map = new HashMap<>();
    //private ArrayList<Deck> decks = new ArrayList<>();
    public List<DataLoadedListener> dataLoadedListeners = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    private FlashcardRepository(){
        this.deckRepository = DeckRepository.getInstance();
        //databaseReference2 = FirebaseDatabase.getInstance().getReference().child("deck");
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("deck");
        init();
    }

    public interface DataLoadedListener {
        void onDataLoaded();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(){
        List<Deck> decklist = new ArrayList<>();
        decklist = deckRepository.getDecks();
        decklist.forEach(deck -> getname(deck));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*for(String key: map.keySet()){
                    map.get(key).clear();
                }*/

                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                    Deck deck = itemSnapshot.getValue(Deck.class);
                    DataSnapshot snapshot = itemSnapshot.child("flashcards");
                    Iterable<DataSnapshot> flashcardSnapshot = snapshot.getChildren();
                    for(DataSnapshot flashcard: flashcardSnapshot) {
                        Flashcard f = flashcard.getValue(Flashcard.class);
                        //flashcard.setUuid(flashcardSnapshot.getKey());
                        deck.addFlashcard(f);
                       // map.get(d.addFlashcard(flashcard);
                    }

                }

                FlashcardRepository.this.dataLoadedListeners.forEach(x -> x.onDataLoaded());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getname(Deck deck) {
        map.put(deck.getDeckName(), deck);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    FlashcardRepository(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static FlashcardRepository getInstance() {
        if (instance == null) {
            instance = new FlashcardRepository();
        }
        return instance;
    }

    public void addDataLoadedListener(FlashcardRepository.DataLoadedListener dataLoadedListener) {
        this.dataLoadedListeners.add(dataLoadedListener);
    }

    public void addFlashcard(Flashcard flashcard){
        databaseReference.push().setValue(flashcard);
    }

    /*public ArrayList<Flashcard> getFlashcards() {
        ArrayList<Flashcard> a = new ArrayList<>();
        a.addAll(map.values().);
        return a;
    }*/


}
