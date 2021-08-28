package com.khalyd.cecyteapp.CheckingInternetClasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReceiver extends BroadcastReceiver {

    //This is a receiver class which will be notified whenever there is change in network / internet connection.
    //Cuando cambie el internet a este men le llegara

    public static  ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    } //Supogno que es el constructor




    @Override
    public void onReceive(Context context, Intent arg1) {

        //Esta encontradando la conectivada (?)
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting(); //Orale no sabia q se puede hacer eso
        //Tecnicamente esta haciendo la validacion con ese boolean

        if (connectivityReceiverListener != null){

            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);

        }//Le esta pasando la validacion

    }

    public static boolean isConnected(){

        ConnectivityManager cm =  (ConnectivityManager)  MyApp.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }



    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);

    }
}
