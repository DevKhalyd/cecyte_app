package com.khalyd.cecyteapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khalyd.cecyteapp.ActivityTeacher.MyGroupsFragment;
import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calificaciones extends Fragment {


    private TextView txtInfo;


    public Calificaciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files, container, false);
        // Inflate the layout for this fragment
        UI(view);
        String ms = "En esta opción podras ver tus calificaciones dependiendo de la parte en la que te encuentres (1ra,2da,3ra)." +
                " Esta opción dependerá del directivo, ya qué ellos autorizarán el uso de esta opción ";

        MyGroupsFragment.setAlerDialog(txtInfo,getContext(),ms);
        return view;
    }

    private void UI(View v){

        txtInfo = v.findViewById(R.id.touchHereCalifications);
    }


}
