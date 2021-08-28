package com.khalyd.cecyteapp.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khalyd.cecyteapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schoolships extends Fragment {





    public Schoolships() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schoolships, container, false);
        // Inflate the layout for this fragment

        toPagWebSchool(view);
        toMoreInfo(view);

        return view;

    }

    private void toPagWebSchool(View view) {
        ImageView imgBus = (ImageView) view.findViewById(R.id.transport);
        imgBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.becasmediasuperior.sep.gob.mx/#");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageView imgStudents = (ImageView) view.findViewById(R.id.two_students);
        imgStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.becasmediasuperior.sep.gob.mx/#");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageView imgStudents2 = (ImageView) view.findViewById(R.id.backpack_schools);
        imgStudents2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.becasmediasuperior.sep.gob.mx/#");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void toMoreInfo(View view){

        View img1 = view.findViewById(R.id.entrepreneur);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.becasmediasuperior.sep.gob.mx/#");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent1);
            }
        });

        View img2 = view.findViewById(R.id.circle_background);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.becasmediasuperior.sep.gob.mx/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }

}