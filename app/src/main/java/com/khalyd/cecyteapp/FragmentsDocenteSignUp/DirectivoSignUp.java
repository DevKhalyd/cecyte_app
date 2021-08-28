package com.khalyd.cecyteapp.FragmentsDocenteSignUp;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.khalyd.cecyteapp.Activities.SplashScreen;
import com.khalyd.cecyteapp.ActivitiesDeprecated.LoginTeacher;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectivoSignUp extends Fragment {

    private SharedPreferences preferences;

    public DirectivoSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_directivo_sign_up, container, false);
        // Inflate the layout for this fragment
        preferences  = Utils.getMySharedPreferences(getContext());

        setSignUp(v);

        return v;


    }

    private void setSignUp (View v){

        final LinearLayout linearLayout = v.findViewById(R.id.linearVertical_Directivo_SigUp);
        final CircularProgressButton btn = v.findViewById(R.id.sign_up_Teacher_Directivo);

        final TextInputLayout InpEnrollmentTeacher =  v.findViewById(R.id.enrollmentTeacher_Professional_Directivo);
        final EditText edtEnrollmentTeacher =  v.findViewById(R.id.get_enrollmentTeacher_Professional_Directivo);

        final TextInputLayout InpEmailTeacher = v.findViewById(R.id.emailTeacher_Directivo);
        final EditText edtEmailTeacher =  v.findViewById(R.id.get_emailTeacher_Directivo);

        final TextInputLayout InpPassTeacher =  v.findViewById(R.id.passwordTeacher_Directivo);
        final EditText edtPassTeacher =  v.findViewById(R.id.get_passwordTeacher_Directivo);

        final TextInputLayout InpPassTeacherConfirm =  v.findViewById(R.id.password_Confirm_Teacher_Directivo);
        final EditText edtPassTeacherConfirm =  v.findViewById(R.id.get_password_Confirm_Teacher_Directivo);

        //_Directivo para evitar la fatiga


        final AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numberCard = edtEnrollmentTeacher.getText().toString();
                String emailTeacher = edtEmailTeacher.getText().toString();
                String password = edtPassTeacher.getText().toString();
                String passwordConfirm = edtPassTeacherConfirm.getText().toString();

                String url = "/AcitivitiesMain/signUpAll/signUpPersonal.php?";

                String c1 = "numbers";
                String c2 = "email";
                String c3 = "password";
                String c4 = "table";
                String c5 = "where";
                String value4= "directivo";
                String value5= "noasignado";

                String [] parametres = {"succesful","nosuccesful","accountBusy","emailBusy","AccNoFound"}; //SomethingIsbad
                int  color = getResources().getColor(R.color.red2);
                //int  colorpostive = getResources().getColor(R.color.barSignUp);
                int txtBig = 16;
                int txtlitle = 15;

                String messageDirectivoPHP= "Otra cuenta ya fue registrada con el mismo número. \n \n" +
                        "Si esta seguro de que ese número le corresponde y no la ha registrado, por favor infórmaselo al programador.";

                String message = "El número asignado no existe";

                String messageContext = "1. El número que ingresó es incorrecto   \n \n " +
                        "2. Al ser una app beta, no se han agregado todos los números asignados por el desarrollador";

                String docenteError = "*Introduzca sus 7 dígitos asignados ";

                String position = "Directivo";


                //En vez de numbercard podria poner 2 if donde indetifique si es alumno o profesor o sea

                //Ctrl + c = Copy all line


                if (Utils.checkIfSpacesIsCorrectSignUpPersonal(numberCard,InpEnrollmentTeacher,emailTeacher,InpEmailTeacher,password,InpPassTeacher,passwordConfirm,InpPassTeacherConfirm,
                        docenteError,7,linearLayout,color)){


              WebServices.signUpPersonal(getContext(),numberCard,emailTeacher,password,url,c1,c2,c3,c4,c5,value4,
                            value5,btn,parametres[0],parametres[1],parametres[2],parametres[3],parametres[4],linearLayout,color,txtBig,
                            txtlitle,dialogo,messageDirectivoPHP,message,messageContext,position,preferences);

                    //Modificar este metodo ya que se tiene que ir al activity y luego abrir un fragment
                    //Una manera de solucionarlo seria poniendo el Fragment trasanction
                    //If bundle != null = Fragment Transaction txt.setText = Email Send

                }
                //Update path anothers signUp
            }

        });



    }



}
