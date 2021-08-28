package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.content.Intent;
import android.content.SharedPreferences;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.ActivityParent.MainActivityParent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginParent extends AppCompatActivity {

    //Pasar esta info al Fragment

    private EditText edtgetEmailParent;
    private CircularProgressButton btnParent;
    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_parent);
        preferences = Utils.getMySharedPreferences(this);

        UI();

        //Poner la animacion de atras del background
        allItems();
    }
    private void allItems(){

        goToSignAs();
        loginParent();
        ImageView view = findViewById(R.id.loadingViewParent);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);

    }


    private void UI (){

        edtgetEmailParent = findViewById(R.id.get_email_parent);



    }




    private void goToSignAs(){
        ImageView imageView = findViewById(R.id.backToSIGN);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(LoginParent.this,FirtsActivtiChooseTwo.class);
                startActivity(intent);


            }
        });

    }

    private void loginParent(){


        btnParent = findViewById(R.id.button_parent);

        final TextInputLayout txtemailParent = findViewById(R.id.email_parent);


        final TextInputLayout txtPassParent = findViewById(R.id.password_parent_login);
        final EditText edtgetPassParent = findViewById(R.id.get_password_parent);

        //Checar porq checa 1 en uno

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getEmailParent =  edtgetEmailParent.getText().toString();
                String getPassParent = edtgetPassParent.getText().toString();

                String c1 = "email";
                String c2 = "password";
                String c3 = "table";
                String table = "parents";
                String position = "Tutor";

                String url = "/AcitivitiesMain/loginAccounts/loginAll.php?";//Siempre agregar el ?



/*
                if (Utils.emailandPassValidation(getEmailParent,txtemailParent,getPassParent,txtPassParent)){


                    WebServices.loginAll(LoginParent.this,table,getEmailParent,getPassParent,url,c1,c2,c3,btnParent,preferences,position);

                    //Una vez que esten bien los resultados en vez de regresar la palabra succesful que me de el id del alumno para asi guardarlo

                }*/





            }
        });







    }



}
