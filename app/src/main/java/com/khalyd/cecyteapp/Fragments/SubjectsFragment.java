package com.khalyd.cecyteapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khalyd.cecyteapp.ActivityTeacher.MyGroupsFragment;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.PandS;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.ProfessionalModule;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.FilosofySubject;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.SubejectsCompletes;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.TutoriasSubjects;
import com.khalyd.cecyteapp.Adapters.MyAdapter;
import com.khalyd.cecyteapp.Models.Materias;
import com.khalyd.cecyteapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
//Todo esto estaba en el main activity
public class SubjectsFragment extends Fragment {


    private TextView textView;


    //Debo de checar queeste en el manifest el parentactivyti para que no haya problemas

    public SubjectsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);
        UI(view);

        String ms ="En cuantó a tu perfíl (Estudiante) esperamos que desde esta pestaña (Materias) puedas acceder a grupos por medio de un código elaborado " +
                "por tu profesor y así poder ver archivos, videos u otro tipo de material compartido por el profesor";

        MyGroupsFragment.setAlerDialog(textView,getContext(),ms);


        return view;
    }

    private void UI(View v){

        textView =  v.findViewById(R.id.materiastxt);

    }




}
