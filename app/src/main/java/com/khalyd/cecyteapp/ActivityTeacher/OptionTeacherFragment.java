package com.khalyd.cecyteapp.ActivityTeacher;


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
import android.widget.TextView;
import android.widget.Toast;

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
public class OptionTeacherFragment extends Fragment {

    private CircleImageView imageView ;
    private SharedPreferences preferences;
    private TextView txtName,txtLastName,showIntructuctins;



    public OptionTeacherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_option_teacher, container, false);
        UI(v);
        preferences = Utils.getMySharedPreferences(getContext());
        setToolbar(v);
        setHasOptionsMenu(true); //Para hacer q aparezcan las opcines
        loadAllItems();
        setFab(v);
        setChangeImage();

        return v;
    }

    private void loadAllItems(){

        String url = "/AcitivitiesMain/checkingInfoAll.php?";
        String table = "teacher";
        ServicesDocente.checkInfoAllforGET(getContext(),url,preferences,imageView,txtName,txtLastName,table,showIntructuctins);

    }

    private void UI(View v){

        imageView = v.findViewById(R.id.circleImageView_Docente);
        txtName = v.findViewById(R.id.textNameDocente);
        txtLastName = v.findViewById(R.id.txtLastNameDocente);
        showIntructuctins = v.findViewById(R.id.txtShowIntructions);



    }

    private void setFab(View v){

        FloatingActionButton fab = v.findViewById(R.id.fab_Edit_Profile_Docente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i =  new Intent(getContext(),EditProfileTeacher.class);
                startActivity(i);

            }
        });

    }


    private void setChangeImage(){



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeImage();

            }
        });


    }

    private void changeImage() {
        //Only use data for teacher
        Utils.saveOnlyOneDateBundle(getContext(),Utils.TABLE_TEACHER,Utils.FOLDER_TEACHERS);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_options__log_out,menu); //May error

        super.onCreateOptionsMenu(menu, inflater); //Maybe here too
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.LogOutAnyProfile:
                Utils.logoutAnyProfile(preferences,getContext());
                return true;
        }
        return super.onOptionsItemSelected(item); //Esto va hasta abajo

    }


    private void setToolbar(View v){
        Toolbar toolbar = v.findViewById(R.id.toolbar_options_docente);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar); //Para soporte en el framgment
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        activity.getSupportActionBar().setTitle("    Opciones");
    }


}
