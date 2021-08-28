package com.khalyd.cecyteapp.FragmentsToLocate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToLocate_Nacional extends Fragment implements OnMapReadyCallback {

    private View viewInflate;
    private MapView mapView;
    private GoogleMap mMap;
    private LatLng uCN,uCN1,uCN2,uCN3,uCN4,uCN5,uCN6,uCN7,uCN8,uCN9;

    public ToLocate_Nacional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInflate = inflater.inflate(R.layout.fragment_to_locate__nacional, container, false);
        // Inflate the layout for this fragment
        return viewInflate;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) viewInflate.findViewById(R.id.state_nacional);
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

        mMap.setMinZoomPreference(5); //Hace lo que dice
        mMap.setMaxZoomPreference(15);

        // Add a marker in Sydney and move the camera
        uCN  = new LatLng(19.4011429, -99.099575); //Este es para poner la localizacion del marcador en este caso Mi ubicacion
        uCN1 = new LatLng(20.6699281, -103.3755819);
        uCN2 = new LatLng(27.5856138, -109.9193044);
        uCN3 = new LatLng(29.1438407, -110.9652916);
        uCN4 = new LatLng(25.7733723, -100.2067929);

        mMap.addMarker(new MarkerOptions().position(uCN).title("Coordinación Nacional CECyTE"));//Con esta intruccion se pone el titulo del marcador
        mMap.addMarker(new MarkerOptions().position(uCN1).title("CECyTE Jalisco"));
        mMap.addMarker(new MarkerOptions().position(uCN2).title("CECyTE Plantel Esperanza"));
        mMap.addMarker(new MarkerOptions().position(uCN3).title("CECyTE Alto Valle"));
        mMap.addMarker(new MarkerOptions().position(uCN4).title("CECyTE Monterrey"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(uCN));//Con esta instruccion se ubica la camara(O la posciicon de la pantalla ) donde queramos (miUbicacion)

        CameraPosition camera = new CameraPosition.Builder()//Al parecer el builder es para personalizar
                .target(uCN) //Aqui va la ubicacion u objetivo del Mapa
                .zoom(5) //Hay diferente tipos de zoom. Limite = 21 //En todo lo demas hay un  limite solo hay q jugar con los valores
                .bearing(0)//Rotacion del Mapa L= 365°
                .tilt(0)//Incliniacion L= 90° Al parecer esto es parar el 3D y en 0 es plano
                .build();//Al parecer se pone al fianl del Builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera)); //Con esto se llama a nuestra Camara position (Animacion de la camara)

    }
}
