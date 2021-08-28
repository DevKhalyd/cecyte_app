package com.khalyd.cecyteapp.ActivityDirectivo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesDirectivo;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class PotsAnyProfile extends AppCompatActivity {


    //Todos los pots funcionan de acuerdo a los codigos
    private static final int COD_SELECT = 10;
    private Toolbar toolbar;
    private LayoutInflater inflater;
    private ImageView potsImage;
    private EditText title,description;
    private TextView touchHere,info,touchRotate;
    private Bitmap imageBitmap;
    private String folder,profile,profileCalled;
    private ProgressBar bar;
    private String groupSelectForDirectivo;
    private Spinner spinnerGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pots_profile_student);
        UI();
        getBundle();
        AlertDialogInfoPotsAll();
        setToolbar();
        textViewHere();
        bar.setVisibility(View.INVISIBLE);

    }

    private void AlertDialogInfoPotsAll (){

        if (profile.equals("10")){

        String ms = "Esta publicación será publicada para todos los perfíles sin excepción.";
        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(this),ms);

        }else if (profile.equals("45")){

            openAlertDialog();

        }

    }

    private void openAlertDialog() {

        inflater = this.getLayoutInflater();
        View content = inflater.inflate(R.layout.set_a_new_post_depend_case,null);
        spinnerGroup = content.findViewById(R.id.spinnerGroupsSelected);
        fillInSpinner();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(content)
                .setTitle("Seleccione un grupo para públicar (Tutores)")
                .setMessage("\nEn futuras actualizaciones se podrá publciar para todos los grupos (Tutores). Por el momento sólo se podrá publicar por grupos\n")
                .setCancelable(false)
                .setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        getSupportActionBar().setTitle("P. " + profileCalled + " ("  + groupSelectForDirectivo  +")");


                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                onBackPressed();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void fillInSpinner(){

        ArrayList<Integer> fillin = new ArrayList<>();

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

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,fillin);
        spinnerGroup.setAdapter(adapter);

        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                groupSelectForDirectivo = adapterView.getItemAtPosition(position).toString(); //Lo convierte a String
                //Si no mal se a php no le importa si le llega el numero en String ya q tambien se puede tomar como int
                //Asi q le pasare el group.toString

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void getBundle(){

        Bundle myBundle = new Bundle();

        try {

            myBundle = getIntent().getExtras();

             folder = myBundle.getString("folder");
             profile = myBundle.getString("profile");
             profileCalled = myBundle.getString("toolbar");

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    private void UI(){
        toolbar = findViewById(R.id.toolbar_pots_student);
        potsImage = findViewById(R.id.imagePots);
        touchHere = findViewById(R.id.touchHereFoto);
        info = findViewById(R.id.text_Previsualation);
        title = findViewById(R.id.titlePots);
        description = findViewById(R.id.descriptionPots);
        bar = findViewById(R.id.load_post_Directivo);
        touchRotate = findViewById(R.id.touchHereForRotateImg);
        touchRotate.setVisibility(View.INVISIBLE);

    }

    private void textViewHere(){

        touchHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setPhoto();
            }
        });


    }

    private void setToolbar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        if (profile.equals("45")){

                        getSupportActionBar().setTitle("P. " + profileCalled + "("  + "seleccionando..."  +")");


        }else{

            getSupportActionBar().setTitle("P. " + profileCalled);

        }

    }

    private void setPhoto(){

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i,"Selecciona"),COD_SELECT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case COD_SELECT:

                if (data != null) {

                    Uri photo = data.getData();

                    try {

                        //Este forma de poner el codigo me parece mejor ya q no necesita traerlo completo y luego dividirlo si no lo divide al instante
                        imageBitmap = DesignForAppMethods.resizeBitmapForPutApp(MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo));
                        //Esta forma es mejor para traer los bitmaps

                        int height2 = imageBitmap.getHeight();
                        int width2= imageBitmap.getWidth();
                        Log.i("DimensionsAfter", " = H,W " + height2 + " " + width2 ) ;
                        potsImage.setImageBitmap(imageBitmap);
                        info.setText("Previsualización");
                        rotateImg();
                        touchHere.setText("...");
                        String ms = "La imagen ya esta siendo previsualizada, toque el botón para rotar la imagen, una vez que ya este seguro de la posición " +
                                "de la imagen presione \"PUBLICAR\"";
                        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(this),ms);

                    } catch (IOException e) {

                        e.printStackTrace();
                        Toasty.error(this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toasty.error(this, "No seleccionaste nada", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void rotateImg(){

        touchRotate.setVisibility(View.VISIBLE);
        touchRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageBitmap = DesignForAppMethods.rotateImg(imageBitmap,90);
                potsImage.setImageBitmap(imageBitmap);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_postar_something,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.postear:

                postearSomething();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void postearSomething() {

        //Identeficando al padre
        if (profile.equals("45")){

            String one = title.getText().toString();
            String two = description.getText().toString();

            if (!one.isEmpty() && !two.isEmpty() ){
                String url = "/AcitivitiesMain/IntoAppDirectivo/sendPOst.php?";
                //Debo de buscar por medio de las shared preferences si algo esta mal es q esta llegando mal el el mail
                ServicesDirectivo.sendDataDirectivo(this,imageBitmap,url,one,two, Utils.getMySharedPreferences(this),folder,groupSelectForDirectivo,bar);
                //Puede enviar el bitmap vacio
            }else {
                Toasty.error(this,"Por lo menos deben de estar llenos los dos campos (Asunto y Descripción)",Toast.LENGTH_LONG).show();
                //Cuando le de en los botones
            }


        }else{

            String one = title.getText().toString();
            String two = description.getText().toString();


            if (!one.isEmpty() && !two.isEmpty() ){
                String url = "/AcitivitiesMain/IntoAppDirectivo/sendPOst.php?";
                //Debo de buscar por medio de las shared preferences si algo esta mal es q esta llegando mal el el mail
                ServicesDirectivo.sendDataDirectivo(this,imageBitmap,url,one,two, Utils.getMySharedPreferences(this),folder,profile,bar);
                //Puede enviar el bitmap vacio
            }else {
                Toasty.error(this,"Por lo menos deben de estar llenos los dos campos (Asunto y Descripción)",Toast.LENGTH_LONG).show();
                //Cuando le de en los botones
            }

        }



    }


}
