package com.khalyd.cecyteapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.khalyd.cecyteapp.ActivityDirectivo.MainActivityDirectivo;
import com.khalyd.cecyteapp.ActivityParent.MainActivityParent;
import com.khalyd.cecyteapp.ActivityTeacher.MainActivityTeacher;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.MainActivity;
import com.khalyd.cecyteapp.CheckingInternetClasses.ConnectivityReceiver;
import com.khalyd.cecyteapp.CheckingInternetClasses.MyApp;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import java.io.File;


public class SplashScreen extends AppCompatActivity {

    private  SharedPreferences preferences;

    //Creare otro codigo donde diga como esta version todavia no se ha lanzado porfavor espera en la consulta q hace primero

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = Utils.getMySharedPreferences(this);
        Utils.setVersionActually(preferences,"1");//The version inicial is 1 Actaully 03/03/2018

        //Maneje String porq solo se envian strings no ints asi que por eso maneje asi ademas q php no diferencia

        if (!ConnectivityReceiver.isConnected()) {

            Intent i = new Intent(SplashScreen.this,NoInternetConnetivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();

        }if (Build.VERSION.SDK_INT >= 21) {

            getWindow().setStatusBarColor(getResources().getColor(R.color.barInfoApp));

        }

        MyApp.getInstance().clearCache();
       // fullScreen();
        setContentView(R.layout.activity_splash_screen);
        time();

    }

    private void time(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (ConnectivityReceiver.isConnected()){

                    LoadVersion loadServices = new LoadVersion();
                    loadServices.execute();

                    //Tecniacamente no lo esta haciendo en otro hilo si no espera ese tiempo de 1250 y luego lo haace pero ya aprendi
                    // a manejar los hilos para una mejor interfaz de usuario
                }

            }
        },1250);

    }


    private  class LoadVersion extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Esta es la interfaz del ususario antes de hacer la ejecucion(ProgressBar)
            //Este controla la interfaz antes del proceso



        }
        @Override
        protected Void doInBackground(Void... voids) {
            //Segundo Plano al parecer aqui se hace todo
           // publishProgress();Metodo para usar el onProgressUpdate este controla lo q se vaya a hacer durante el proceso
            String url2 = "/AcitivitiesMain/checkVersionApp.php?";
            DesignForAppMethods.getTheVersionMoreActualFromDB(SplashScreen.this,url2,preferences, SplashScreen.this);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {//Update in realtimeUserUInterface
            super.onProgressUpdate(values);

            //Este controla la interfaz durante el proceso
        }

        @Override
        protected void onPostExecute(Void aVoid) { //Aqui ya acabo la tarea //Supongo q aqui ya le avisa al
            // progrres que desaparezca q se detenga
            //Este controla la interfaz despues del proceso

            super.onPostExecute(aVoid);



        }
    }
}
