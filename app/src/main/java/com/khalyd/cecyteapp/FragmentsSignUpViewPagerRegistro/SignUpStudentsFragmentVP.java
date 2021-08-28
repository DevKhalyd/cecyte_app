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
import com.khalyd.cecyteapp.ActivitiesDeprecated.LoginStudent;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpStudentsFragmentVP extends Fragment {


    private SharedPreferences preferences;

    public SignUpStudentsFragmentVP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_sign_up_student, container, false);
        preferences = Utils.getMySharedPreferences(getContext());
        ImageView view = v.findViewById(R.id.loadingViewStudentSignUp);
        String img = getString(R.string.SecondImage); //Para los logins y registros
        Utils.getImageFromUrl(img,getContext(),view);
        String message = "Para cambiar el perfíl de registro sólo debe DESLIZAR a la izquierda\n\n" +
                "1.Estudiante\n2.Tutor\n3.Docente y Directivo";
        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(getContext()),message);

        allViews(v);

        return v;
    }




    private void allViews(View v){

        goToBack(v);
        signupStudent(v);

        ImageView view = v.findViewById(R.id.loadingViewStudentSignUp);

        String img = getString(R.string.SecondImage);

        Utils.getImageFromUrl(img,getContext(),view);

    }

    private void signupStudent(View v){

        final CircularProgressButton btnStudent = v.findViewById(R.id.sign_Up_student);

        final TextInputLayout txtEnrollment  = v.findViewById(R.id.sign_up_student_number_enrollement);
        final EditText edtEnrollment = v.findViewById(R.id.get_Number_Student_sign_Up);

        final TextInputLayout txtEmailStudent = v.findViewById(R.id.email_student_sign_up);
        final EditText edtEmailStudent = v.findViewById(R.id.get_email_student_sign_up);

        final TextInputLayout inputpass = v.findViewById(R.id.password_stundet_sign_up);
        final EditText edtinputpass = v.findViewById(R.id.get_password_stundet_sign_up);

        final TextInputLayout inputPassConfirm = v.findViewById(R.id.password_stundet_sign_up_confirm);
        final EditText edtpassConfirm = v.findViewById(R.id.get_password_stundet_sign_up_confirm);

        final RelativeLayout relativeLayout = v.findViewById(R.id.relativesignin_Student);

        final AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());

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

                String position = "Estudiante";


                //Aqui seria estudiante para q vaya asi alla

                //Metodo por cambiar
                boolean checkIfStuundtIsRegister = Utils.getIfStudentBeforeSignUp(preferences);

                if (!checkIfStuundtIsRegister){//Falso porq no entrra la primera vez


                    if (Utils.checkIfSpacesIsCorrectSignUp(enrollmentStudent,txtEnrollment,null
                            ,email,txtEmailStudent,pass,inputpass,passconfirm,inputPassConfirm)){

                        //Cambiar por splashscreen para q el lo rediccione
                        WebServices.signUpAll(getContext(),enrollmentStudent,email,pass,url,c1,c2,c3,btnStudent,
                                parametres[0],parametres[1],parametres[2],parametres[3],parametres[4],parametres[5],relativeLayout,color,colorpostive,txtBig,txtlitle,null
                                ,dialogo,messageAlumno,preferences,position);

                    }//Este metodo siempre me regresara true ya q asi lo programe


                }else{


                    String messageError =  "Sólo puedes registrarte una vez";
                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(getContext()),messageError);

                }

            }
        });





    }

    private void goToBack(View v){
        View img = v.findViewById(R.id.back2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getContext(),FirtsActivtiChooseTwo.class);
                startActivity(i);

            }
        });
    }








}
