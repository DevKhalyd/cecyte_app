package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

public class SignInAs extends AppCompatActivity {

    private Button bt1,bt2,bt3;


    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,FirtsActivtiChooseTwo.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as);
        //setTollbar23();
        allCall();

        ImageView view = findViewById(R.id.loadingChooseAcc);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }

    private void allCall(){

        returntoMain();
        teahcerLog();
        parentLog();
        studenLog();

    }



  private void returntoMain(){
      ImageView imageView = (ImageView) findViewById(R.id.back);
      imageView.setOnClickListener(new View.OnClickListener() {
          @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(SignInAs.this, FirtsActivtiChooseTwo.class);
              startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(SignInAs.this).toBundle());
          }
      });

  }
    private void studenLog(){
        bt1 = (Button) findViewById(R.id.btnStudent);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInAs.this,LoginStudent.class);
                startActivity(intent);

            }
        });


    }


  private void teahcerLog(){
      bt2 = (Button) findViewById(R.id.Teacher);
      bt2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(SignInAs.this, LoginTeacher.class);
              startActivity(intent);
          }
      });


  }
    private void parentLog(){
        bt3 = (Button) findViewById(R.id.Parent);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInAs.this,LoginParent.class);
                startActivity(intent);
            }
        });


    }
}



