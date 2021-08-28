package com.khalyd.cecyteapp.ActivityTeacher;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesDocente;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import es.dmoral.toasty.Toasty;

public class EditProfileTeacher extends AppCompatActivity {


    //Profile Teacher
    private EditText txtName,txtLastName;
    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_teacher);
        preferences = Utils.getMySharedPreferences(this);
        UI();
        setToolbar();
        String ms = "Le recomendamos que haga uso de mayúsculas y minúsculas para una buena presentación de sus datos";
        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(this),ms);

    }


    private void UI (){

        txtName = findViewById(R.id.edit_name_docente);
        txtLastName = findViewById(R.id.edit_apellidos_docente);

    }


    private void setToolbar( ){
        Toolbar toolbar = findViewById(R.id.toolbar_options_docente);
        setSupportActionBar(toolbar); //Para soporte en el framgment
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Editar Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Esta es una actividad

        getMenuInflater().inflate(R.menu.menu_edit_profile_update,menu);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Son booleans

        switch (item.getItemId()){

            case R.id.UpdateProfile:
                allUpdate();
                return true;

        }

        return super.onOptionsItemSelected(item);



    }

    private void allUpdate(){

        String name = txtName.getText().toString();
        String lastanme = txtLastName.getText().toString();
        String table = "teacher"; //Checar bien a q tabla estoy mandando los datos q me puedo enviarlos a otro lado
        //Dato curioso, envie los datos a otra tabla yh segun hizo la inserccion, pero no la hizo ya q no habia ningn email del mismo tipo en esa tabla
        //A la q fue enviada

        if (!name.isEmpty() && !lastanme.isEmpty()){


            String url = "/AcitivitiesMain/updateAllProfileExeptionStudent.php?";

                ServicesDocente.updateProfileAllExeptioStudent(this,name,lastanme,table,url,MainActivityTeacher.class,preferences);

        }else {

            Toasty.error(this,"Complete todos los campos", Toast.LENGTH_LONG).show();

        }

    }


}
