package com.example.randriamasy.endarija;

/**
 * Created by telolahy on 06/10/14.
 */
public class DictionaryModel {


    public String[] allWords() {

        // TODO: load from file

        String[] items = { "Milk:lait", "Butter:beurre", "Yogurt", "Toothpaste", "Ice Cream",
                "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream",
                "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream"};
        return items;
    }

    public String[] wordswithKey(String key) {

        String[] items = { "Toothpaste", "Ice Cream"};
        return items;
    }

}
