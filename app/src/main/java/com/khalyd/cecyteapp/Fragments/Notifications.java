package com.khalyd.cecyteapp.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Adapters.AdapterNotificationsStudent;
import com.khalyd.cecyteapp.Adapters.PaysAdapter;
import com.khalyd.cecyteapp.Class.ComplemetsUrlandConstansFinal;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.VolleySingleton;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.Models.Students;
import com.khalyd.cecyteapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


    //Crear un metodo donde retorne la suma o le quite un numero para cambiar el nombre

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications extends Fragment {

    static RecyclerView recyclerView;

    static ArrayList<Posts> postsnews;
    private ProgressBar progressBar;


    public Notifications() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pays, container, false);

        //Este es difente al del parent
        UI(view);
        Utils.setrecycler(recyclerView,getContext(),ComplemetsUrlandConstansFinal.code_Student,progressBar,false,null);
        return view;
        //Este es de los estudiantes
    }

    private void UI(View v){

        postsnews = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recycler_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
        recyclerView.setPreserveFocusAfterLayout(true);
        recyclerView.setHasFixedSize(true);
         progressBar = v.findViewById(R.id.load_services);


    }

}

