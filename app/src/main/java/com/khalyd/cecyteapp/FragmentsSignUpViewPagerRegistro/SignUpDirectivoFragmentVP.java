package com.khalyd.cecyteapp.FragmentsSignUpViewPagerRegistro;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.BlankFragmentEmptyuse;
import com.khalyd.cecyteapp.FragmentsDocenteSignUp.DirectivoSignUp;
import com.khalyd.cecyteapp.FragmentsDocenteSignUp.DocenteSignUp;
import com.khalyd.cecyteapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class SignUpDirectivoFragmentVP extends Fragment {


    private  TextView txtStar;
    private Spinner spinnerTeach;

    public SignUpDirectivoFragmentVP() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.activity_sign_up_teacher, container, false);

            ImageView view = v.findViewById(R.id.loadingViewTeacherSignUp);
            String img = getString(R.string.SecondImage); //Para los logins y registros
            Utils.getImageFromUrl(img,getContext(),view);
            UI(v);
        //Remember put the methods on allViews
            allMethods(v);

            return v;

    }

    private void UI (View v){

        spinnerTeach = v.findViewById(R.id.spinner_Signup_Teache);

    }


    private void allMethods(View v){
        goToSignUpAs(v);
        //    signUpTeacher();

        setSpiner(v);

        ImageView view = v.findViewById(R.id.loadingViewTeacherSignUp);

        String img = getString(R.string.SecondImage);

        Utils.getImageFromUrl(img,getContext(),view);


    }

    private void goToSignUpAs(View v){
        View img1 = v.findViewById(R.id.backToSIGN);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message  = "Pulse en \"Ninguno\" y seleccione su perfil con el que desea registrarse";

                DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(getContext()),message);

            }
        });
    }

    private void setSpiner(View v){

        ArrayList<String> data = new ArrayList<>();
        data.add("Ninguno");
        data.add("Docente");
        data.add("Directivo");

        txtStar = v.findViewById(R.id.select_perfil);


        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getContext(),R.layout.spinner_model,data);

        spinnerTeach.setAdapter(adapterSpinner);

        spinnerTeach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (position){

                    case 0:
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        fragmentTransaction.replace(R.id.containerSignUp_Teach,new BlankFragmentEmptyuse()).commit();
                        txtStar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.replace(R.id.containerSignUp_Teach,new DocenteSignUp()).commit();
                        txtStar.setVisibility(View.INVISIBLE);
                        Toasty.info(getContext(),"Deslice hacia arriba", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.replace(R.id.containerSignUp_Teach,new DirectivoSignUp()).commit();
                        txtStar.setVisibility(View.INVISIBLE);
                        Toasty.info(getContext(),"Deslice hacia arriba", Toast.LENGTH_SHORT).show();
                        break;
                    //Check que no me haya revolvido
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });

    }








}
