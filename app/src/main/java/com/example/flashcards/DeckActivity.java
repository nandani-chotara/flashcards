package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class DeckActivity extends AppCompatActivity {

    //vars
    private ArrayList<String> mdeckNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

       /* FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.deck_fragment_container);
        if (f==null){
            f = new DeckFragment();
            fm.beginTransaction().add(R.id.deck_fragment_container, f).commit();
        }*/

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeckActivity.this, AddDeckActivity.class);
                startActivity(intent);
            }
        });*/
        initialise();
    }

    private void initialise(){
        mdeckNames.add("CP104");
        mdeckNames.add("CP106");
        mdeckNames.add("CP164");
        mdeckNames.add("CP264");
        mdeckNames.add("CP213");
        mdeckNames.add("CP212");
        mdeckNames.add("CP470");
        mdeckNames.add("CP312");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");
        mdeckNames.add("CP372");


        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.deck_recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mdeckNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
