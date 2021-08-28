package com.khalyd.cecyteapp.ActivitysSubjectsMain;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.R;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import es.dmoral.toasty.Toasty;

public class EditProfile extends AppCompatActivity {

    //Darle mas diseño
    //Este sera la actividad  q sera utilizado para su update del studiante

    private String group;
    //Este es el que debo de usar el V7
    private android.support.v7.widget.Toolbar toolbar;
    private Spinner spinner;
    private EditText txtName, txtLastName;
    private SharedPreferences preferences;
    private String name,lastanme;



    //This is working Fine


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = Utils.getMySharedPreferences(this);
        setContentView(R.layout.activity_edit_profile); //Puedo asignale otra layout dependiendo de su Registro
        UI();
        Methods();


    }


    private void  Methods(){

        toolbarMethod();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

        String meesage = "Hola, el primer paso para usar la app es añadir tus datos (nombre,apellidos,grupo (Te recomendamos usar mayúsculas y minúsculas, para una mejor presentación de tus datos))\n\n" +
                         "Asegúrate que los datos que ingreses sean verídicos, ya qué esa información sera vista por: \n\n" +
                         "Profesores \n" +
                         "Directivo \n" +
                         "Tu Tutor \n\n" +
                         "Además que te ayudará a identificarte de los demás estudiantes. \n" +
                         "No se permiten apodos o caracteres especiales.\n\n" +
                         "Está información solo se puede EDITAR UNA VEZ en el tiempo que uses la app";

        DesignForAppMethods.AlertDialogInfo(builder,meesage);
        setAdapter();


    }

    private void UI(){

        toolbar = findViewById(R.id.toolbar_editProfileStudent);
        spinner = findViewById(R.id.spinner2);
        txtLastName = findViewById(R.id.txtxLastName);
        txtName = findViewById(R.id.txtname);


    }

    public  void toolbarMethod (){

        toolbar.setTitle("Editar Perfil");
        setSupportActionBar(toolbar);


        //Con el getSupportActionBar se pudee obtener todos los metodos de un actionbar
    }



    private void setAdapter(){

        //Primary Type Should be Primitive

        ArrayList<Integer> fillin = new ArrayList<>();

        try {


           int GroupstoShow = Utils.getSharedPreferencesInteger(preferences,"Year");

           switch (GroupstoShow){

               case 1:

                   for (int i = 1; i < 7; i++){ //Primer año

                       fillin.add(200 + i);

                   }
                   break;
               case 2:
                   for (int i = 1; i < 6; i++){ //Segundo año

                       fillin.add(400 + i);

                   }
                   break;
               case 3:

                   for (int i = 1; i < 6; i++){ //Ultimo año

                       fillin.add(600 + i);

                   }

                   break;
               case 0: //NO encontro nada en las shared preferernces

                   for (int i = 1; i < 6; i++){ //Tengo q comprobar y entender lo q dice la intruciion no debo de ponerla asi nadamas

                       fillin.add(600 + i);

                       if (i == 5){

                           for (int o = 1; o < 6; o++){

                               fillin.add(400 + o);

                               if (o == 5){

                                   for (int u = 1; u < 7; u++){ //Al parecer hay 6 grupos del primer grado

                                       fillin.add(200 +  u);

                                   }
                               }
                           }
                       }
                   }
                   break;
           }



        }catch (IllegalArgumentException e){

            e.printStackTrace();
            //NO tendria porq llegar aqui
        }



        //20 caracteres

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,fillin);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                group = adapterView.getItemAtPosition(position).toString(); //Lo convierte a String
                //Si no mal se a php no le importa si le llega el numero en String ya q tambien se puede tomar como int
                //Asi q le pasare el group.toString

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_edit_profile_update,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.UpdateProfile:
                //Aqui va a ir el alertDialogo
                getAllDataForSendPHP();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    private void getAllDataForSendPHP(){

        //Esto siempre debe de ir en un onClickListener ya q asi puede obtener los datos mas rapidamente o en realtime
        name = txtName.getText().toString();
        lastanme = txtLastName.getText().toString();

        final String url = "/AcitivitiesMain/IntoAppStudent/updateStudent.php?";


        //Get the email
        if (!name.isEmpty() && !lastanme.isEmpty()){

        String title  = "Confirma tus datos";
        String message =  "Nombre(s): " + name + "\n\n" +
                "Apellidos: " + lastanme + "\n\n"
                + "Grupo: " + group;

        DesignForAppMethods.AlertDialogCustom(new android.app.AlertDialog.Builder(this), title, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ServicesStudent.updateStudent(EditProfile.this,name,lastanme,group,url,
                        MainActivity.class,preferences);
            }
        },"CONFIRMAR");


    }else {


         Toasty.error(getApplicationContext(),"Debes de completar todos los datos",Toast.LENGTH_SHORT).show();

        }



        //Windows mas l me cambia el {} para presionar de un solo toque

    }

    //Me quede en hacerlo funcionar con PHP y debo de editar el signUpAll

}
