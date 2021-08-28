package com.khalyd.cecyteapp.ActivitysSubjectsMain;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.khalyd.cecyteapp.R;

public class PandS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probability_statistics_subject);

        setTollbar3();
    }
    //Checar todo el codigo
    private void setTollbar3(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfeatures1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);//Icono de la hamburgesa(Son las tres lineas)
        toolbar.setTitle("Probalidad y Estádistica");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.sloganColor));
        //En este caso seria el icono hacia atras
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                //No hay nececidad de inflar porq segun ya viene de manera nativa
                Intent i = new Intent(PandS.this,MainActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
