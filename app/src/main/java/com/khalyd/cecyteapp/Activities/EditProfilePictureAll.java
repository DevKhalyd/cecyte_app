package com.khalyd.cecyteapp.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.DeadObjectException;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khalyd.cecyteapp.CheckingInternetClasses.MyApp;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class EditProfilePictureAll extends AppCompatActivity {

    private static final int COD_SELECT = 10;
    private ImageView imgRotate,imageToEdit;
    private TextView info;
    private Bitmap bitmapPhoto;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_picture_all);
        preferences = Utils.getMySharedPreferences(this);
        UI();
        setToolbar();
        setHome8();
        setImgRotate();

    }

    private void setHome8(){

        info.setVisibility(View.INVISIBLE);
        imgRotate.setVisibility(View.INVISIBLE);
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i,"Selecciona tu foto desde las siguientes opciones:"),COD_SELECT);
        MyApp.getInstance().clearCache();


    }

    private void UI(){
        imgRotate = findViewById(R.id.rotate_right);
        imageToEdit = findViewById(R.id.imageEditProfileAny);
        info = findViewById(R.id.infoNoImprtant);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            Uri photo = data.getData();


            try {


                String ms = "Al momento de presionar \"Actualizar\" puede ser que se tarde unos minutos en actualizar su foto de perfíl (Si es la primera vez que actualiza su foto de perfíl será al instante) ";

                DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(this),ms);

                info.setVisibility(View.VISIBLE);
                imgRotate.setVisibility(View.VISIBLE);

             //   bitmapPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(),photo);

                bitmapPhoto = DesignForAppMethods.resizeBitmapForPutApp(MediaStore.Images.Media.getBitmap(this.getContentResolver(),photo));
                //Tengo q darle su filtro para q pueda poner la imagen y para q tambien la pueda enviar

                imageToEdit.setImageBitmap(bitmapPhoto);

              /*  int newWidht = bitmapPhoto.getWidth();
                int newHeight = bitmapPhoto.getHeight();

                Log.i("TargetBitMap"," = " + newHeight + newWidht);*/
              //Efectivamente regresa nuevos valores

            } catch (IOException e) {

                e.printStackTrace();

            }


        }else {
            onBackPressed();
        }



    }

    private void  setToolbar(){

        Toolbar toolbar;

        toolbar = findViewById(R.id.toolbar_Edit_Picture_All);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(" Editar foto de perfil");
        //Con el getSupportActionBar se pudee obtener todos los metodos de un actionbar

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
                if (bitmapPhoto != null){

                  UpdatePictureProfile();

                }else {

                    onBackPressed();
                    Toasty.error(getApplicationContext(),"No había imagen para actualizar",Toast.LENGTH_SHORT).show();

                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setImgRotate(){

        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bitmapPhoto = DesignForAppMethods.rotateImg(bitmapPhoto,90);
                imageToEdit.setImageBitmap(bitmapPhoto);

            }
        });

    }




    private void UpdatePictureProfile() {
        //Enviando foto ya editada

        try{//Aqui debe de tratar de recuperar el nombre de la tabla q le llegue

            Bundle myBundle = new Bundle();
            myBundle = getIntent().getExtras();

            String tableFromBundle = myBundle.getString("tableUsers");
            String folderanyProfyle = myBundle.getString("folderUsers");

            //Tambien debi de haberle psasdo la carpeta no solo la tabla
            String url = "/AcitivitiesMain/AllImages/updateProfilePicture.php?";

            ServicesStudent.updateProfilePictuerUser(this,bitmapPhoto,url,preferences,folderanyProfyle,tableFromBundle);


        }catch (Exception e){

            e.printStackTrace();
            Toasty.error(this,"Un error fatal ha ocurrido, " +
                    "porfavor informelo a los desarrolladores para solucionarlo lo más pronto posible",Toast.LENGTH_LONG).show();

            //No debería de llegar a este punto

        }

    }

}
