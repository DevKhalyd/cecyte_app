package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

public class SignUpAs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as);

       allItem();
    }

    private void allItem(){
        toMain();
        goToSignUpStudent();
        goToSignUpTeacher();
        goToSignUpParent();

        ImageView view = findViewById(R.id.loadingChooseAcc);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);

    }

    private void toMain(){
        ImageView img =  findViewById(R.id.back);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpAs.this,FirtsActivtiChooseTwo.class);
                startActivity(intent);
            }
        });
    }

    private void goToSignUpStudent(){

        //TODO AQUI en vez de castearlo se puso un view y ya no se castea

        View btn = findViewById(R.id.btnSignUpStudent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpAs.this,SignUpStudent.class);
                startActivity(i);
            }
        });

    }
    private void goToSignUpTeacher(){
        View btn2 = findViewById(R.id.Teacher);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpAs.this,SignUpTeacher.class);
                startActivity(i);
            }
        });
    }

    private void goToSignUpParent(){
        View btn = findViewById(R.id.Parent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpAs.this,SignUpParent.class);
                startActivity(i);
            }
        });
    }
}
