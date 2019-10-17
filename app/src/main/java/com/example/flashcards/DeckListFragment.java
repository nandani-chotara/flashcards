package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DeckListFragment extends androidx.fragment.app.Fragment {

    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private ArrayList<Deck> decks = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deck_list_fragment_layout, container,false);

        for(int i=0;i<10;i++){
            Deck d = new Deck("Deck number " + i);
            decks.add(d);
        }

        mRecyclerView = (RecyclerView) v.findViewById(R.id.deck_list_fragment_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ItemAdapter(decks);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView deckName;
        private Deck deckObj;

        public ItemHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.deck_list_item_layout, parent, false));
            itemView.setOnClickListener(this);
            deckName = (TextView) itemView.findViewById(R.id.deck_name);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
            Intent intent = FlashCardActivity.newIntent(getActivity(), null);
            //intent.putExtra("arg_food_name", foodObj.getFoodName());
            startActivity(intent);
        }
    }

    public void addFood(Deck deck)
    {
        decks.add(deck);
    }
    public ArrayList<Deck> getFoods()
    {
        return decks;
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{

        private ArrayList<Deck> mDecks;

        public ItemAdapter(ArrayList<Deck> tempDecks) {
            mDecks = tempDecks;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ItemHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            Deck deck = mDecks.get(position);

            holder.deckObj = deck;
            holder.deckName.setText(deck.getDeckName());
        }

        @Override
        public int getItemCount() {
            return mDecks.size();
        }
    }
}
