package com.khalyd.cecyteapp.ActivityDirectivo;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.ActivitysSubjectsMain.MainActivity;
import com.khalyd.cecyteapp.CheckingInternetClasses.MyApp;
import com.khalyd.cecyteapp.Class.ServicesDirectivo;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */

public class OptionsDirectivo extends Fragment {

    private CircleImageView profile;
    private TextView showIntructions,name,lastname,cargo;
    private SharedPreferences preferences;

    public OptionsDirectivo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyApp.getInstance().clearCache();
        View v = inflater.inflate(R.layout.fragment_options_directivo, container, false);
        UI(v);
        loadData();
        loadImage();
        return v;
    }

    private void UI(View v){
        preferences = Utils.getMySharedPreferences(getContext());
        profile = v.findViewById(R.id.circleDirectivoProfile);
        showIntructions  = v.findViewById(R.id.showIntructionsDirectivo);
        cargo = v.findViewById(R.id.positionDirectivo);
        name = v.findViewById(R.id.nameDirectovo);
        lastname = v.findViewById(R.id.lastsNameDirectivo);
    }

    private void loadData (){



        String url = "/AcitivitiesMain/checkingInfoAll.php?";
        String table = "directivo";

        ServicesDirectivo.checkInfoDirectivo(getContext(),url,preferences,profile,name,lastname,table,showIntructions,cargo);


    }


    private void loadImage(){

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Only data directivo
                Utils.saveOnlyOneDateBundle(getContext(),Utils.TABLE_DIRECTIVO,Utils.FOLDER_DIRECTIVO);

            }
        });

    }

}
