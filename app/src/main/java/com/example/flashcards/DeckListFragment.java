package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DeckListFragment extends androidx.fragment.app.Fragment {

    //private ItemAdapter mAdapter;
    private List<Deck> decksList = new ArrayList<>();
    RecyclerView mrecyclerView;
    private ItemAdapter mAdapter;
    private DeckRepository deckRepository;
    /*public DeckListFragment(ArrayList<String> mdeckNames) {

        decks = mdeckNames;
    }*/

    public static final String mypreference = "mypref";
    public static final String TEXT = "text";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deckRepository = DeckRepository.getInstance();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deck_list_fragment_layout, container,false);

        //decksList = DeckStorage.get(getActivity()).getDecks();

        mrecyclerView = v.findViewById(R.id.recycler_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ItemAdapter(decksList);
        mrecyclerView.setAdapter(mAdapter);

        FloatingActionButton addButon = v.findViewById(R.id.add_food_item_button);
       addButon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), AddDeckActivity.class);
               startActivity(intent);
           }
       });

       /*deckRepository.addDataLoadedListener(() -> {
        mAdapter.setDecks(deckRepository.getDecks());
       });*/


        deckRepository.addDataLoadedListener(new DeckRepository.DataLoadedListener() {
            @Override
            public void onDataLoaded() {
                mAdapter.setDecks(deckRepository.getDecks());
            }
        });
        return v;
    }

    @Override
    public void onResume()
    {
        Log.i("Deck in resume", "in resume");
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    private class ItemHolder extends RecyclerView.ViewHolder
    {
        private TextView deckName;
        private ImageButton options_btn;
        private Button add_cardsBtn;

        public ItemHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.deck_list_item_layout, parent, false));
            deckName = (TextView) itemView.findViewById(R.id.deck_name);

            //Deck d = deckRepository.getDecks().get(getAdapterPosition());

            options_btn = (ImageButton) itemView.findViewById(R.id.options_imgBtn);
            options_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(getContext(), view);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Deck d = deckRepository.getDecks().get(getAdapterPosition());
                            switch(menuItem.getItemId()){
                                case R.id.edit:
                                    //String key = d.getUuid();
                                    String key=d.getUuid();
                                    Intent intent = new Intent(getContext(), EditDeckActivity.class);
                                    intent.putExtra("key", key);
                                    intent.putExtra("deckName", deckName.getText().toString());
                                    startActivity(intent);
                                    return true;
                                case R.id.delete:

                                    //Deck deck = new Deck(deckName.getText().toString());
                                    //DeckStorage.get(getActivity()).removeDeck(deck);
                                    DeckRepository.getInstance().removeDeck(d);
                                    //decksList.remove(deck);

                                    mAdapter.notifyDataSetChanged();
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
                    popup.show();
                }
            });


            add_cardsBtn = (Button) itemView.findViewById(R.id.add_cardsBtn);
            add_cardsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                    Deck d = deckRepository.getDecks().get(getAdapterPosition());
                    String key=d.getUuid();
                    Intent intent = new Intent(getActivity(), FlashcardRecyclerViewActivity.class);
                    //intent.putExtra("arg_deck_name", d.getDeckName());
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
            });
        }

        public void bind(Deck deck){
            deckName.setText(deck.getDeckName());
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{  //this class is responsible for creating deck item view, the above one.

        private List<Deck> mDecks;
        public ItemAdapter(List<Deck> tempDeck)
        {
            this.mDecks = tempDeck;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ItemHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
            //String s = mDecks.get(position).getDeckName();
            holder.bind(mDecks.get(position));
        }

        @Override
        public int getItemCount() {
            return mDecks.size();
        }

        public void setDecks(List<Deck> decks){
            this.mDecks = decks;
            this.notifyDataSetChanged();
        }

    }
}
