package com.example.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DeckListFragment extends androidx.fragment.app.Fragment implements DeckAdapterListener{

    private List<Deck> decksList = new ArrayList<>();
    RecyclerView mrecyclerView;
    private ItemAdapter mAdapter;
    private DeckRepository deckRepository;

    private CoordinatorLayout coordinatorLayout;


    public static final String mypreference = "mypref";
    public static final String TEXT = "text";

    Toolbar toolbar;

    @Override
    public void onDeckSelected(Deck deck) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deckRepository = DeckRepository.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deck_list_fragment_layout, container,false);


        coordinatorLayout = (CoordinatorLayout)v.findViewById(R.id.coordinatorLayout);
        //decksList = DeckStorage.get(getActivity()).getDecks();

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        toolbar.setTitle("Decks");
        toolbar.inflateMenu(R.menu.toolbar_search_menu);
        Menu menu = toolbar.getMenu();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.i("query changed", "query changed");
                        mAdapter.getFilter().filter(newText);
                        return false;
                    }
                });
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mAdapter.setDecks(deckRepository.getDecks());
                return true;
            }
        });

        mrecyclerView = v.findViewById(R.id.recycler_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.i("query item adapter","going in item adapter");
        mAdapter = new ItemAdapter(decksList, this);
        mrecyclerView.setAdapter(mAdapter);

        FloatingActionButton addButon = v.findViewById(R.id.add_food_item_button);
       addButon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), AddDeckActivity.class);
               startActivity(intent);
           }
       });

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
        mAdapter.setDecks(deckRepository.getDecks());
        mAdapter.notifyDataSetChanged();

    }

    private class ItemHolder extends RecyclerView.ViewHolder
    {
        private TextView deckName;
        private TextView deckDesp;
        private ImageButton options_btn;
        private Button add_cardsBtn;


        public ItemHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.deck_list_item_layout2, parent, false));
            //itemView.findViewById(R.id.constraint).setBackgroundColor(Color.parseColor("#ef9a9a"));
            deckName = (TextView) itemView.findViewById(R.id.deck_name);
            deckDesp = (TextView) itemView.findViewById(R.id.deck_desp);
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
                                    String key=d.getUuid();
                                    Intent intent = new Intent(getContext(), EditDeckActivity.class);
                                    intent.putExtra("key", key);
                                    intent.putExtra("deckName", deckName.getText().toString());
                                    intent.putExtra("deckDescription", deckDesp.getText().toString());
                                    startActivity(intent);
                                    return true;
                                case R.id.delete:
                                    DeckRepository.getInstance().removeDeck(d);

                                    Snackbar snackbar = Snackbar
                                            .make(coordinatorLayout, "Deck is deleted", Snackbar.LENGTH_LONG);
                                    snackbar.show();
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
                    Deck d = deckRepository.getDecks().get(getAdapterPosition());
                    String key=d.getUuid();
                    Intent intent = new Intent(getActivity(), FlashcardRecyclerViewActivity.class);
                    intent.putExtra("key",key);
                    startActivity(intent);
                }
            });

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.listener.onDeckSelected(mAdapter.mDeckListFull.get(getAdapterPosition()));
                }
            });*/
        }

        public void bind(Deck deck){
            deckName.setText(deck.getDeckName());
            deckDesp.setText(deck.getDescription());

            if(deck.getDeckColor().length()!=0) {
                if (deck.getDeckColor().equals("pink")) {
                    itemView.findViewById(R.id.constraint).setBackgroundResource(R.color.pink);
                } else if (deck.getDeckColor().equals("purple")) {
                    itemView.findViewById(R.id.constraint).setBackgroundResource(R.color.purple);
                } else if (deck.getDeckColor().equals("orange")) {
                    itemView.findViewById(R.id.constraint).setBackgroundResource(R.color.orange);
                } else if (deck.getDeckColor().equals("green")) {
                    itemView.findViewById(R.id.constraint).setBackgroundResource(R.color.green);
                } else if (deck.getDeckColor().equals("yellow")) {
                    itemView.findViewById(R.id.constraint).setBackgroundResource(R.color.yellow);
                }
            }
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> implements Filterable {  //this class is responsible for creating deck item view, the above one.

        private List<Deck> mDecks;
        private List<Deck> mDeckListFull;
        private DeckAdapterListener listener;

        public ItemAdapter(List<Deck> tempDeck, DeckAdapterListener listener)
        {
            this.listener = listener;
            this.mDecks = tempDeck;
            mDeckListFull = new ArrayList<>(tempDeck);
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ItemHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
            holder.bind(mDeckListFull.get(position));
        }

        @Override
        public int getItemCount() {
            return mDeckListFull.size();
        }

        public void setDecks(List<Deck> decks){
            this.mDeckListFull = decks;
            this.notifyDataSetChanged();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    List<Deck> filteredList = new ArrayList<>();
                    String charString = constraint.toString();
                    if(charString.isEmpty()){
                        Log.i("query in filer", "in filter empty");
                        filteredList.addAll(mDeckListFull);

                    }
                    else{
                        Log.i("query in filer", "in filter not empty");
                        for(Deck row : mDeckListFull){
                            Log.i("query deck name", row.getDeckName());
                            if(row.getDeckName().toLowerCase().contains(charString.toLowerCase())){
                                Log.i("query in filer", "row found");
                                filteredList.add(row);
                            }
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = filteredList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mDecks.clear();
                    mDecks.addAll((List)results.values);
                    mAdapter.setDecks(mDecks);
                    notifyDataSetChanged();
                }
            };
        }
    }

}
