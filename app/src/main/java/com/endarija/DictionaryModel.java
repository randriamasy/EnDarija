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

    private ArrayList<String> wordsList;

    public DictionaryModel(Context context) {

        wordsList = new ArrayList<String>();

        InputStream instream = null;
        try {
            // open the file for reading
            instream = context.getAssets().open("data.txt");

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
                wordsList.add(fr + ":" + ar);
            }

            // close the file.
            instream.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    public String[] allWords() {

        return wordsList.toArray(new String[wordsList.size()]);
    }

    public String[] wordswithKey(String key) {

        String[] items = {"Toothpaste", "Ice Cream"};
        return items;
    }

}
