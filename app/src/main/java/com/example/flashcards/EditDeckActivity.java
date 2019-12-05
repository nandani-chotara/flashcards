package com.example.flashcards;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditDeckActivity extends AppCompatActivity {

    private TextInputEditText mDeckName;
    private TextInputEditText mDescription;
    private Button mEditButton;
    private RadioGroup radioGroup;

    private String deckName;
    private String deckDescription;
    private String key;
    private String deckColor="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        key = getIntent().getStringExtra("key");
        deckName = getIntent().getStringExtra("deckName");
        deckDescription = getIntent().getStringExtra("deckDescription");

        mDeckName = (TextInputEditText)findViewById(R.id.added_deck_name_edit);
        mDescription = (TextInputEditText)findViewById(R.id.edited_deck_desp);
        mEditButton = (Button) findViewById(R.id.DeckBtnEdit);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupColorEdit);

        mDeckName.setText(deckName);
        mDescription.setText(deckDescription);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(i);
                int id = radioGroup.getCheckedRadioButtonId();
                if (null != rb) {
                    switch(id){
                        case R.id.radioButton1:
                            deckColor = "pink";
                            break;
                        case R.id.radioButton2:
                            deckColor = "yellow";
                            break;
                        case R.id.radioButton3:
                            deckColor = "purple";
                            break;
                        case R.id.radioButton4:
                            deckColor = "orange";
                            break;
                        case R.id.radioButton5:
                            deckColor = "green";
                            break;
                    }
                }
            }
        });

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deck deck = new Deck();
                deck.setDeckName(mDeckName.getText().toString());
                deck.setDescription(mDescription.getText().toString());
                deck.setDeckColor(deckColor);
                deck.setUuid(key);
                DeckRepository.getInstance().updateDeck(key, deck);
                finish();

            }
        });
    }
}
