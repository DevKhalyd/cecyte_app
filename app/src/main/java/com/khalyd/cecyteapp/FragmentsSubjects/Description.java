package com.khalyd.cecyteapp.FragmentsSubjects;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Description extends Fragment {


    public Description() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pays_done, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}
