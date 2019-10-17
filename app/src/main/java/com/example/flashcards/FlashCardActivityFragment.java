package com.example.flashcards;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlashCardActivityFragment extends Fragment {

    public FlashCardActivityFragment() {
    }

    public static Fragment newInstance(Deck deckName) {
        Bundle args = new Bundle();
        args.putSerializable("arg_deck_name", (Serializable) deckName);

        FlashCardActivityFragment fragment = new FlashCardActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flash_card, container, false);
    }
}
