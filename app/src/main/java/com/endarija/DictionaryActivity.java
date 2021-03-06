package com.endarija;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


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

        // When using the support library, the setOnActionExpandListener() method is
        // static and accepts the MenuItem object as an argument
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                reloadListViewWithAllWords();
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showAbout() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("A propos de EnDarija");
        ad.setMessage("Traduction Français/Darija avec plus de 1000 mots et expressions quotidiens.");
        ad.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ad.setPositiveButton("Contactez-nous", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"malala.randriamasy@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Objet:");
                email.putExtra(Intent.EXTRA_TEXT, "Message: ");
                email.setType("message/rfc822");

                startActivityForResult(Intent.createChooser(email, "Choose an Email client:"),
                        1);
            }
        });
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
