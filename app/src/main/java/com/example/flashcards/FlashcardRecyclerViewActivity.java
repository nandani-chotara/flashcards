package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlashcardRecyclerViewActivity extends AppCompatActivity {

    static  List<Flashcard> flashcards;
    private RecyclerView rv;
    private Toolbar toolbar;
    private TextView deckSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_recyclerview_activity);

        toolbar = findViewById(R.id.flashcard_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("testing");
        getIncomingIntent();

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        rv.addItemDecoration(new FlashcardMarginItem(20));

        initializeData();
        initializeAdapter();

        deckSize = findViewById(R.id.deckSize);
        deckSize.setText(String.valueOf(flashcards.size()));

        FloatingActionButton fabAddFlashcard = findViewById(R.id.fabAddFlashcard);
        fabAddFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FlashcardAddActivity.class);
                intent.putExtra("flashcards", (Serializable) flashcards);
                startActivity(intent);
            }
        });
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
        flashcards = new ArrayList<>();
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
        FlashcardRVAdapter adapter = new FlashcardRVAdapter(flashcards);
        rv.setAdapter(adapter);
    }
}
