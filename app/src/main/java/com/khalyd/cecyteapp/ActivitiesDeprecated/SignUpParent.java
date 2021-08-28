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

import com.khalyd.cecyteapp.ActivitiesDeprecated.LoginParent;
import com.khalyd.cecyteapp.ActivitiesDeprecated.SignUpAs;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class SignUpParent extends AppCompatActivity  {

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_parent);

        allViews1();

    }

    private void allViews1(){
        goToSignAs();
        signUpParent();


        ImageView view = findViewById(R.id.loadingViewTutorSignUp);

        String img = getString(R.string.FirtsImage);

        Utils.getImageFromUrl(img,this,view);


    }

    private void goToSignAs(){
        View img = findViewById(R.id.back3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpParent.this,SignUpAs.class);
                startActivity(i);
            }
        });
    }



    private void signUpParent(){

        final CircularProgressButton btnSignUpParent = findViewById(R.id.btn_parent_sign_up);

        final TextInputLayout txtInputNumberCardStudent = findViewById(R.id.enroollmens_Students_for_sign_Up_Parent);
        final EditText edtNummberCard = findViewById(R.id.get_enroollmens_Students_for_sign_Up_Parent);

        final TextInputLayout txtEmailParent = findViewById(R.id.email__parent_sign_up);
        final EditText edtEmailParent = findViewById(R.id.get_email__parent_sign_up);

        final TextInputLayout txtpass = findViewById(R.id.password_Parent_Sign_Up);
        final EditText edtpass = findViewById(R.id.get_password_Parent_Sign_Up);

        final TextInputLayout txtpassConfirm = findViewById(R.id.password_Parent_Sign_Up_Confirm);
        final EditText edtpassConfirm = findViewById(R.id.get_password_Parent_Sign_Up_Confirm);
        final RelativeLayout relativeLayout = findViewById(R.id.relative_parentSign);
        final AlertDialog.Builder dialgo = new AlertDialog.Builder(this);


        btnSignUpParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numberCard = edtNummberCard.getText().toString();
                String emailParent = edtEmailParent.getText().toString();
                String pass = edtpass.getText().toString();
                String passCofirm = edtpassConfirm.getText().toString();

                String url = "/AcitivitiesMain/signUpAll/signUpParent.php?";

                String c1 = "matricula";
                String c2 = "email";
                String c3 = "password";


                String [] parametres = {"AllWassuccesful","nosuccesful","bussyAcc","emailRegistrado","ErroTooStrong","stundentUserNoexits","shouldStudentSignUp"};
                int  color = getResources().getColor(R.color.red2);
                int  colorpostive = getResources().getColor(R.color.barSignUp);
                int txtBig = 16;
                int txtlitle = 15;
                String messageParent = "Este alumno ya cuenta con un tutor registrado \n" +
                        "Si usted cree que esto es falso informe al administrador";

                String forTutor = "*Introduzca los 14 dígitos de la credencial \n del tutorado";



                //Metodo por cambiar
                if ( Utils.checkIfSpacesIsCorrectSignUp(numberCard,txtInputNumberCardStudent,forTutor,
                        emailParent,txtEmailParent,pass,txtpass,passCofirm,txtpassConfirm)) {

                    WebServices.signUpAll(SignUpParent.this, numberCard, emailParent, passCofirm, url, LoginParent.class, c1, c2, c3, btnSignUpParent,
                            parametres[0], parametres[1], parametres[2], parametres[3], parametres[4], parametres[5],
                            relativeLayout, color, colorpostive, txtBig, txtlitle,parametres[6],dialgo,messageParent);


                }
            }
        });


    }



*/




}
