package com.khalyd.cecyteapp.ActivityParent;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.khalyd.cecyteapp.ActivityTeacher.MainActivityTeacher;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesDocente;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import es.dmoral.toasty.Toasty;

public class EditProfileParent extends AppCompatActivity {

    //Editar perfil Parent
    private SharedPreferences preferences;
    private EditText name,lastname;

    //Ya cuenta con el editperfilAll


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_parent);
        UI();
        setToolbar();
        String ms = "Le recomendamos que haga uso de mayúsculas y minúsculas para una buena presentación de sus datos";
        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(this),ms);
    }

    private void UI(){

        preferences = Utils.getMySharedPreferences(this);
        name = findViewById(R.id.edit_name_parent);
        lastname = findViewById(R.id.edit_apellidos_parent);

    }



    private void setToolbar( ){
        Toolbar toolbar = findViewById(R.id.toolbar_options_parent);
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

    private void allUpdate() {


        String name1 = name.getText().toString();
        String lastanme2 = lastname.getText().toString();
        String table = "parents"; //Checar bien a q tabla estoy mandando los datos q me puedo enviarlos a otro lado
        //Dato curioso, envie los datos a otra tabla yh segun hizo la inserccion, pero no la hizo ya q no habia ningn email del mismo tipo en esa tabla
        //A la q fue enviada

        if (!name1.isEmpty() && !lastanme2.isEmpty()){


            String url = "/AcitivitiesMain/updateAllProfileExeptionStudent.php?";

            ServicesDocente.updateProfileAllExeptioStudent(this,name1,lastanme2,table,url,MainActivityParent.class,preferences);

        }else {

            Toasty.error(this,"Complete todos los campos", Toast.LENGTH_LONG).show();

        }

    }


}



