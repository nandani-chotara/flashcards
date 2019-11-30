package com.example.flashcards;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class DeckActivity extends AppCompatActivity {

    //vars
    private ArrayList<String> mdeckNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_list_fragment_container);

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

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.deck_list_fragment_container);
        if (f==null){
            f = new DeckListFragment();
            fm.beginTransaction().add(R.id.deck_list_fragment_container, f).commit();
        }
        //initRecyclerView();
    }
   /* private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mdeckNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }*/

}
