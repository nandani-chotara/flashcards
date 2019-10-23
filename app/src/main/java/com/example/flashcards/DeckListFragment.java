package com.example.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DeckListFragment extends androidx.fragment.app.Fragment {

    //private ItemAdapter mAdapter;
    private ArrayList<String> decks = new ArrayList<>();

    public DeckListFragment(ArrayList<String> mdeckNames) {
        decks = mdeckNames;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deck_list_fragment_layout, container,false);

        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), decks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
            Intent intent = new Intent(getActivity(), FlashCardActivity.class);
            //intent.putExtra("arg_food_name", foodObj.getFoodName());
            startActivity(intent);
        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

        private ArrayList<String> mDeckNames = new ArrayList<>();
        private Context mContext;

        public RecyclerViewAdapter(Context Context, ArrayList<String> DeckNames) {
            mDeckNames = DeckNames;
            mContext = Context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_list_item_layout,parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.deckName.setText(mDeckNames.get(position));
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, FlashCardActivity.class);
                    intent.putExtra("deck_name", mDeckNames.get(position));
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDeckNames.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView deckName;
            RelativeLayout parentLayout;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                deckName = itemView.findViewById(R.id.deck_name);
                parentLayout = itemView.findViewById(R.id.parent_layout);
            }
        }
    }
}
