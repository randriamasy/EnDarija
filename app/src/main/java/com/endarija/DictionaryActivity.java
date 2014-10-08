package com.endarija;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class DictionaryActivity extends ActionBarActivity {

    private DictionaryModel model;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        model = new DictionaryModel(this);
        listView1 = (ListView) findViewById(R.id.listView1);

        reloadListViewWithAllWords();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // TODO: remove keyboard
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // update listView content
                reloadListViewWithWordsContainingKey(s);
                return false;
            }
        });

        return true;
    }


    private void reloadListViewWithAllWords() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, model.allWords());
        listView1.setAdapter(adapter);

    }

    private void reloadListViewWithWordsContainingKey(String key) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, model.wordsWithKey(key));
        listView1.setAdapter(adapter);
    }

}
