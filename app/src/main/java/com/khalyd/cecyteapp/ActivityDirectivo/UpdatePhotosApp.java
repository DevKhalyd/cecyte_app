package com.khalyd.cecyteapp.ActivityDirectivo;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
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

import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesDirectivo;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.R;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class UpdatePhotosApp extends AppCompatActivity {

    //Las imagenes cuadradas las debo de impedir en proximas actualizaciones
    //Tal vez esto lo pueda hacer con un if para q entre ahi (Logic)

    //06/03/2018 Tecnicamente esta actividadad ya esta funcionando correctamente

    private static final int COD_SELECT = 10 ;
    private static final int COD_SECON_IMAGE = 20 ;
    private Toolbar toolbar;
    private TextView txtOneTouch,txtSecondTouch;
    private ImageView firtsImage,secondFirts;
    private Bitmap bimage1,bimage2;
    private ImageView rotate1,rotate2;

    //Usar el onActivityResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_photos_app);
        UI();
        setToolbar();
        loadImages();
        String message  = "Desde aquí se puede actualizar la interfaz de inicio de la app \n\nLa primera imagen es de la pantalla principal" +
                "\n\nLa segunda imagen es de las cuentas de usuario";
             //   "Además las imagenes no deben ser de muy alta calidad ni de dimensiones muy exageradas (3000 x 3000) "; //Explicacion
             //Lo quite porq segun yo ya podria aceptar imagenes pas pesadas
        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(this),message);




    }

    private void UI(){

        toolbar = findViewById(R.id.updateFotosApptoolbar);
        txtOneTouch = findViewById(R.id.tocuhHereFirtsPic);
        txtSecondTouch = findViewById(R.id.tocuhHereSecondPic);
        firtsImage = findViewById(R.id.firtsPic);
        secondFirts  =findViewById(R.id.secondPic);
        rotate1 = findViewById(R.id.rotateFirtsImage);
        rotate2 = findViewById(R.id.rotateseconImage);
        rotate1.setVisibility(View.INVISIBLE);
        rotate2.setVisibility(View.INVISIBLE);

    }

    private void setToolbar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Fondos de Inicio (App)");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_edit_profile_update,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            //Colocar un layout en vertical
            case R.id.UpdateProfile:
                if (bimage1 != null && bimage2 != null){

                    String url = "/AcitivitiesMain/AllImages/updateBackGroundapp.php?";


                    ServicesDirectivo.updateBackgroundApp(this,bimage1,bimage2,url);//Aqui ya le estoy pasando los bitmaps
                    //compuesto. SI surge alguna duda lo puedo probar


                    //Poner un ProgressBar

                }else {

                    Toasty.error(getApplicationContext(),"Se deben de actualizar los dos fondos", Toast.LENGTH_SHORT).show();

                }

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadImages(){


        txtOneTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firtsImage.setImageBitmap(null);

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/");
                startActivityForResult(Intent.createChooser(i,"Seleccione"),COD_SELECT);

            }
        });


        txtSecondTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                secondFirts.setImageBitmap(null);

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/");
                startActivityForResult(Intent.createChooser(i,"Seleccione"),COD_SECON_IMAGE);

            }
        });

    }
    //Poner q la foto sea en vertical en un alert y poner los webservices
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Aqui las imagenes ya pasaron por el filtro asi q ahora pesan 1/3 de lo q pesaban originalmente

        switch (requestCode){

            //Siempre hay q checar bien el codigo

            //Solo tenia q poner el return para q obtubiera el bitmap el metodo

            case COD_SELECT:

                //Todo esto lo pase a un filtro donde compone los bitmaps y que me tiene q regresar un bitmap

                if (data != null) {

                    Uri photo = data.getData();

                    try {
                        //Componer los mensajes

                        //bimage1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo);

                        String message  = "La imagen ya esta siendo previsualizada, porfavor asegúrese " +
                                "que la imagen sea vertical y que se vea correctamente, ya que asi sera vista en la pantalla principal." +
                                " \n\n Ahora toque en \"Agregar el fondo de pantalla de las cuentas de usuario\"";

                        String messageLandScape  = "La imagen ya esta siendo previsualizada, al parecer la imagen era horizontal asi qué, hemos " +
                                "rotado la imagen 90° para que sea vertical, si la imagen no se rotó correctamente, sólo presione el botón de rotar."+
                                " \n\nAhora toque en \"Agregar el fondo de pantalla de las cuentas de usuario\"";

                        //Solo le psae la imagen para q la procese y ya la devuelva como debe ser
                        bimage1 = DesignForAppMethods.filterBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(),photo),null,firtsImage,null,this,message,messageLandScape);

                        Log.i("InfFirtImage"," = " + "Heigh = " + bimage1.getHeight() + " Width = " + bimage1.getWidth() + "FirtImage");

                        rotateImage1();



                    } catch (IOException e) {

                        e.printStackTrace();
                        Toasty.error(this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toasty.error(this, "No seleccionaste nada", Toast.LENGTH_SHORT).show();
                }
                break;

            case COD_SECON_IMAGE:

                if (data != null) {

                    Uri photo = data.getData();

                    try {


                    //    bimage2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo);//Get bitmap

                        String message = "La imagen ya esta siendo previsualizada, porfavor asegúrese " +
                                "que la imagen sea vertical y que se vea correctamente, ya que asi sera vista en la pantalla principal." +
                                " \n\nAhora toque en \"Actualizar\" " +
                                "\nSi tiene algun incoveniente pida ayuda al área de informáticos\n\n" +
                                "Después de presionar \"Actualizar\", sí lo desea puede cerrar sesión un momento para comprobar que las imágenes se visualicen correctamente";

                        String messageLandScape = "La imagen ya esta siendo previsualizada, al parecer la imagen era horizontal asi qué, hemos " +
                                "rotado la imagen 90° para que sea vertical, si la imagen no se rotó correctamente, sólo presione el botón de rotar." +
                                " \n\nAhora toque en \"Actualizar\" " +
                                "\nSi tiene algun incoveniente pida ayuda al área de informáticos o al desarrollador.\n\n" +
                                "Una vez que se haya asegurado que los fondos sean los correctos presione \"Actualizar\"; sí lo desea puede cerrar sesión un momento para comprobar que las imágenes se visualicen correctamente.";




                        //Solo le psae la imagen para q la procese y ya la devuelva como debe ser
                        bimage2 = DesignForAppMethods.filterBitmap(null, MediaStore.Images.Media.getBitmap(this.getContentResolver(), photo),null,secondFirts,this,message,messageLandScape);

                        rotateImage2();

                        Log.i("InfSecondImage"," = " + "Heigh = " + bimage2.getHeight() + " Width = " + bimage2.getWidth() + "SecondImage");

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

    private void  rotateImage1(){

        rotate1.setVisibility(View.VISIBLE);
        rotate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bimage1 = DesignForAppMethods.rotateImg(bimage1,180);
                firtsImage.setImageBitmap(bimage1);

            }
        });



    }

    private void rotateImage2(){

        rotate2.setVisibility(View.VISIBLE);
        rotate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bimage2 = DesignForAppMethods.rotateImg(bimage2,180);
                secondFirts.setImageBitmap(bimage2);

            }
        });


    }

}
