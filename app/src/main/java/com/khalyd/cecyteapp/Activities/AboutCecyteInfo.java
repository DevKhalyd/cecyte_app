package com.khalyd.cecyteapp.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

public class AboutCecyteInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cecyte);

        wegoMain();
        toLocate();

        ImageView view = findViewById(R.id.anim_Info);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }

    private void wegoMain() {
        ImageView next =  findViewById(R.id.to_Main_Activity);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutCecyteInfo.this, FirtsActivtiChooseTwo.class);
                startActivity(intent);
            }
        });
    }



    private void toLocate(){
        Button btnlocate = findViewById(R.id.to_locate);
        btnlocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutCecyteInfo.this,CecyteLocate.class);
                startActivity(intent);
            }
        });
    }




}
