package com.khalyd.cecyteapp.ActivitysSubjectsMain;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.khalyd.cecyteapp.Adapters.PaysAdapter;
import com.khalyd.cecyteapp.R;

public class FilosofySubject extends AppCompatActivity {

    //Debo de crear un modelo para cada materia (I think) aunque pensandolo en grande tengo que hacer de todas las materias y grados y grupos?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filosofy);

        setTollbar2();
        tabsCounted();
    }
    private void setTollbar2(){
        Toolbar toolbar =  findViewById(R.id.toolbar_philosophy);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Filosofía");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);//Icono de la hamburgesa(Son las tres lineas)
        //En este caso seria el icono hacia atras

    }
    private void tabsCounted(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutPhilosophy);
        tabLayout.addTab(tabLayout.newTab().setText("Descripción"));
        tabLayout.addTab(tabLayout.newTab().setText("Recursos"));
        tabLayout.addTab(tabLayout.newTab().setText("Foro"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerFilosofy);
        PaysAdapter paysAdapter = new PaysAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        //Si algo falla es esto de arriba


        viewPager.setAdapter(paysAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                //No hay nececidad de inflar porq segun ya viene de manera nativa
                Intent intent = new Intent(FilosofySubject.this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
