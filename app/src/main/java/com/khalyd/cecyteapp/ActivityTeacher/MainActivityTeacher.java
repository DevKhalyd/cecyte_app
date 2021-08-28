package com.khalyd.cecyteapp.ActivityTeacher;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.khalyd.cecyteapp.R;

public class MainActivityTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.content, new FeatureFragment()).commit();

        setBottomViewTeacher();


    }

    private void setBottomViewTeacher(){


        BottomNavigationView btnVT =  findViewById(R.id.navigation_Teacher);
        btnVT.setSelectedItemId(R.id.feature);

        btnVT.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                switch (item.getItemId()){

                    case R.id.myGroups:
                        ft.replace(R.id.content,new MyGroupsFragment()).commit();
                        return true;
                    case R.id.feature:
                        ft.replace(R.id.content,new FeatureFragment()).commit();
                        return true;
                    case R.id.optionTeacher:
                        ft.replace(R.id.content,new OptionTeacherFragment()).commit();
                        return true;
                   }
                return true;

            }
        });


    }


}
