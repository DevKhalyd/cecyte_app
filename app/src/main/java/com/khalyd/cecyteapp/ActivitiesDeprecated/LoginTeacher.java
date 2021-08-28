package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.Toast;

import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.BlankFragmentEmptyuse;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.FragmentDirectivo;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.FragmentDocente;
import com.khalyd.cecyteapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoginTeacher extends AppCompatActivity {

    TextView txtStar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        UI();
        all();
    }






    private void UI(){
        txtStar = findViewById(R.id.textStart);

    }



    private void all() {
       // goToMain();
        goToBck();

        setSpinner();
        ImageView view = findViewById(R.id.loadingViewTeacher);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }



    private void goToBck() {
        ImageView imageView =  findViewById(R.id.backToSIGN);
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginTeacher.this, SignInAs.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginTeacher.this).toBundle());
            }
        });

    }



    private void setSpinner(){

        final Spinner spinner =  findViewById(R.id.spinner);

        ArrayList<String> profilesAvaibles = new ArrayList<>();
        profilesAvaibles.add("Ninguno");
        profilesAvaibles.add("Docente");
        profilesAvaibles.add("Directivo");


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,R.layout.spinner_model,profilesAvaibles);
        spinner.setAdapter(spinnerAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                try {


                    Bundle bundle = new Bundle();


                    if (bundle != null){


                        bundle = getIntent().getExtras();

                        int positionCargo = bundle.getInt("position");
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                        switch (positionCargo){//Docente == 2 Directivo == 1
                            case 1:
                                fragmentTransaction.replace(R.id.containerDocente,new FragmentDirectivo()).commit();
                                txtStar.setVisibility(View.INVISIBLE);
                                adapterView.setSelection(2);
                                Toasty.info(getApplicationContext(),"Verifique sus datos", Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                fragmentTransaction.replace(R.id.containerDocente,new FragmentDocente()).commit();
                                txtStar.setVisibility(View.INVISIBLE);
                                adapterView.setSelection(1);
                                Toasty.info(getApplicationContext(),"Verifique sus datos", Toast.LENGTH_LONG).show();

                                break;
                        }


                    }


                }catch (Exception e){


                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    switch (position){

                        case 0:
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                            fragmentTransaction.replace(R.id.containerDocente,new BlankFragmentEmptyuse()).commit();
                            txtStar.setVisibility(View.VISIBLE);
                            break;


                        case 1:
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.replace(R.id.containerDocente,new FragmentDocente()).commit();
                            txtStar.setVisibility(View.INVISIBLE);
                            break;

                        case 2:

                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.replace(R.id.containerDocente,new FragmentDirectivo()).commit();
                            txtStar.setVisibility(View.INVISIBLE);
                            break;
                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


}


