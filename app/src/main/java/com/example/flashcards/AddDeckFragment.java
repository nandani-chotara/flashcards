package com.example.flashcards;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;


public class AddDeckFragment extends Fragment {

    private TextInputEditText mDeckName;
    private Button mAddButton;
    //private RadioGroup radioGroup;
    private String deckName;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.add_deck_fragment_layout, container, false);
        mDeckName = (TextInputEditText) v.findViewById(R.id.added_deck_name);
        //radioGroup = (RadioGroup) v.findViewById(R.id.radioGroupColor);
        //radioGroup.clearCheck();


        mAddButton = (Button) v.findViewById(R.id.addToDeckBtn);

        mDeckName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                deckName = charSequence.toString();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(i);
                if (null != rb && i > -1) {
                    ConstraintLayout lay = (ConstraintLayout) v.findViewById(R.id.parent_layout);
                    lay.findViewById(R.id.constraint).setBackgroundColor(Color.RED);
                    //lay.setBackgroundColor(Color.RED);
                    // lay.setBackgroundColor(Color.RED);
                    //lay.setBackgroundColor(Color.parseColor("#FF7B82"));
                    Toast.makeText(getContext(), i , Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deck deck = new Deck(deckName);
                DeckRepository.getInstance().addDeck(deck);
                //DeckStorage.get(getActivity()).addDeck(deck);
                getActivity().finish();
            }
        });

        return v;
    }


}
