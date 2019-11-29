package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class FlashcardRecyclerViewActivity extends AppCompatActivity {

    private ArrayList<Flashcard> flashcards = new ArrayList<>();
    private RecyclerView rv;
    private Toolbar toolbar;
    private TextView deckSize;
    //private FlashcardRepository flashcardRepository;
    FlashcardRVAdapter adapter;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_recyclerview_activity);
        //flashcardRepository = FlashcardRepository.getInstance();


        if (getIntent().hasExtra("key")) {
            key = getIntent().getStringExtra("key");
        }


        flashcards = DeckRepository.getInstance().getCardOfDeck(key);
        Deck d = DeckRepository.getInstance().getDeck(key);
        d.setFlashcards(flashcards);

        toolbar = findViewById(R.id.flashcard_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("testing");
        getIncomingIntent();


        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        rv.addItemDecoration(new FlashcardMarginItem(20));

        //initializeData();
        initializeAdapter();

        deckSize = findViewById(R.id.deckSize);
        //deckSize.setText(String.valueOf(flashcards.size()));

        FloatingActionButton fabAddFlashcard = findViewById(R.id.fabAddFlashcard);
        fabAddFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FlashcardAddActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("flashcards", (Serializable) flashcards);
                startActivity(intent);
            }
        });

        /*flashcardRepository.addDataLoadedListener(new DeckRepository.DataLoadedListener() {
            @Override
            public void onDataLoaded() {
                adapter.setFlashcards();
            }
        });*/
    }

    @Override
    public void onResume()
    {
        //Log.i("Deck in resume", "in resume");
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("deck_name")) {
            String deckname = getIntent().getStringExtra("deck_name");
            setName(deckname);
        }
    }

    private void setName(String deckname) {
        toolbar.setTitle("Deck: " + deckname);
    }

    private void initializeData() {
        //flashcards = new ArrayList<>();
        Flashcard card1 = new Flashcard();
        card1.setQuestion("5+5");
        card1.setAnswer("10");
        Flashcard card2 = new Flashcard();
        card2.setQuestion("What colour is the sky?");
        card2.setAnswer("Blue");
        Flashcard card3 = new Flashcard();
        card3.setQuestion("What is the meaning of life?");
        card3.setAnswer("Nothing");
        Flashcard card4 = new Flashcard();
        card4.setQuestion("It's great to be a _____");
        card4.setAnswer("Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk" +
                "Laurier Golden Hawk");
        flashcards.add(card1);
        flashcards.add(card2);
        flashcards.add(card3);
        flashcards.add(card4);
    }

    private void initializeAdapter() {
        //get cards of the particular deck, may be use key

        adapter = new FlashcardRVAdapter(flashcards);
        rv.setAdapter(adapter);
    }
}
