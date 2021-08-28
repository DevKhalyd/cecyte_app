package com.khalyd.cecyteapp.ActivityParent;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Class.ServicesDocente;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment {


    //Fragmen Options Parent

    private SharedPreferences preferences;
    private FloatingActionButton fab;
    private CircleImageView circleImageView;
    private TextView txtName,txtLastName;
    private TextView showIntructuctins;



    public OptionsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        UI(view);
        preferences = Utils.getMySharedPreferences(getContext());
        setHasOptionsMenu(true);
        setToolbar(view);
        setChangeImage();
        seFab();
        loadAllItems();
        return view;


    }

    //Checar el envio de los datos
    private void loadAllItems(){

        String url = "/AcitivitiesMain/checkingInfoAll.php?";
        String table = "parents";
        ServicesDocente.checkInfoAllforGET(getContext(),url,preferences,circleImageView,txtName,txtLastName,table,showIntructuctins);

    }

    private void seFab(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(),EditProfileParent.class);
                startActivity(i);

            }
        });

    }

    private void UI(View v){

       fab = v.findViewById(R.id.fab_Edit_Profile_Parent);
       circleImageView = v.findViewById(R.id.circleImageView_Parent);
       txtName = v.findViewById(R.id.txtNameParent);
       txtLastName = v.findViewById(R.id.txtLastNameParent);
       showIntructuctins = v.findViewById(R.id.txtShowIntructionsParent);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_options__log_out,menu); //May error

        super.onCreateOptionsMenu(menu, inflater); //Maybe here too
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.LogOutAnyProfile: //Es el
                Utils.logoutAnyProfile(preferences,getContext());
                return true;
        }

        return super.onOptionsItemSelected(item); //Esto va hasta abajo

    }


    private void setToolbar(View v){

        Toolbar toolbar = v.findViewById(R.id.toolbar_options_parent);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar); //Para soporte en el framgment
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        activity.getSupportActionBar().setTitle("    Opciones");
    }

    private void setChangeImage(){



        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeImage();

            }
        });


    }

    private void changeImage() {

        //Use only parameters for parents
        Utils.saveOnlyOneDateBundle(getContext(),Utils.TABLE_PARENT,Utils.FOLDER_PARENTS);


    }





}
