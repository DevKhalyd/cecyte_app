package com.khalyd.cecyteapp.ActivitysSubjectsMain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.khalyd.cecyteapp.R;

public class TutoriasSubjects extends AppCompatActivity {

    //Checar porq da error el nombre esta clase junto con PandS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoty);

        setTollbar2();
    }
    private void setTollbar2(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTest);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Tutorias");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);//Icono de la hamburgesa(Son las tres lineas)
        //En este caso seria el icono hacia atras

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                //No hay nececidad de inflar porq segun ya viene de manera nativa
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

