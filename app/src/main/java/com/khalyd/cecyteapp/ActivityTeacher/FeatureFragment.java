package com.khalyd.cecyteapp.ActivityTeacher;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.khalyd.cecyteapp.Class.ComplemetsUrlandConstansFinal;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureFragment extends Fragment {

    //Teacher MAINFragment

    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    public FeatureFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Este es difente al del parent

        View v = inflater.inflate(R.layout.fragment_feature, container, false);
        UI(v);

        Utils.setrecycler(recyclerView,getContext(), ComplemetsUrlandConstansFinal.code_Teacher,progressBar,false,null);
        //Esto es de los teachers
        return v;

    }

    private void UI(View v){

        recyclerView = v.findViewById(R.id.recyclerView_Docente);
        progressBar = v.findViewById(R.id.progressBarDocente);

    }

}
