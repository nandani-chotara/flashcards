package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DeckFragment extends androidx.fragment.app.Fragment {
    @Nullable
    private Button viewContentsButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deck_fragment_layout, container, false);

        viewContentsButton = (Button) v.findViewById(R.id.view_contents_button);
        viewContentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DeckListActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
