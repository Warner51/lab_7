package com.example.lab_7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private TextView textViewName;
    private TextView textViewHeight;
    private TextView textViewMass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView textViewName = view.findViewById(R.id.nameFill);
        TextView textViewHgt = view.findViewById(R.id.hgtFill);
        TextView textViewMass = view.findViewById(R.id.massFill);

        Bundle bdl = getArguments();
        if(bdl != null) {
            String name = bdl.getString("name");
            String height = bdl.getString("height");
            String mass = bdl.getString("mass");

            textViewName.setText(name);
            textViewHgt.setText(height);
            textViewMass.setText(mass);
        }
        return view;
    }
}