package com.khalyd.cecyteapp.ActivityParent;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SharedPreferences preferences;

    //FragmentParent

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        UI(view);

        Utils.setrecycler(recyclerView,getContext(),null,progressBar,true,preferences);
        //True porq es para los parents

        return view;
    }

    private void UI(View v){

        recyclerView = v.findViewById(R.id.recycler_parent_News);
        progressBar = v.findViewById(R.id.progressBar4);
        preferences = Utils.getMySharedPreferences(getContext());


    }





}
