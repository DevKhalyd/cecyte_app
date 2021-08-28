package com.khalyd.cecyteapp.ActivitysSubjectsMain;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.khalyd.cecyteapp.Adapters.PaysAdapter;
import com.khalyd.cecyteapp.Fragments.SubjectsFragment;
import com.khalyd.cecyteapp.R;

public class ProfessionalModule extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_modules);

        setToolbar2();


    }
  //Para poner a partir de que version se puede ver  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  private void setToolbar2(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCollapsing);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
        toolbar.setTitle("Módulos Profesionales");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.sloganColor));

       //Con esto lo puedo poner blanco toolbar.setTitleTextColor(0xFFFFFFFF);


        //En este caso seria el icono hacia atras

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                //No hay nececidad de inflar porq segun ya viene de manera nativa
                Intent intent = new Intent(ProfessionalModule.this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
