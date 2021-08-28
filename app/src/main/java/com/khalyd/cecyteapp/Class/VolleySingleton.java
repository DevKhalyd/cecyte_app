package com.khalyd.cecyteapp.Class;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingleton {

    private static VolleySingleton mInstance; //La clase o instancia
    private RequestQueue mRequestQueue; //
    private static Context mCtx;

    private VolleySingleton(Context context) {
        //Construtor para q pida desde otras classes
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }



    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);

        //Se encarga del request JSON y String Request
    }

    private RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }


        return mRequestQueue;

    }



    public static synchronized VolleySingleton getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }



}
