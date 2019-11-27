package com.example.flashcards;

import android.content.Context;
import android.icu.util.ULocale;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class DeckStorage {
    private static DeckStorage sDeckStorage;
    private ArrayList<Deck> decksList = new ArrayList<>();

    public static DeckStorage get(Context context)
    {
        if(sDeckStorage == null)
        {
            sDeckStorage = new DeckStorage(context);
        }
        return sDeckStorage;
    }

    private DeckStorage(Context context)
    {

        for(int i = 0; i < 10; ++i)
        {
            Deck d = new Deck("Deck number " + i);
            addDeck(d);

        }

    }

    public ArrayList<Deck> getDecks(){
        return decksList;
    }

    public void addDeck(Deck deck)
    {
        decksList.add(deck);
    }

    public void removeDeck(Deck deck){ decksList.remove(deck);}
}
