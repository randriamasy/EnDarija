package com.endarija;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RANDRIAMASY on 09/10/2014.
 */
public class DictionaryAdapter extends ArrayAdapter<Word>{

    public DictionaryAdapter(Context context, int itemResource, List<Word> items) {
        super(context, itemResource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Inflate the view
        LinearLayout itemView;
        if(convertView==null)
        {
            itemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(R.layout.item_layout, itemView, true);
        }
        else
        {
            itemView = (LinearLayout) convertView;
        }

        // Récupérer le mot à la position courante
        Word mot = getItem(position);

        // Setup data on the item
        TextView textViewFr =(TextView)itemView.findViewById(R.id.textRight);
        TextView textViewAr =(TextView)itemView.findViewById(R.id.textLeft);

        //Assign the appropriate data from our alert object above
        textViewFr.setText(mot.valueFr);
        textViewAr.setText(mot.valueAr);

        return itemView;
    }

}
