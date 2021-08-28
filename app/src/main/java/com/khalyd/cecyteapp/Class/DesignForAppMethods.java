package com.khalyd.cecyteapp.Class;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Activities.NoInternetConnetivity;
import com.khalyd.cecyteapp.ActivityDirectivo.MainActivityDirectivo;
import com.khalyd.cecyteapp.ActivityParent.MainActivityParent;
import com.khalyd.cecyteapp.ActivityTeacher.MainActivityTeacher;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.MainActivity;
import com.khalyd.cecyteapp.R;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class DesignForAppMethods {



  /*  public  void toolbarMethod (Toolbar toolbar, String title,Context context){
        toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
            //Con el getSupportActionBar se pudee obtener todos los metodos de un actionbar

    }*/

    public static void AlertDialogInfo (AlertDialog.Builder alertDialog, String messageCustom){

        //  AlertDialog.Builder =  new AlertDialog.Builder()Lo creare en la clase

        alertDialog.setTitle("Aviso").setMessage(messageCustom).setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();


            }
        });


        AlertDialog dialog =  alertDialog.create();
        dialog.show();

    }

    public static void AlertDialogCustom (AlertDialog.Builder alertDialog, String TitleCustom, String messageCustom,DialogInterface.OnClickListener hereMethod, String btnPositive){

        //  AlertDialog.Builder =  new AlertDialog.Builder()Lo creare en la clase

        alertDialog.setTitle(TitleCustom).setMessage(messageCustom).setPositiveButton(btnPositive, hereMethod);


        AlertDialog dialog =  alertDialog.create();

        dialog.show();


    }

    public static void getTheVersionMoreActualFromDB(Context context, String complementoUrl,
                                                     final SharedPreferences preferences, final Activity activity) {

        //Aqui pondre la version q tenga la base de datos o sea la mas actual

        StringRequest requestString;
        context = context.getApplicationContext();

        try {

            String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)


            final Context finalContext = context;


            requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Con el response obtengo el id


                    Log.i("VersionReturn","= " + response);

                    if (response.trim().equalsIgnoreCase("DontNeedUpdate")){

                    //Normal
                        DesignForAppMethods.Untilto(finalContext,preferences,activity);


                    }else if (response.trim().equalsIgnoreCase("ServersOnMaintenance")){

                        int serverOnMaintinience  = 1;
                        String  keyForServers = "serverNotWorking";

                        Utils.setContentViewForApp(finalContext,serverOnMaintinience,keyForServers,null);
                        //Intent hacia la info


                    }else {


                        //Un Intent al Update o sea  la playstore
                        int serverOnMaintinience  = 2;
                        String  keyForServers = "serverNotWorking";


                        Utils.setContentViewForApp(finalContext,serverOnMaintinience,keyForServers,response);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                    Log.i("Error Volley","= " + error);
                    int serverOnMaintinience  = 3;
                    String keyForServers = "serverNotWorking";
                    Utils.setContentViewForApp(finalContext,serverOnMaintinience,keyForServers,null);
                    //No se pudo establecer conexión con el servidor
                }

            }){

                //POr ser metodo POST se usa params
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {



                    String versionInstalled =  Utils.getVersionActually(preferences);
                    Log.i("Info", " = " + versionInstalled);

                    Map<String, String> parametros = new HashMap<>();
                    parametros.put(Utils.VERSION_CODE,versionInstalled);
                    return parametros;

                }
            };

           // DEFAULT_TIMEOUT_MS = 9 sec cada uno
            requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 4,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

        }
    }

    public static void Untilto(Context context,SharedPreferences preferences,Activity activity){
        //Activity o AppCompatAcivity

        //19/03/18 Puse los flags ya que en dispositovs menor a 22 api presentaba un erro y necesitaba los flags


        Intent student = new Intent(context,MainActivity.class);
        student.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent parent = new Intent(context,MainActivityParent.class);
        parent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent teacher = new Intent(context,MainActivityTeacher.class);
        teacher.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent directivo = new Intent(context,MainActivityDirectivo.class);
        directivo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent home = new Intent(context,FirtsActivtiChooseTwo.class);
        home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if(!TextUtils.isEmpty(Utils.getSharedPreferencesData(preferences,"email")) &&
                !TextUtils.isEmpty(Utils.getSharedPreferencesData(preferences,"pass")) ){ //Hay algo

            if (Utils.getSharedPreferencesData(preferences,"position").equals("Estudiante")){ //Los distintos perfiles

                context.startActivity(student);

            }else if (Utils.getSharedPreferencesData(preferences,"position").equals("Tutor")){

                context.startActivity(parent);

            }else if (Utils.getSharedPreferencesData(preferences,"position").equals("Teacher")){

                context.startActivity(teacher);

            }else if (Utils.getSharedPreferencesData(preferences,"position").equals("Directivo")){

                context. startActivity(directivo);
            }

            //Be inicailizated account

        }else {


                context.startActivity(home);

        }


        activity.finish();
        /*AppCompatActivity compatActivity = new AppCompatActivity();
        compatActivity.finish();*/
    }

    //El siguiente metodo es el filtro para los fondos de la app en el cual se rota si la
    // imagen viene vertical (90ª grados de rotacion)

    public static Bitmap filterBitmap(Bitmap bimage1,Bitmap bimage2, ImageView FirtsImage,ImageView secondFirts,Context context,String info,String infoIfLandScape){ //Aqui le va a llegar el bitmap y este regresara compuesto

        //Puede haber un error aqui porq lo hice durmiendo



        if (bimage1 != null){ //Es q va a componer el bitmap1

            int height1 = bimage1.getHeight();
            int width1 = bimage1.getWidth();


            if (width1 < height1) { //Is portroait

                bimage1 = ResizeBitmapIfPortrait(bimage1,0);

                if (bimage1 != null ){


                    FirtsImage.setImageBitmap(bimage1);

                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), info);

                    return bimage1;



                }else {

                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), "La imagen no pudo ser procesada");

                }


            }else { //Es landscape

                try {

                    bimage1 =  RotateBitmap(bimage1,90);


                    if (bimage1 != null){ //Si regresara null pues no se pudo

                        FirtsImage.setImageBitmap(bimage1);

                        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), infoIfLandScape);


                        return bimage1;

                    }else {


                        String message = "La imagen es demasiada grande, por favor escoja una más chica (Actualmente estamos trabajando en mas opciones de subida de imagenes mas pesadas) ";

                        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), message);
                    }

                }catch (Exception e){

                    Log.i("ErroBitMap","= " + e);
                    String message = "Intentamos rotar la imagen pero no se pudó, por favor escoja otra imagen";

                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), message);

                }

            }

        }else if (bimage2 != null){//Es q va a componer el bitmap2


            int height = bimage2.getHeight();
            int width = bimage2.getWidth();


            if (width < height) { //Is portroait

                bimage2 = ResizeBitmapIfPortrait(bimage2,0);


                if (bimage2 != null ){


                    secondFirts.setImageBitmap(bimage2); //Image


                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), info);

                    return bimage2;

                }else {

                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), "La imagen no pudo ser procesada");

                }


            }else { //Es landscape

                try {


                    bimage2 =  RotateBitmap(bimage2,90); //90 grados


                    if (bimage2 != null  ){ //Si regresara null pues no se pudo

                        secondFirts.setImageBitmap(bimage2); //Aqui lo estoy poniendo
                        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), infoIfLandScape);

                        return bimage2;


                    }else {


                        String message = "La imagen es demasiada grande, por favor escoja una más chica (Actualmente estamos trabajando en mas opciones de subida de imagenes mas pesadas) ";

                        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), message);
                    }


                }catch (Exception e){

                    Log.i("ErroBitMap","= " + e);

                    String message = "Intentamos rotar la imagen pero no se pudó, por favor escoja otra imagen";

                    DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(context), message);

                }



            }


        }


        return null;
    }



    private static Bitmap RotateBitmap(Bitmap resource, int angle){

        //Funcion para rotar la imagen
        //Siempre voy a importar las imagenes xhdpi

        try {

            int widht = resource.getWidth();
            int height = resource.getHeight();

            if (widht < 2500 && height < 2500){

                Matrix matrix = new Matrix();
                matrix.postRotate(angle);
                return Bitmap.createBitmap(resource,0,0,resource.getWidth(),resource.getHeight(),matrix,true);

            }else {



                Bitmap rezizeBitma =  Bitmap.createScaledBitmap(resource,widht/3,height/3,true);

                Matrix matrix = new Matrix();
                matrix.postRotate(angle);

                return Bitmap.createBitmap(rezizeBitma,0,0,rezizeBitma.getWidth(),rezizeBitma.getHeight(),matrix,true);


            }

            //No puede darle la vuelta a un bitmap tan grande


        }catch (IllegalArgumentException e){

            Log.i("CantBitMap", " = " + e );

            return null; //Si regresa null muestra un error
        }

    }
    //El problema inició desde que mi telefono agarraba las imagenes verticales y las ponia horizontales en la app
    //Entonces lo q hice fue crear estos metodos para poder rotar la imagen
    private static Bitmap ResizeBitmapIfPortrait(Bitmap resource, int angle){

        //Funcion para rotar la imagen
        //Siempre voy a importar las imagenes xhdpi

        try {

            //Entonces, si las iamgenes verticals las agarra horizontales nunca entraria a este metodo, pero aun asi estara por si agarra
            //una imagen vertical

            int widht = resource.getWidth();
            int height = resource.getHeight();

            if (widht < 2500 && height < 2500){

                Matrix matrix = new Matrix();
                matrix.postRotate(angle);//Si le pongo cero supongo q no hara nada o si le pongo null tamopoc lo hara
                return Bitmap.createBitmap(resource,0,0,resource.getWidth(),resource.getHeight(),matrix,true);


            }else {



                Bitmap rezizeBitma =  Bitmap.createScaledBitmap(resource,widht/3,height/3,true);

                Matrix matrix = new Matrix();
                matrix.postRotate(angle);

                return Bitmap.createBitmap(rezizeBitma,0,0,rezizeBitma.getWidth(),rezizeBitma.getHeight(),matrix,true);

                //Aqui ya lo habia puesto para hacer q en la app le llegara una imagen ya resize
            }

            //No puede darle la vuelta a un bitmap tan grande


        }catch (IllegalArgumentException e){

            Log.i("CantBitMap", " = " + e );

            return null; //Si regresa null muestra un error
        }

    }

    //Este metodo hara que la imagen sea mas facil de colocar al telefono y lo haga mas rapido

    public static Bitmap resizeBitmapForPutApp(Bitmap bitmap){


        //Esto es para resize a la imagen

        int widtth = bitmap.getWidth();
        int height = bitmap.getHeight();

        Log.i("WithdBefore" , "= " + widtth);

        if (widtth < 2500 && height < 2500)  //Estos valores son en px
           {

               return bitmap; //Regresa el original

        }else {

            Bitmap rezizeBitma =  Bitmap.createScaledBitmap(bitmap,widtth/4,height/4,true);
            //Nuevas dimensiones

           return Bitmap.createBitmap(rezizeBitma,0,0,rezizeBitma.getWidth(),rezizeBitma.getHeight(),null,true);
            //Crea el bitmap de nuevo
        }

    }

    public static Bitmap rotateImg(Bitmap resource, int angle){

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(resource,0,0,resource.getWidth(),resource.getHeight(),matrix,true);

    }

    public static void AlertDialogInfoNoCancelable (AlertDialog.Builder alertDialog, String messageCustom){

        //No se cancela la info

        alertDialog.setCancelable(false).setTitle("Aviso").setMessage(messageCustom).setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();


            }
        });


        AlertDialog dialog =  alertDialog.create();
        dialog.show();

    }











}
