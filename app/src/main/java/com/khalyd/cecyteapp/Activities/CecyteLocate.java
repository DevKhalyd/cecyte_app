package com.khalyd.cecyteapp.Activities;



import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.khalyd.cecyteapp.Adapters.ToLocateAdapter;
import com.khalyd.cecyteapp.R;

public class CecyteLocate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cecyte_to_locate);


        calledtoToolbar();
    }

    //Una vez hecho el layout invocamos a la toolbar


    private void calledtoToolbar(){

        Toolbar toolbarLocate = findViewById(R.id.toolbar_to_Locate1);
        setSupportActionBar(toolbarLocate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarLocate.setTitle("Localizalo");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);


        TabLayout tabLayoutLocate = (TabLayout) findViewById(R.id.tabLayout_to_Locate);
        tabLayoutLocate.addTab(tabLayoutLocate.newTab().setText("Estatal"));
        tabLayoutLocate.addTab(tabLayoutLocate.newTab().setText("Nacional"));
        tabLayoutLocate.setTabGravity(TabLayout.MODE_SCROLLABLE);

        final ViewPager vPToLocate = (ViewPager) findViewById(R.id.viewPagertoLocate);
        ToLocateAdapter toLocateAdapter = new ToLocateAdapter(getSupportFragmentManager(),tabLayoutLocate.getTabCount());

        vPToLocate.setAdapter(toLocateAdapter);
        vPToLocate.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutLocate));

        tabLayoutLocate.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                vPToLocate.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    //El switch pide que le pases un valor el que se le pasa es el segundo valor de option..... o sea item.getItemID


}
