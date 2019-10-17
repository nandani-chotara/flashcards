package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DeckListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_list_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.deck_list_fragment_container);
        if(f == null)
        {
            f = new DeckListFragment();
            fm.beginTransaction().add(R.id.deck_list_fragment_container, f).commit();
        }
    }
}
