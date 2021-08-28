package com.khalyd.cecyteapp.Activities;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.khalyd.cecyteapp.ActivitiesDeprecated.SignUpAs;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

public class FirtsActivtiChooseTwo extends AppCompatActivity {

    private Button bt1,bt2;

    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    //Voy hacer poner una flecha hacia atras con codigo sin ponerlo en el xml de la proxima SIGN UP AHI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_firts_activti_choose_two);

        AlertDialog.Builder alertDialogo = new AlertDialog.Builder(this);

        WebServices.CheckIfInternetIsWorking(findViewById(R.id.relative_check),this);

        String meesage = "Actualmente la app se encuentra en fase beta, por lo tanto puede presentar fallos. \n\nSi encuentras algun fallo, háznoslo saber.";

        WebServices.AlertDialog(alertDialogo,meesage);

        allObjects();

        //Quitar la barra de color y hacerla tranaspente

    }

    private void allObjects(){

        goToLogIn();
        goToSignUp();
        goToInfo();

        ImageView view = findViewById(R.id.loadingLogin1);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }



    private void goToLogIn(){
        bt1 =  findViewById(R.id.btnSignInAs);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirtsActivtiChooseTwo.this,AllSignInViewPager.class);
                //Debo de poner la transicion y el metodo onbacked en la otra activity
                startActivity(intent);

            }
        });


    }

    //TODO CHANGE

    private void goToSignUp(){
        bt2 =  findViewById(R.id.Registro);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(FirtsActivtiChooseTwo.this, AllSignUpViewPagerRegistro.class);
                startActivity(intent);
            }
        });
    }



    private void goToInfo(){
        ImageView close =  findViewById(R.id.goToInfo);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirtsActivtiChooseTwo.this,AboutCecyteInfo.class);
                startActivity(i);
            }
        });

    }


}
