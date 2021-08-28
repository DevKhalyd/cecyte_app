package com.khalyd.cecyteapp.FragmentsSignUpViewPagerRegistro;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Activities.SplashScreen;
import com.khalyd.cecyteapp.ActivitiesDeprecated.SignUpAs;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpParentsFragmentVP extends Fragment {

    private SharedPreferences preferences;


    public SignUpParentsFragmentVP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_sign_up_parent, container, false);
        preferences = Utils.getMySharedPreferences(getContext());


        ImageView view = v.findViewById(R.id.loadingViewTutorSignUp);
        String img = getString(R.string.SecondImage); //Para los logins y registros
        Utils.getImageFromUrl(img,getContext(),view);
        allViews1(v);

        return v;

    }

    private void allViews1(View v){
        goToSignAs(v);
        signUpParent(v);


        ImageView view = v.findViewById(R.id.loadingViewTutorSignUp);

        String img = getString(R.string.SecondImage);


        Utils.getImageFromUrl(img,getContext(),view);


    }

    private void goToSignAs(View v){
        View img = v.findViewById(R.id.back3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),FirtsActivtiChooseTwo.class);
                startActivity(i);
            }
        });
    }



    private void signUpParent(View v){

        final CircularProgressButton btnSignUpParent = v.findViewById(R.id.btn_parent_sign_up);

        final TextInputLayout txtInputNumberCardStudent = v.findViewById(R.id.enroollmens_Students_for_sign_Up_Parent);
        final EditText edtNummberCard = v.findViewById(R.id.get_enroollmens_Students_for_sign_Up_Parent);

        final TextInputLayout txtEmailParent = v.findViewById(R.id.email__parent_sign_up);
        final EditText edtEmailParent = v.findViewById(R.id.get_email__parent_sign_up);

        final TextInputLayout txtpass = v.findViewById(R.id.password_Parent_Sign_Up);
        final EditText edtpass = v.findViewById(R.id.get_password_Parent_Sign_Up);

        final TextInputLayout txtpassConfirm = v.findViewById(R.id.password_Parent_Sign_Up_Confirm);
        final EditText edtpassConfirm = v.findViewById(R.id.get_password_Parent_Sign_Up_Confirm);
        final RelativeLayout relativeLayout = v.findViewById(R.id.relative_parentSign);
        final AlertDialog.Builder dialgo = new AlertDialog.Builder(getContext());


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

                String position = "Tutor";



                //Metodo por cambiar
                if ( Utils.checkIfSpacesIsCorrectSignUp(numberCard,txtInputNumberCardStudent,forTutor,
                        emailParent,txtEmailParent,pass,txtpass,passCofirm,txtpassConfirm)) {

                    WebServices.signUpAll(getContext(), numberCard, emailParent, passCofirm, url, c1, c2, c3, btnSignUpParent,
                            parametres[0], parametres[1], parametres[2], parametres[3], parametres[4], parametres[5],
                            relativeLayout, color, colorpostive, txtBig, txtlitle,parametres[6],dialgo,messageParent,
                            preferences,position);

                }
            }
        });


    }








}
