package com.khalyd.cecyteapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CecyteTheOption extends Fragment {


    public CecyteTheOption() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cecyte_the_option, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}
