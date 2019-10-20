package com.example.flashcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deckName.setText(mDeckNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {

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
