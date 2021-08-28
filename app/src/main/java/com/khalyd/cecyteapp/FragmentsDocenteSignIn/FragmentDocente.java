package com.khalyd.cecyteapp.FragmentsDocenteSignIn;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.khalyd.cecyteapp.Activities.SplashScreen;
import com.khalyd.cecyteapp.ActivityTeacher.MainActivityTeacher;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDocente extends Fragment {

    //Este es el fragemnt para hacer el login (Es el q se sustiyen en la actividad) == Login

    private EditText edtUser;
    private SharedPreferences preferences;



    public FragmentDocente() {
        // Required empty public constructor
    }

    //Hacia el perfil del docente



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_fragment_docente, container, false);

        preferences = Utils.getMySharedPreferences(getContext());

        edtUser =  v.findViewById(R.id.email_Teacher_Txt_Docente);

        // Inflate the layout for this fragment
        loginDocente(v);

        return v;

    }








        private void loginDocente (View v) {

        final TextInputLayout txtUser = v.findViewById(R.id.emailDocente);


        final TextInputLayout txtPass =  v.findViewById(R.id.passDocente);
        final EditText edtPass =  v.findViewById(R.id.passwordDocente);

        final CircularProgressButton button = v.findViewById(R.id.loginDocente);
        final LinearLayout linearLayout  = v.findViewById(R.id.LInearDocente);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String getPass = edtPass.getText().toString();
                String getEmail = edtUser.getText().toString();

                String c1 = "email";
                String c2 = "password";
                String c3 = "table"; //Asi se llama como buscara en todo caso no necesitaria esto ya que seria lo mismo
                String table = "teacher";
                String position = "Teacher";

                if (Utils.emailandPassValidation(getEmail,txtUser,getPass,txtPass)){


                        String url = "/AcitivitiesMain/loginAccounts/loginAll.php?";//Siempre agregar el ?

                        WebServices.loginAll(getContext(),table,getEmail,getPass,url,c1,c2,c3,button,preferences,position,linearLayout);

                        //Cada uno va a ir a diferente ventana ya que son diferentes perfiles

                }
            }
        });
    }


}
