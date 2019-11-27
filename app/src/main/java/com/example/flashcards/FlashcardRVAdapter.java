package com.example.flashcards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashcardRVAdapter extends RecyclerView.Adapter<FlashcardRVAdapter.FlashcardViewHolder> {

    public static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView question;
        TextView answer;
        Button seeAnswerButton, hideAnswerButton;


        FlashcardViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
            seeAnswerButton = itemView.findViewById(R.id.seeAnswerButton);
            hideAnswerButton = itemView.findViewById(R.id.hideAnswerButton);
            seeAnswerButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    answer.setVisibility(View.VISIBLE);
                    hideAnswerButton.setVisibility(View.VISIBLE);
                    seeAnswerButton.setVisibility(View.GONE);
                }
            });
            hideAnswerButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    answer.setVisibility(View.GONE);
                    hideAnswerButton.setVisibility(View.GONE);
                    seeAnswerButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    List<Flashcard> flashcards;

    FlashcardRVAdapter(List<Flashcard> flashcards){
        this.flashcards = flashcards;
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    @Override
    public FlashcardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.flashcard_item, viewGroup, false);
        FlashcardViewHolder flashcardViewHolder = new FlashcardViewHolder(view);
        return flashcardViewHolder;
    }

    @Override
    public void onBindViewHolder(FlashcardViewHolder flashcardViewHolder, int i) {
        flashcardViewHolder.question.setText(flashcards.get(i).getQuestion());
        flashcardViewHolder.answer.setText(flashcards.get(i).getAnswer());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}