package com.example.flashcards;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;


public class AddDeckFragment extends Fragment {

    private TextInputEditText mDeckName;
    private TextInputEditText mDeckDesp;
    private Button mAddButton;
    private RadioGroup radioGroup;
    private String deckName;
    private String deckDesp;
    private String deckColor="";




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
        mDeckDesp = (TextInputEditText) v.findViewById(R.id.added_deck_desp);
        radioGroup = (RadioGroup) v.findViewById(R.id.radioGroupColor);
        radioGroup.clearCheck();


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

        mDeckDesp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    deckDesp = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Deck deck = new Deck(deckName);
                DeckRepository.getInstance().addDeck(deckName, deckDesp, deckColor);
                //DeckStorage.get(getActivity()).addDeck(deck);
                getActivity().finish();
            }
        });

        return v;
    }


}
