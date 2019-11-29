package com.example.flashcards;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class FlashcardAddActivity extends AppCompatActivity {

    Button flashcardAddBtn;
    TextInputEditText textInputEditQuestion, textInputEditAnswer;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_add);

        if (getIntent().hasExtra("key")) {
            key = getIntent().getStringExtra("key");
        }


        textInputEditQuestion = findViewById(R.id.textInputEditQuestion);
        textInputEditAnswer = findViewById(R.id.textInputEditAnswer);

        flashcardAddBtn = findViewById(R.id.flashcardAddBtn);
        flashcardAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Flashcard newCard = new Flashcard();
                newCard.setQuestion(textInputEditQuestion.getText().toString());
                newCard.setAnswer(textInputEditAnswer.getText().toString());
                DeckRepository.getInstance().addCardstoDeck(key, newCard);
                //FlashcardRepository.getInstance().addFlashcard(newCard);
                finish();
            }
        });

//        Intent i = getIntent();
//        ArrayList<?> recievedFlashcards = (ArrayList<?>) i.getSerializableExtra("flashcards");
//        ArrayList<String> returnedFlashcards = new ArrayList<String>();

    }

}
