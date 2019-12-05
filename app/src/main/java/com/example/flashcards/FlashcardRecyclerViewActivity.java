package com.example.flashcards;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashcardRecyclerViewActivity extends AppCompatActivity {

    private List<Flashcard> flashcards = Collections.emptyList();
    public List<FlashcardRecyclerViewActivity.DataLoadedListener> dataLoadedListeners = new ArrayList<>();
    private RecyclerView rv;
    private Toolbar toolbar;
    private TextView deckSize;
    private DatabaseReference databaseReference;


    FlashcardRVAdapter adapter;
    String key;
    String deckName;
    public void addDataLoadedListener(FlashcardRecyclerViewActivity.DataLoadedListener dataLoadedListener) {
        this.dataLoadedListeners.add(dataLoadedListener);
    }

    public interface DataLoadedListener{
        void onDataLoaded();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_recyclerview_activity);



        if (getIntent().hasExtra("key")) {
            key = getIntent().getStringExtra("key");
        }
        if (getIntent().hasExtra("deckName")) {
            deckName = getIntent().getStringExtra("deckName");
        }


        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("decks").child(key);
        //init();
        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        List<Flashcard> flashcardsList = new ArrayList<>();
                                                        for (DataSnapshot flashcardListSnapshot : dataSnapshot.getChildren()) {
                                                            if (flashcardListSnapshot.getChildrenCount()==2) {
                                                                Flashcard flashcard = flashcardListSnapshot.getValue(Flashcard.class);
                                                                flashcardsList.add(flashcard);
                                                            }

                                                        }
                                                        FlashcardRecyclerViewActivity.this.flashcards = flashcardsList;
                                                        FlashcardRecyclerViewActivity.this.dataLoadedListeners.forEach(x -> x.onDataLoaded());
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });


        addDataLoadedListener(new DataLoadedListener() {
            @Override
            public void onDataLoaded() {
                adapter.setFlashcards(flashcards);
            }
        });

        //update toolbar to show the name of deck currently in
        toolbar = findViewById(R.id.flashcard_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(deckName);

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        rv.addItemDecoration(new FlashcardMarginItem(20));

        initializeAdapter();

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

    }

    @Override
    public void onResume()
    {
        //Log.i("Deck in resume", "in resume");
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void initializeAdapter() {
        //get cards of the particular deck, may be use key

        adapter = new FlashcardRVAdapter(flashcards);
        rv.setAdapter(adapter);
    }
}
