package com.khalyd.cecyteapp.ActivityDirectivo;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.khalyd.cecyteapp.ActivityTeacher.FeatureFragment;
import com.khalyd.cecyteapp.ActivityTeacher.MyGroupsFragment;
import com.khalyd.cecyteapp.ActivityTeacher.OptionTeacherFragment;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

public class MainActivityDirectivo extends AppCompatActivity {
    private  BottomNavigationView btnVT;
    private android.support.v7.widget.Toolbar toolbar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_directivo);
        preferences = Utils.getMySharedPreferences(this);
        toolbar = findViewById(R.id.toolbar_Directivo);
        supportBar();
        setBottomViewTeacher();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentDirectivo,new PotsDirectivo()).commit();
        getSupportActionBar().setTitle("    Publicaciones");
        btnVT.setSelectedItemId(R.id.pots);
    }

    private void supportBar(){

        setSupportActionBar(toolbar); //Usar el 7 widget
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //Con el getSupportActionBar se pudee obtener todos los metodos de un actionbar
    }


    private void setBottomViewTeacher(){


         btnVT =  findViewById(R.id.navigation_Directivo);

        btnVT.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                switch (item.getItemId()){
                    case R.id.students:

                        ft.replace(R.id.contentDirectivo,new StudentsDirectivo()).commit();
                        getSupportActionBar().setTitle("    Alumnos");
                        return true;
                    case R.id.pots:
                        ft.replace(R.id.contentDirectivo,new PotsDirectivo()).commit();
                        getSupportActionBar().setTitle("    Publicaciones");
                        return true;
                    case R.id.optionDirectivo:
                        ft.replace(R.id.contentDirectivo,new OptionsDirectivo()).commit();
                        getSupportActionBar().setTitle("    Perfil");

                        return true;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.trheepontdirectivo, menu);

        return super.onCreateOptionsMenu(menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.LogOutDirectivo:
                Utils.logoutAnyProfile(preferences,this);
            return true;
            case R.id.UpdateProfileDirectivo:
                Utils.intententwithoutFLAGS(this,EditProfileDirectivo.class);
                return true;
            case R.id.UpdatePictures:
                Utils.intententwithoutFLAGS(this,UpdatePhotosApp.class);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

}
