package com.khalyd.cecyteapp.FragmentsToLocate;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.khalyd.cecyteapp.Activities.CecyteLocate;
import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToLocate_State extends Fragment implements OnMapReadyCallback{

    private View viewInflate1;
    private MapView mapView;
    private GoogleMap mMap;
    private LatLng uC0,uC1,uC2,uC3;




    public ToLocate_State() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewInflate1 = inflater.inflate(R.layout.fragment_to_locate__state, container, false);
        // Inflate the layout for this fragment


        return viewInflate1;
    }

    //Ciclo de vida del fragment
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) viewInflate1.findViewById(R.id.state_oaxaca);
        if(mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap = googleMap;

        mMap.setMinZoomPreference(7); //Hace lo que dice
        mMap.setMaxZoomPreference(20);

        // Add a marker in Sydney and move the camera
        uC0 = new LatLng(17.0767006, -96.7138886); //Este es para poner la localizacion del marcador en este caso Mi ubicacion
        uC1 = new LatLng(16.905831, -96.3967335);
        uC2 = new LatLng(15.668552, -96.492849);
        uC3 = new LatLng(16.8032313, -95.0972936);
        //Los de arriba son los marcadores de Google
        mMap.addMarker(new MarkerOptions().position(uC0).title("CECyTE 05 Etla"));//Con esta intruccion se pone el titulo del marcador
        mMap.addMarker(new MarkerOptions().position(uC1).title("CECyTE 16 Mitla"));
        mMap.addMarker(new MarkerOptions().position(uC2).title("CECyTE 21 Puerto Angel"));
        mMap.addMarker(new MarkerOptions().position(uC3).title("CECyTE 06"));

        //Cecyte Name Marcadores

        mMap.moveCamera(CameraUpdateFactory.newLatLng(uC0));//Con esta instruccion se ubica la camara(O la posciicon de la pantalla ) donde queramos (miUbicacion)

        CameraPosition camera = new CameraPosition.Builder()//Al parecer el builder es para personalizar
                .target(uC0) //Aqui va la ubicacion u objetivo del Mapa
                .zoom(7) //Hay diferente tipos de zoom. Limite = 21 //En todo lo demas hay un  limite solo hay q jugar con los valores
                .bearing(0)//Rotacion del Mapa L= 365°
                .tilt(0)//Incliniacion L= 90° Al parecer esto es parar el 3D y en 0 es plano
                .build();//Al parecer se pone al fianl del Builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera)); //Con esto se llama a nuestra Camara position (Animacion de la camara)

    }




}
