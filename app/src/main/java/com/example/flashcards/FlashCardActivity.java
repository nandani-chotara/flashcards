package com.example.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;

public class FlashCardActivity extends AppCompatActivity {

    public static Intent newIntent(Context packageContext, Deck deckObj) {
        Intent intent = new Intent(packageContext, FlashCardActivity.class);
        intent.putExtra("args_deck_id", (Serializable) deckObj);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_fragment_container);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        FragmentManager fm = getSupportFragmentManager();

        Deck deckName = (Deck) getIntent().getSerializableExtra("args_deck_id");

        Fragment f = fm.findFragmentById(R.id.flashcard_fragment_container);
        if (f == null) {
            f = FlashCardActivityFragment.newInstance(deckName);
            fm.beginTransaction().add(R.id.flashcard_fragment_container, f).commit();
        }
    }

}
