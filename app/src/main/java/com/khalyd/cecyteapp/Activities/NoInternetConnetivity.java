package com.khalyd.cecyteapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.R;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class NoInternetConnetivity extends AppCompatActivity {

    private TextView txt;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle myBundle = new Bundle();

        try {
            //NO se si todos los dispositivos funcionan de la misma manera pero podria haber un error

            if (myBundle != null){

                myBundle = getIntent().getExtras();

                int numberForContent = myBundle.getInt("serverNotWorking");


                url = myBundle.getString("UrlPlayStore");


                switch (numberForContent){

                    case 1://Es para el server3
                        if (Build.VERSION.SDK_INT >= 21) {

                            getWindow().setStatusBarColor(getResources().getColor(R.color.serverNoWokr));
                        }
                       // Debo trabajar con network WorkHome
                        setContentView(R.layout.serverisnotworking);
                        break;
                    case 2://Es para la update en la playstore

                        if (Build.VERSION.SDK_INT >= 21) {

                            getWindow().setStatusBarColor(getResources().getColor(R.color.barInfoApp));

                        }
                        setContentView(R.layout.dowloadfromplaystore);
                        break;

                    case 3:
                        // Debo trabajar con network WorkHome
                        setContentView(R.layout.activity_no_internet_connetivity);
                        txt =  findViewById(R.id.description_No);
                        txt.setText("Hubó un problema con el servidor, \nporfavor intenta de nuevo más tarde");
                        break;
                        //Creo q solo el 3 sera el mas ocupado ya que los otros 2 solo se muestran cuando hace la peticion al server
                       //De ahi no lo vuelve a mostrar
                }

            }
        }catch (Exception e){

            setContentView(R.layout.activity_no_internet_connetivity);

        }

    }



    public void retry(View view) {

        //Este un onClick

        Intent i = new Intent(this,SplashScreen.class);
        startActivity(i);
        // Debo trabajar con network WorkHome

    }


    public void updateApp(View view) {

        try {



            Uri uri = Uri.parse(url);
            Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent1);


        }catch (Exception e){

            Toasty.error(this,"Algo salió mal, cierra la app e intenta de nuevo",Toast.LENGTH_LONG).show();

        }


    }

}
