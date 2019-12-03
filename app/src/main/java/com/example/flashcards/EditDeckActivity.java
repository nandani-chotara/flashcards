package com.example.flashcards;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditDeckActivity extends AppCompatActivity {

    private TextInputEditText mDeckName;
    private Button mAddButton;

    private String deckName;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        key = getIntent().getStringExtra("key");
        deckName = getIntent().getStringExtra("deckName");

        mDeckName = (TextInputEditText)findViewById(R.id.added_deck_name_edit);
        mAddButton = (Button) findViewById(R.id.DeckBtnEdit);

        mDeckName.setText(deckName);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deck deck = new Deck();
                deck.setDeckName(mDeckName.getText().toString());
                deck.setUuid(key);

                new DeckRepository().updateDeck(key, deck);
                finish();

            }
        });
    }
}
