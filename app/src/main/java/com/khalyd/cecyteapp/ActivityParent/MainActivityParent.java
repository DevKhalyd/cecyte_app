package com.khalyd.cecyteapp.ActivityParent;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.khalyd.cecyteapp.R;

public class MainActivityParent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.content, new NewsFragment()).commit();


        bottomView();
    }

    private void bottomView(){

        BottomNavigationView btnV =  findViewById(R.id.navigation_Parent);
        btnV.setSelectedItemId(R.id.newsParent);
        btnV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                switch (item.getItemId()){
                    case R.id.califications:
                        ft.replace(R.id.content,new CalficationsFragment()).commit();
                        return true;
                    case R.id.newsParent:
                        ft.replace(R.id.content,new NewsFragment()).commit();
                        return true;
                    case R.id.optionParent:
                        ft.replace(R.id.content,new OptionsFragment()).commit();
                        return true;
                }

                return true;
            }
        });

    }

}

