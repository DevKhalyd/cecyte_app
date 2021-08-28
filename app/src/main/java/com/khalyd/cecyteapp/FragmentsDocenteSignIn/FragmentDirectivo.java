package com.khalyd.cecyteapp.FragmentsDocenteSignIn;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.khalyd.cecyteapp.Activities.SplashScreen;
import com.khalyd.cecyteapp.ActivityDirectivo.MainActivityDirectivo;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDirectivo extends Fragment {

    private SharedPreferences preferences;
    EditText edtEmail;

    //Aqui esta el del directivo


    //Aqiu esta el login del directivo


    public FragmentDirectivo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_directivo, container, false);
        // Inflate the layout for this fragment
        edtEmail = v.findViewById(R.id.edtemailDirectivo);
        preferences = Utils.getMySharedPreferences(getContext());

        loginDirectivo(v);
        return v;
    }




    private void  loginDirectivo(View v){

        final TextInputLayout txtEmail,txtPassword;
        final EditText edtPassword;
        final CircularProgressButton circularProgressButton;

        txtEmail = v.findViewById(R.id.emailDirectivo);


        txtPassword = v.findViewById(R.id.txtpassDirectivo);
        edtPassword = v.findViewById(R.id.edtpasswordDirectivo);

        circularProgressButton = v.findViewById(R.id.loginDirectivo);
        final LinearLayout linearLayout = v.findViewById(R.id.linearVertical_Directivo);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getEmail = edtEmail.getText().toString();
                String getPass = edtPassword.getText().toString();

                String c1 = "email";
                String c2 = "password";
                String c3 = "table";
                String table = "directivo";


                String url = "/AcitivitiesMain/loginAccounts/loginAll.php?";
                String position = "Directivo";

                if (Utils.emailandPassValidation(getEmail,txtEmail,getPass,txtPassword)) {

                    WebServices.loginAll(getContext(),table,getEmail,getPass,url,c1,c2,c3,
                            circularProgressButton,preferences,position,linearLayout);

                }
            }

        });




    }





}
