package com.khalyd.cecyteapp.Class;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Activities.NoInternetConnetivity;
import com.khalyd.cecyteapp.ActivityDirectivo.MainActivityDirectivo;
import com.khalyd.cecyteapp.ActivityParent.MainActivityParent;
import com.khalyd.cecyteapp.ActivityTeacher.MainActivityTeacher;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.MainActivity;
import com.khalyd.cecyteapp.CheckingInternetClasses.MyApp;
import com.khalyd.cecyteapp.Models.Students;
import com.khalyd.cecyteapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;



public class ServicesStudent {
    //Pueden servir en otros perfiles

    private static final Boolean isUpdate = true;
    //Se lo cambie a privado aun asi lo termino llamando lo cual haria poner doble vez esto

    public static void updateStudent (Context context, final String name, final String lastname, final String grupo,
                                      String complementoUrl, final Class intent, final SharedPreferences preferences) {

        //Le pondre el valor true si actualiza y busque false si no ha actualizado


        StringRequest requestString;
        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php
        context = context.getApplicationContext();


            try {

                String ip = context.getString(R.string.ip);
                String url = ip + complementoUrl; //Signo ? (?)

                final Context finalContext = context;


                requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Con el response obtengo el id


                        Log.i("UpdateProfile","= " + response);


                        if (response.trim().equalsIgnoreCase("succesful")){

                            int groupStudentAsignado = Integer.parseInt(grupo); //Pasando a int el numero del string


                            Toasty.success(finalContext, "Actualizado", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(finalContext,intent);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setGroupStudent(preferences,groupStudentAsignado); //En este punto se supone q ya estaria todo
                            //isUpdateFirts Lo llame asi para q no interfiera con el valor
                            Log.i("NoGroup", " = " + groupStudentAsignado);
                            finalContext.startActivity(i);

                            //Hasta aqui ya estaria guardando el grupo

                        }else if (response.trim().equalsIgnoreCase("nosuccesful")){



                            Toasty.error(finalContext,"Intentalo de nuevo",Toast.LENGTH_SHORT).show();


                        }else if (response.trim().equalsIgnoreCase("ERRORFATAL")){


                            Toasty.error(finalContext,"Algo esta mal",Toast.LENGTH_SHORT).show();


                        }else if (response.trim().equalsIgnoreCase("ProfileUpdated")){


                            Toasty.error(finalContext,"Este perfil ya fue actualizado",Toast.LENGTH_SHORT).show();


                        }else {

                            Toasty.error(finalContext,"Ocurrio un problema con el servidor",Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                        Log.i("Error Volley","= " + error);
                        Toasty.error(finalContext,"No se pudo establecer conexión con el servidor",Toast.LENGTH_SHORT).show();

                    }

                }){

                    //POr ser metodo POST se usa params
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        String email = Utils.getSharedPreferencesData(preferences,"email");
                        Map<String, String> parametros = new HashMap<>();
                        //Derecha los valores

                        parametros.put(Utils.SEARCH_EMAIL,email);
                        parametros.put(Utils.SEARCH_NAME,name);
                        parametros.put(Utils.SEARCH_LASTANAME,lastname);
                        parametros.put(Utils.SEARCH_GROUP,grupo);
                        //Izquierda (k) = Esos van a ser los nombres por los cual el metodo post buscara

                        //Nombre de las varibales en las que revisara del archivo ph
                        return parametros;

                        //El del directivo va a ser una actividad diferente
                    }
                };

                requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 25,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);
                //requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS *
                // 25, Asi seria uno de un minuto de espera si no ya seria mucho asi voy ir actualizando cada uno


            }catch (Exception e){


                Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

            }
    }

    public static void updateProfilePictuerUser (Context context, final Bitmap codebase64, String complementoUrl, final SharedPreferences preferences,
                                                 final String nameFolder,final String table) {

        //Este metodo actualiza la imagen de perfil de todos los perfiles

        final SharedPreferences.Editor editor = preferences.edit();
        StringRequest requestString;
        context = context.getApplicationContext();
        try {

            Toasty.info(context, "Procesando...", Toast.LENGTH_SHORT).show();

            String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)

            final Context finalContext = context;


            requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String responsePicture) {

                    //Con el response obtengo el id
                    //Aqui va entrar la resolucion de la imagen bajarsela con el bitmap

                    Log.i("UpdateProfilePicture","= " + responsePicture);

                    if (responsePicture.trim().equalsIgnoreCase("succesful")){

                        //Cambie el metodo que hacia que cambiara el nombre de las imagenes (?) para q no se guarde doble vez

                        //Estas son las tablas que deberian de estar llegando como parametro

                        if (table.equals("students")){

                            MyApp.getInstance().clearCache();
                            Toasty.success(finalContext, "Actualizado", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(finalContext, MainActivity.class); //No le pongo el splash porq es obvio que tiene q regresar aqui
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            finalContext.startActivity(i);
                            MyApp.getInstance().clearCache();

                            //En futuras updates poner el NavigationBottom
                        }else if (table.equals("directivo")){

                            MyApp.getInstance().clearCache();
                            Toasty.success(finalContext, "Actualizado", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(finalContext, MainActivityDirectivo.class); //No le pongo el splash porq es obvio que tiene q regresar aqui
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            finalContext.startActivity(i);
                            MyApp.getInstance().clearCache();


                        }else if (table.equals("parents")){

                            MyApp.getInstance().clearCache();
                            Toasty.success(finalContext, "Actualizado", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(finalContext, MainActivityParent.class); //No le pongo el splash porq es obvio que tiene q regresar aqui
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            finalContext.startActivity(i);
                            MyApp.getInstance().clearCache();



                        }else if (table.equals("teacher")){

                            MyApp.getInstance().clearCache();
                            Toasty.success(finalContext, "Actualizado", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(finalContext, MainActivityTeacher.class); //No le pongo el splash porq es obvio que tiene q regresar aqui
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            finalContext.startActivity(i);
                            MyApp.getInstance().clearCache();
                        }

                    }else if (responsePicture.trim().equalsIgnoreCase("nosuccesful")){

                        Toasty.error(finalContext,"No se pudo actualizar tu foto de perfil",Toast.LENGTH_SHORT).show();

                    }else if (responsePicture.trim().equalsIgnoreCase("ImageTooLong")){

                        Toasty.error(finalContext,"Escoge una foto de mas baja resolución",Toast.LENGTH_SHORT).show();

                    }else if (responsePicture.trim().equalsIgnoreCase("NoImage")){

                        Toasty.error(finalContext,"El archivo seleccionado no es una imagen ",Toast.LENGTH_SHORT).show();

                    }else if (responsePicture.trim().equalsIgnoreCase("NoAgain")){
                        //Ya despues lo quitare porque podria haber una mejor forma de solucionar esto

                        Toasty.error(finalContext,"Ya has actualizado tu foto de perfil",Toast.LENGTH_SHORT).show();

                    }else {


                        Toasty.error(finalContext,"Algo ha salido mal",Toast.LENGTH_SHORT).show();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError errorPicImage) {
                    //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                    Log.i("Error Volley","= " + errorPicImage);
                    Toasty.error(finalContext,"No se pudo establecer conexión con el servidor",Toast.LENGTH_SHORT).show();

                }

            }){

                //POr ser metodo POST se usa params
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                   String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL); //Va a buscar por email



                    String pictureBitmapString = becomeToStringBitmap(codebase64);

                    Map<String, String> parametros = new HashMap<>();

                    parametros.put(Utils.SEARCH_EMAIL,email);
                    parametros.put(Utils.BITMAP_ENCODE,pictureBitmapString);
                    parametros.put(Utils.GO_FOLDER,nameFolder);
                    parametros.put(Utils.SEARCH_TABLE,table);

                    //A partir de ahora ya no se cambiara el nombre (?) Se elimino el cache

                    return parametros;

                    //El del directivo va a ser una actividad diferente

                }
            };

            requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 25,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }

    private static String becomeToStringBitmap(Bitmap bitmap){

           //Tengo q comparar los metodos ya que debe de haber una mejor manaera de bajar la resolucion sin afectar las imagenes

          //Y encerrar en un try y un catch

        //ya se donde esta el problema de porque se detenia la app
        //O sea aqui la estoy reduciendo para q se envie no para q la ponga

        try {

            int height = bitmap.getHeight();
            int width = bitmap.getWidth();

            if (width < 2500 && height < 2500 ){

                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,70,arrayOutputStream);
                //La calidad si es 100 creo q es toda la calidad q tiene la foto si es 50 ps la mitad

                byte [] toByte = arrayOutputStream.toByteArray();

                Log.i("INFOPHOTO1"," = " + toByte.toString());

                return Base64.encodeToString(toByte,Base64.DEFAULT);
            }else {


                Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap,width/3,height/3,true);
                //Tecnicamente aqui no habria tanto problema al dividirlo ya esa imagen no sera para que sea mostrada en una alta calidad
                //el filter es el q hace q no se vea pixeleado

                //matrix = null (?)  Dont need this case
                Bitmap newBitmap = Bitmap.createBitmap(resizeBitmap,0,0,resizeBitmap.getWidth(),resizeBitmap.getHeight(),null,true);


                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                newBitmap.compress(Bitmap.CompressFormat.JPEG,40,arrayOutputStream);

                byte [] toByte = arrayOutputStream.toByteArray();


                return Base64.encodeToString(toByte,Base64.DEFAULT);

            }


        }catch (IllegalArgumentException e){

            return "";

        }


    }

    public static void checkInfoStudent (Context context, String complementoUrl, final SharedPreferences preferences,
                                         final CircleImageView imgCircle, final TextView txtNameUser, final TextView txtSemestre) {

        //Le pondre el valor true si actualiza y busque false si no ha actualizado

        JsonObjectRequest jsonObjectRequest;

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php
        context = context.getApplicationContext();


        try {

            final String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)

            final Context finalContext = context;

            String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL); //Va a buscar por email
            Map<String, String> parametros = new HashMap<>();
            parametros.put("email",email);

            JSONObject params= new JSONObject(parametros);


                                                            //Al parecer listener es null
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d("response " ,"= " + response);
                    Students student = new Students();

                    JSONArray jsonArray = response.optJSONArray("students"); //Obten el JSONArray called
                    JSONObject jsonObject = null;


                    try {

                        jsonObject = jsonArray.getJSONObject(0); //Supongo q 0 porq pueden haber mas
                        student.setName(jsonObject.optString("nombre"));
                        student.setSemestre(jsonObject.optString("semestre"));
                        student.setUrl(jsonObject.optString("url"));


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                    if (student.getName() != null && student.getSemestre() != null ){

                        txtNameUser.setText(student.getName());
                        txtSemestre.setText(student.getSemestre());

                        if (student.getUrl() != null) {
                            Glide.with(finalContext).load(ip + "AcitivitiesMain/" + student.getUrl()).into(imgCircle);
                        }else {

                            Glide.with(finalContext).load("https://blog.searchofficespace.com/wp-content/uploads/2017/10/no-image.jpg").into(imgCircle);

                        }
                    }else {

                        txtNameUser.setText("Actualiza tus datos");
                        txtSemestre.setText("Presionando el boton de arriba");

                        if (student.getUrl() != null) {
                            Glide.with(finalContext).load(ip + "AcitivitiesMain/" + student.getUrl()).into(imgCircle);
                        }else {

                            Glide.with(finalContext).load("https://blog.searchofficespace.com/wp-content/uploads/2017/10/no-image.jpg").into(imgCircle);

                        }


                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        //Set messages error

                    Log.d("errorVolley " ,"= " + error);
                    Toasty.error(finalContext,error.toString(),Toast.LENGTH_LONG).show();


                }
            });


         /*Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL); //Va a buscar por email

                Map<String, String> parametros = new HashMap<>();

                parametros.put("email",email);

                return parametros;

                //Voy a necesitar : nombre semestre  url where email

            }*/

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);


        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }

    public static void checkInfoStudentGET (Context context, String complementoUrl, final SharedPreferences preferences,
                                            final CircleImageView imgCircle, final TextView txtNameUser,
                                            final TextView txtSemestre, final ImageView editProfileimg, final TextView showInfo) {

        //Le pondre el valor true si actualiza y busque false si no ha actualizado
        //Esto es lo q aparece en el header
        //Lo hice porq me esta devolviendo datos por eso esta por metodo GET


        JsonObjectRequest jsonObjectRequest;

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php
        context = context.getApplicationContext();


        try {

            final String ip = context.getString(R.string.ip);



            String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL);

            String url = ip + complementoUrl + "email" + "=" + email; //Signo ? (?)

            final Context finalContext = context;

            //Va a buscar por email




            //Al parecer listener es null
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d("response " ,"= " + response);
                    Students student = new Students();

                    JSONArray jsonArray = response.optJSONArray("students"); //Obten el JSONArray called
                    JSONObject jsonObject = null;



                    try {

                        jsonObject = jsonArray.getJSONObject(0); //Supongo q 0 porq pueden haber mas
                        student.setName(jsonObject.optString("nombre"));
                        student.setSemestre(jsonObject.optString("groupStudent"));
                        student.setUrl(jsonObject.optString("url"));


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                    //semestre es igual a group



                        if (student.getName().equals("") && student.getSemestre().equals("0")) {

                            txtNameUser.setText("Actualiza tus datos");
                            txtSemestre.setText("Presionando el botón de editar");
                            editProfileimg.setVisibility(View.VISIBLE);
                            //Aqui le voy a pasar un boton para habitarlo e inabilitarlo dependiendo del caso

                            if (student.getUrl().equals("")) {


                                showInfo.setVisibility(View.VISIBLE);
                                String img = finalContext.getString(R.string.img_noImageProfile);
                                Glide.with(finalContext).load(img).into(imgCircle);

                            } else {


                                Glide.with(finalContext).load(ip + "/AcitivitiesMain/AllImages/" + student.getUrl()).into(imgCircle);
                                showInfo.setVisibility(View.INVISIBLE);
                            }


                        } else {

                        //Aqui se supone q ya deberia de trer todos los datos

                            editProfileimg.setVisibility(View.INVISIBLE);
                            String namec = student.getName();
                            txtNameUser.setText(namec);
                            txtSemestre.setText("Grupo: " + student.getSemestre()); //Este semestre se cambio a grupo

                            int groupStudents = Integer.parseInt(student.getSemestre());

                            if (Utils.getGroupStudent(preferences) == 0){//No hay grupo asi q tengo q entrar aqui a asignarle

                                Utils.setGroupStudent(preferences,groupStudents);

                                //Luego de aqui se iria al fragmentNotfications y tendria q buscar este numero si no lo encuentra pondria un error yo
                                //Pondria actualiza tus datos o todavia no se ha publciado nada en tu grupo //Creo q esto era para el tema de los pots
                                //Aun asi lo voy hacer

                            }



                            if (!student.getUrl().equals("")) {

                                Glide.with(finalContext).load(ip + "/AcitivitiesMain/AllImages/" + student.getUrl()).into(imgCircle);
                                showInfo.setVisibility(View.INVISIBLE);
                            } else {

                                String img = finalContext.getString(R.string.img_noImageProfile);
                                showInfo.setVisibility(View.VISIBLE);
                                Glide.with(finalContext).load(img).into(imgCircle);

                            }
                        }






                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Set messages error

                    Log.d("errorVolley " ,"= " + error);

                    Intent i =  new Intent(finalContext, NoInternetConnetivity.class);
                    Bundle info =  new Bundle();
                    info.putString("NoServer","NoWorkingServer");
                    i.putExtras(info);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finalContext.startActivity(i);

                }
            });



            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);


        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }


    public static void sendComments (Context context, String complementoUrl, final SharedPreferences preferences, final String title, final String desription) {

        //Le pondre el valor true si actualiza y busque false si no ha actualizado

        StringRequest requestString;
        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php
        context = context.getApplicationContext();

        try {

            String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)




            final Context finalContext = context;


            requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Con el response obtengo el id


                    Log.i("SendCommens","= " + response);

                    if (response.trim().equalsIgnoreCase("succesful")){



                        Toasty.success(finalContext, "Comentario Enviado...", Toast.LENGTH_SHORT).show();


                    }else if (response.trim().equalsIgnoreCase("nosuccesful")){

                        Toasty.error(finalContext,"Ocurrio un error al enviar el archivo",Toast.LENGTH_SHORT).show();

                    }else {

                        Toasty.error(finalContext,"Algo ha salido mal",Toast.LENGTH_SHORT).show();

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                    Log.i("Error Volley","= " + error);
                    Toasty.error(finalContext,"No se pudo establecer conexión con el servidor",Toast.LENGTH_SHORT).show();

                }

            }){

                //POr ser metodo POST se usa params
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL); //Va a buscar por email


                    Map<String, String> parametros = new HashMap<>();
                    parametros.put(Utils.SEARCH_EMAIL,email);
                    parametros.put("title",title);
                    parametros.put("description",desription);
                    return parametros;
                    //El del directivo va a ser una actividad diferente
                }
            };

            requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

        }
    }

}
