package com.example.lab_7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CharListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> characterNames;

    public CharListAdapter(Context context, ArrayList<String> characterNames) {
        this.context = context;
        this.characterNames = characterNames;
    }

    @Override
    public int getCount() {
        return characterNames.size();
    }

    @Override
    public Object getItem(int position) {
        return characterNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.full_screen, null);
        }

        TextView characterNameTextView = listItemView.findViewById(R.id.charNameTV);
        characterNameTextView.setText(characterNames.get(position));

        return listItemView;
    }
}
