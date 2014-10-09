package com.endarija;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by telolahy on 06/10/14.
 */
public class DictionaryModel {

    private ArrayList<Word> wordsList;

    public DictionaryModel(Context context) {

        wordsList = new ArrayList<Word>();

        try {
            // open the file for reading
            InputStream instream = context.getAssets().open("data.txt");

            // prepare the file for reading
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;

            // read every line of the file into the line-variable, on line at the time
            while ((line = buffreader.readLine()) != null) {

                String fields[] = line.split("\t");
                String fr = fields[0];
                String ar = fields[1];

                // copy line in words
                wordsList.add(new Word(fr, ar));
            }

            // close the file.
            instream.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    public ArrayList<Word> allWords() {

        return wordsList;
    }

    public  ArrayList<Word>  wordsWithKey(String key) {

        ArrayList<Word> resultList = new ArrayList<Word>();

        for(Word element : wordsList) {

            String elementInLowerCase = element.valueFr.toLowerCase();
            String keyInLowerCase = key.toLowerCase();

            if (elementInLowerCase.contains(keyInLowerCase)) {

                resultList.add(element);
            }
        }

        return resultList;
    }

}
