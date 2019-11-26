package com.example.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  FlashCardActivity extends AppCompatActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ListView cardview;
    Button Addbutton;
    EditText GetValue;
    String[] ListElements = new String[] {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        cardview = (ListView) findViewById(R.id.cardview1);
        Addbutton = (Button) findViewById(R.id.button1);
        GetValue = (EditText) findViewById(R.id.editText1);

        final List< String > ListElementsArrayList = new ArrayList< String >
                (Arrays.asList(ListElements));


        final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                (FlashCardActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList);

        cardview.setAdapter(adapter);

        Addbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListElementsArrayList.add(GetValue.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

    class flashCard {
        String question;
        String answer;
        int id;

        flashCard(String question, String answer, int id) {
            this.question = question;
            this.answer = answer;
            this.id = id;
        }

    }

    private List<FlashCardActivity.flashCard> flashcards;


    private void initializeData() {
        flashcards = new ArrayList<>();
        flashcards.add(new FlashCardActivity.flashCard("1+1", "2",0));
        flashcards.add(new FlashCardActivity.flashCard("1+2", "3",1));
        flashcards.add(new FlashCardActivity.flashCard("56 + 2", "58",2));


    }



}
