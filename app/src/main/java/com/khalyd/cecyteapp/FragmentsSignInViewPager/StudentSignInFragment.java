package com.khalyd.cecyteapp.FragmentsSignInViewPager;


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
import com.khalyd.cecyteapp.ActivitysSubjectsMain.MainActivity;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentSignInFragment extends Fragment {

    //A todos los fragments le fue borrado los  bundles para evitarnos de errores


    private ImageView imgback;
    private EditText edtgetNumberStudent;
    private CircularProgressButton btnStudent;
    private SharedPreferences preferences;



    //LoginStudentActivity

    public StudentSignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login_student, container, false);
        UI(v);
        preferences = Utils.getMySharedPreferences(getContext());
        AllMethods(v);
        // Inflate the layout for this fragment
        return v;

    }




    private void AllMethods (View v){

        goToSignInAs(v);

        loginStudent(v);

        ImageView view = v.findViewById(R.id.loadingViewStudent);

        String img = getString(R.string.SecondImage); //Para los logins y registros

        Utils.getImageFromUrl(img,getContext(),view);

        String message = "Ahora sólo tienes que deslizar hacia la izquierda para escoger tu perfíl";

        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(getContext()),message);


    }


    private void UI(View v){

        edtgetNumberStudent = v.findViewById(R.id.get_email_student_sign_in);

    }

    private void loginStudent(View v){

        btnStudent = v.findViewById(R.id.loginStudent);

        final TextInputLayout txtinputStudent = v.findViewById(R.id.email_student_sign_in);


        final TextInputLayout txtInputpass = v.findViewById(R.id.password_Student);
        final EditText edtgetPass = v.findViewById(R.id.get_password_Student);
        final RelativeLayout relativeLayout = v.findViewById(R.id.relative_main_student);


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
                String position = "Estudiante"; //Esto es para saber hacia donde se va dirigir la app la proxima vez

                if (Utils.emailandPassValidation(getEmail,txtinputStudent,getPass,txtInputpass)){

                    WebServices.loginAll(getContext(),table,getEmail,getPass,url,c1,c2,c3,btnStudent,preferences,position,relativeLayout);

                }

            }
        });





    }



    private void goToSignInAs(View v){

        imgback = v.findViewById(R.id.backToSIGN);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), FirtsActivtiChooseTwo.class); //A esa clase debe de ir ahora
                startActivity(intent);



            }
        });

    }






}
