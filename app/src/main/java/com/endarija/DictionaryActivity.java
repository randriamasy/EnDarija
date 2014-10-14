package com.endarija;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DictionaryActivity extends ActionBarActivity {

    private DictionaryModel model;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        model = new DictionaryModel(this);
        listView = (ListView) findViewById(R.id.listView1);

        reloadListViewWithAllWords();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.action_search));
        super.onCreateOptionsMenu(menu);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                DemoAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void DemoAlertDialog(){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("A propos");
        ad.setMessage("Traduction Français/Darija avec plus de 1000 mots et expressions quotidiens.");
        ad.show();
    }
    private void reloadListViewWithAllWords() {

        ArrayList<Word> listeTousMots = model.allWords();
        DictionaryAdapter adapter = new DictionaryAdapter(this, R.layout.item_layout, listeTousMots);
        listView.setAdapter(adapter);

    }

    private void reloadListViewWithWordsContainingKey(String key) {

        ArrayList<Word> listeMotsRecheche = model.wordsWithKey(key);
        DictionaryAdapter adapter = new DictionaryAdapter(this, R.layout.item_layout, listeMotsRecheche);
        listView.setAdapter(adapter);
    }

}
