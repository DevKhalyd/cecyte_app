package com.khalyd.cecyteapp.ActivitiesDeprecated;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.khalyd.cecyteapp.ActivitiesDeprecated.LoginStudent;
import com.khalyd.cecyteapp.ActivitiesDeprecated.SignUpAs;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class SignUpStudent extends AppCompatActivity {


/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

                                            //Value Unique


        allViews();


    }

    private void allViews(){

        goToBack();
        signupStudent();


        ImageView view = findViewById(R.id.loadingViewStudentSignUp);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }

    private void signupStudent(){

        final CircularProgressButton btnStudent = findViewById(R.id.sign_Up_student);

        final TextInputLayout txtEnrollment  =findViewById(R.id.sign_up_student_number_enrollement);
        final EditText edtEnrollment = findViewById(R.id.get_Number_Student_sign_Up);

        final TextInputLayout txtEmailStudent = findViewById(R.id.email_student_sign_up);
        final EditText edtEmailStudent = findViewById(R.id.get_email_student_sign_up);

        final TextInputLayout inputpass = findViewById(R.id.password_stundet_sign_up);
        final EditText edtinputpass = findViewById(R.id.get_password_stundet_sign_up);

        final TextInputLayout inputPassConfirm = findViewById(R.id.password_stundet_sign_up_confirm);
        final EditText edtpassConfirm = findViewById(R.id.get_password_stundet_sign_up_confirm);

        final RelativeLayout relativeLayout = findViewById(R.id.relativesignin_Student);

        final AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

       // for (int = 1, int >=2, i++)  ese seria el ciclo

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enrollmentStudent = edtEnrollment.getText().toString();
                String email = edtEmailStudent.getText().toString();
                String pass = edtinputpass.getText().toString();
                String passconfirm = edtpassConfirm.getText().toString();
                String url = "/AcitivitiesMain/signUpAll/signUpStudents.php?";
                String c1 = "matricula";
                String c2 = "email";
                String c3 = "password";
                String [] parametres = {"succesful","nosuccesful","accountBusy","accInUseEmail","somethisiscrashthis","Somethingisbad"};
                int  color = getResources().getColor(R.color.red2);
                int  colorpostive = getResources().getColor(R.color.barSignUp);
                int txtBig = 16;
                int txtlitle = 15;
                String messageAlumno = "Otra cuenta ya fue registrada con el mismo número de matrícula, por favor ingresa otra matrícula \n \n" +
                        "Si estas seguro de que esa matricula es tuya y  no la has registrado, por favor informaselo al administrador";


                //Metodo por cambiar
                if (Utils.checkIfSpacesIsCorrectSignUp(enrollmentStudent,txtEnrollment,null
                        ,email,txtEmailStudent,pass,inputpass,passconfirm,inputPassConfirm)){


                   WebServices.signUpAll(SignUpStudent.this,enrollmentStudent,email,pass,url,LoginStudent.class,c1,c2,c3,btnStudent,
                            parametres[0],parametres[1],parametres[2],parametres[3],parametres[4],parametres[5],relativeLayout,color,colorpostive,txtBig,txtlitle,null
                    ,dialogo,messageAlumno);

                }

            }
        });





    }

    private void goToBack(){
        View img = findViewById(R.id.back2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (SignUpStudent.this,FirtsActivtiChooseTwo.class);
                startActivity(i);

            }
        });
    }

*/


}
