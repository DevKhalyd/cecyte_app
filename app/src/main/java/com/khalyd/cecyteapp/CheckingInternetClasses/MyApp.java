package com.khalyd.cecyteapp.CheckingInternetClasses;

import android.app.Application;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;


public class MyApp extends Application {

    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

       //SystemClock.sleep(2000);  //Esto me podria servir para detener la app
        //Son milisegundos

        mInstance = this;

    }


    public static synchronized MyApp getInstance() {
        return mInstance;

    }

    public void clearCache() {

        File cache = getCacheDir();
        if (cache.exists()){

            deleteDir(cache); //Solo  le pase el directorio del cache

        }



        //Para eliminar el cachw se hizo asi



/*
        File appDir = new File(cache.getParent());
        if (appDir.exists()){


            String[] children = appDir.list();
            for (String s : children){ //Este es un foreach en java

                if (!s.equals("lib")){

                //    deleteDir(new File(appDir,s));

                    Log.i("DATAELIMATED", "File /data/data/APP_PACKAGE/" + s +" FUE BORRADO");

                }

            }

        }*/

    }

    public static boolean  deleteDir(File dir) {

        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            Log.i("Children ", " = " + children);
            int s = children.length;

          for (int i = 0; i < s; i++) {

                   boolean success =  deleteDir(new File(dir, children[i]));

                   if (!success) {
                       return false;
                   }


            }
        }

        return dir.delete();
    }




    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }








}
