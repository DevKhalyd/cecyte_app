package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.khalyd.cecyteapp.ActivitiesDeprecated.SignUpAs;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.BlankFragmentEmptyuse;
import com.khalyd.cecyteapp.FragmentsDocenteSignUp.DirectivoSignUp;
import com.khalyd.cecyteapp.FragmentsDocenteSignUp.DocenteSignUp;
import com.khalyd.cecyteapp.R;

import java.util.ArrayList;

public class SignUpTeacher extends AppCompatActivity {

    /*
    private  TextView txtStar;
    private Spinner spinnerTeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_teacher);
        UI();
        //Remember put the methods on allViews
        allMethods();

    }

    private void UI (){

        spinnerTeach = findViewById(R.id.spinner_Signup_Teache);


    }


    private void allMethods(){
        goToSignUpAs();
    //    signUpTeacher();

        setSpiner();

        ImageView view = findViewById(R.id.loadingViewTeacherSignUp);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }

    private void goToSignUpAs(){
        View img1 = findViewById(R.id.backToSIGN);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpTeacher.this,SignUpAs.class);
                startActivity(i);
            }
        });
    }

    private void setSpiner(){

        ArrayList<String> data = new ArrayList<>();
        data.add("Ninguno");
        data.add("Docente");
        data.add("Directivo");

        txtStar = findViewById(R.id.select_perfil);


        ArrayAdapter<String>  adapterSpinner = new ArrayAdapter<>(this,R.layout.spinner_model,data);

        spinnerTeach.setAdapter(adapterSpinner);

        spinnerTeach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                FragmentManager fragmentManager = getSupportFragmentManager();
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
                        break;

                    case 2:

                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.replace(R.id.containerSignUp_Teach,new DirectivoSignUp()).commit();
                        txtStar.setVisibility(View.INVISIBLE);
                        break;

                        //Check que no me haya revolvido

                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {




            }
        });

    }


*/




}
