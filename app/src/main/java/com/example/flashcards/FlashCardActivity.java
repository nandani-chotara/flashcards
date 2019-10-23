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
import android.widget.TextView;

import java.io.Serializable;

public class FlashCardActivity extends AppCompatActivity {

    /*public static Intent newIntent(Context packageContext, Deck deckObj) {
        Intent intent = new Intent(packageContext, FlashCardActivity.class);
        intent.putExtra("args_deck_id", (Serializable) deckObj);
        return intent;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("deck_name")){
            String deckname = getIntent().getStringExtra("deck_name");
            setName(deckname);
        }

    }

    private void setName(String deckname){
        TextView deckName = findViewById(R.id.deckNameFlashcardAvtivity);
        deckName.setText(deckname);
    }

}
