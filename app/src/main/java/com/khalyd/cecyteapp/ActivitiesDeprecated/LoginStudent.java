package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.khalyd.cecyteapp.ActivitysSubjectsMain.MainActivity;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import es.dmoral.toasty.Toasty;

public class LoginStudent extends AppCompatActivity {

    //Esta actividad no se le movio nada, esto fue antes del ViewPager



    private ImageView imgback;
    private EditText edtgetNumberStudent;
    private CircularProgressButton btnStudent;
    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_student);
        UI();
        bundle();
        preferences = Utils.getMySharedPreferences(this);



        AllMethods();


    }

    private void AllMethods (){
        goToSignInAs();

        loginStudent();

        ImageView view = findViewById(R.id.loadingViewStudent);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);



    }

    private void bundle(){

        Bundle bundle = new Bundle();

        //No debo de usar el bundle de la activity

        try {

            if (bundle != null){

                bundle =  getIntent().getExtras();

                String emailFromStundent = bundle.getString("email");


                edtgetNumberStudent.setText(emailFromStundent);

                Toasty.success(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
                Toasty.info(this,"Verifique sus datos",Toast.LENGTH_LONG).show();

            }


        }catch (Exception e){

            e.printStackTrace();
        }





    }

    private void UI(){

        edtgetNumberStudent = findViewById(R.id.get_email_student_sign_in);

    }

    private void loginStudent(){

        btnStudent = findViewById(R.id.loginStudent);

        final TextInputLayout txtinputStudent = findViewById(R.id.email_student_sign_in);


        final TextInputLayout txtInputpass = findViewById(R.id.password_Student);
        final EditText edtgetPass = findViewById(R.id.get_password_Student);

        //Cuando esta afuera de los metodos y esta de manera global no necesita el final

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getEmail = edtgetNumberStudent.getText().toString();
                String getPass = edtgetPass.getText().toString();

                String c1 = "email";
                String c2 = "password";
                String c3 = "table";
                String table = "students";


                String url = "/AcitivitiesMain/loginAccounts/loginAll.php?";//Siempre agregar el ?
                String position = "Estudiante";
/*
                if (Utils.emailandPassValidation(getEmail,txtinputStudent,getPass,txtInputpass)){

                    WebServices.loginAll(LoginStudent.this,table,getEmail,getPass,url,c1,c2,c3,btnStudent,preferences,position);


                }
*/
            }
        });





    }



    private void goToSignInAs(){

        imgback = findViewById(R.id.backToSIGN);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginStudent.this, SignInAs.class);
                startActivity(intent);
                //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                //Para darle un mejor soporte a las versiones antiguas <= 4.0 debo de quitar las animaciones
               // startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(LoginStudent.this).toBundle())

            }
        });
    }
}
