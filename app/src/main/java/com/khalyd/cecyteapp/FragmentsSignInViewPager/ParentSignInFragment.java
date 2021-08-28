package com.khalyd.cecyteapp.FragmentsSignInViewPager;


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
import com.khalyd.cecyteapp.ActivityParent.MainActivityParent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentSignInFragment extends Fragment {

    //LoginParent
    //Si hay un problema con la iamgen de fondo puedo poner una opcion en el manifest
    //Creo q es pan
    private EditText edtgetEmailParent;
    private CircularProgressButton btnParent;
    private SharedPreferences preferences;

    //Debo de cambiar el registro


    public ParentSignInFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.activity_login_parent, container, false);
        // Inflate the layout for this fragment
        preferences = Utils.getMySharedPreferences(getContext());
        UI(v);
        //Poner la animacion de atras del background
        allItems(v);
        return v;

    }

    //Pasar esta info al Fragment


    private void allItems(View v){

        goToSignAs(v);
        loginParent(v);
        ImageView view = v.findViewById(R.id.loadingViewParent);

        String img = getString(R.string.SecondImage);

        Utils.getImageFromUrl(img,getContext(),view);

    }


    private void UI (View v){

        edtgetEmailParent = v.findViewById(R.id.get_email_parent);



    }




    private void goToSignAs(View v){
        ImageView imageView = v.findViewById(R.id.backToSIGN);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(),FirtsActivtiChooseTwo.class);
                startActivity(intent);


            }
        });

    }

    private void loginParent(View v){


        btnParent = v.findViewById(R.id.button_parent);

        final TextInputLayout txtemailParent = v.findViewById(R.id.email_parent);


        final TextInputLayout txtPassParent = v.findViewById(R.id.password_parent_login);
        final EditText edtgetPassParent = v.findViewById(R.id.get_password_parent);
        final RelativeLayout relativeLayout = v.findViewById(R.id.relative_main_parent);


        //Checar porq checa 1 en uno

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getEmailParent =  edtgetEmailParent.getText().toString();
                String getPassParent = edtgetPassParent.getText().toString();

                String c1 = "email";
                String c2 = "password";
                String c3 = "table";
                String table = "parents";
                String position = "Tutor";

                String url = "/AcitivitiesMain/loginAccounts/loginAll.php?";//Siempre agregar el ?



                if (Utils.emailandPassValidation(getEmailParent,txtemailParent,getPassParent,txtPassParent)){


                    WebServices.loginAll(getContext(),table,getEmailParent,getPassParent,url,c1,c2,c3,
                            btnParent,preferences,position,relativeLayout);

                    //Una vez que esten bien los resultados en vez de regresar la palabra succesful que me de el id del alumno para asi guardarlo
                }





            }
        });


    }


}
