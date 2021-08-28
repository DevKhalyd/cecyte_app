package com.khalyd.cecyteapp.ActivityDirectivo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.khalyd.cecyteapp.Class.ServicesDirectivo;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import es.dmoral.toasty.Toasty;

public class EditProfileDirectivo extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edtName,edtLastname,position; //Cargo que tiene
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_directivo);
        UI();
        setToolbar();

    }


    private void UI(){

        preferences = Utils.getMySharedPreferences(this);
        toolbar = findViewById(R.id.toolbar_Directivo_Edit_Profile);
        edtName = findViewById(R.id.edtDirectivoName);
        edtLastname = findViewById(R.id.edtDirectivoApellidos);
        position = findViewById(R.id.edtDirectivoCargo);
    }



    private void  setToolbar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(" Editar perfíl");
        //Con el getSupportActionBar se pudee obtener todos los metodos de un actionbar
    }

    private void getDataForUpdate(){
        String name = edtName.getText().toString();
        String lastname = edtLastname.getText().toString();
        String cargo = position.getText().toString();

        String table =  "directivo";
        String url = "/AcitivitiesMain/updateAllProfileExeptionStudent.php?";

        if(!name.isEmpty() && !lastname.isEmpty() && !cargo.isEmpty() ){


            ServicesDirectivo.updateProfileDirectivo(this,name,lastname,table,cargo,url,MainActivityDirectivo.class,preferences);


        }else {

            Toasty.error(this,"Deben de star llenos todos los campos",Toast.LENGTH_SHORT).show();

        }


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
                getDataForUpdate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
