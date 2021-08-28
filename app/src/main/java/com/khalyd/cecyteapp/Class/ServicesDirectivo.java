package com.khalyd.cecyteapp.Class;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.khalyd.cecyteapp.Models.Directivo;
import com.khalyd.cecyteapp.Models.ForOthers;
import com.khalyd.cecyteapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class ServicesDirectivo {


    public static void updateProfileDirectivo (Context context, final String name, final String lastname, final String table, final String position,
                                                       String complementoUrl, final Class intent, final SharedPreferences preferences) {


        StringRequest requestString;
        context = context.getApplicationContext();

        try {

            String ip = context.getString(R.string.ip);
            String url = ip + complementoUrl;


            final Context finalContext = context;


            requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Con el response obtengo el id


                    Log.i("UpdateProfileAll","= " + response);

                    if (response.trim().equalsIgnoreCase("succesful")){


                        Toasty.success(finalContext, "Actualizado", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(finalContext,intent);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        //isUpdateFirts Lo llame asi para q no interfiera con el valor
                        finalContext.startActivity(i);


                    }else if (response.trim().equalsIgnoreCase("nosuccesful")){


                        Toasty.error(finalContext,"Intentalo de nuevo",Toast.LENGTH_SHORT).show();


                    }else {

                        Toasty.error(finalContext,"Ocurrió un problema con el servidor",Toast.LENGTH_SHORT).show();

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
                    parametros.put(Utils.SEARCH_EMAIL,email); //No se guardo el correo en los intents
                    parametros.put(Utils.SEARCH_NAME,name);
                    parametros.put(Utils.SEARCH_LASTANAME,lastname);
                    parametros.put(Utils.SEARCH_CARGO,position);
                    parametros.put(Utils.SEARCH_TABLE,table);

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

    public static void checkInfoDirectivo (final Context context, String complementoUrl, final SharedPreferences preferences,
                                           final CircleImageView imgCircle, final TextView txtNameUser,
                                           final TextView txtLastName, final String table, final TextView txtIntructuions, final TextView position) {

        //Al ser del directivo le voy a pasar un valor con el cual lo indentificara php

        JsonObjectRequest jsonObjectRequest;

        try {

            final String ip = context.getString(R.string.ip);



            String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL);

            String url = ip + complementoUrl + "email" + "=" + email + "&" + "table" + "=" + table + "&" + "position" + "=" + "Directivo"; //Signo ? (?)

            //Va a buscar por email


            //Al parecer listener es null
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    Log.d("responseDirectivo " ,"= " + response);
                    Directivo other = new Directivo();

                    JSONArray jsonArray = response.optJSONArray(table); //Obten el JSONArray called
                    JSONObject jsonObject = null;
                    //Aqui me va a regresar tres cosas

                    try {

                        jsonObject = jsonArray.getJSONObject(0); //Supongo q 0 porq pueden haber mas
                        other.setName(jsonObject.optString("name"));
                        other.setLastname(jsonObject.optString("lastname"));
                        other.setUrl(jsonObject.optString("url"));
                        other.setCargo(jsonObject.optString("position"));



                    } catch (JSONException e) {

                        e.printStackTrace();

                    }


                    if (other.getName().equals("") && other.getLastname().equals("")) {

                        txtNameUser.setText("Después toque en ");
                        txtLastName.setText("   \"Actualizar perfíl\"");
                        position.setText("Toca el menú de la parte de arriba");
                        position.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_menu_directivo, 0);


                        if (other.getUrl().equals("") || other.getUrl() == null ) {

                            //alt + 124 = ||

                            String img = context.getString(R.string.img_noImageProfile);
                            Glide.with(context).load(img).into(imgCircle);


                        } else {


                            Glide.with(context).load(ip + "/AcitivitiesMain/AllImages/" + other.getUrl()).into(imgCircle);
                            //Componer el logo

                        }


                    } else {



                        String namec = other.getName();
                        txtNameUser.setText(namec);
                        txtLastName.setText(other.getLastname());
                        position.setText(other.getCargo());

                        if (!other.getUrl().equals("")) {

                            Glide.with(context).load(ip + "/AcitivitiesMain/AllImages/" + other.getUrl()).into(imgCircle);

                            txtIntructuions.setVisibility(View.INVISIBLE);

                        } else {

                            String img = context.getString(R.string.img_noImageProfile);

                            Glide.with(context).load(img).into(imgCircle);

                        }
                    }





                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Set messages error

                    Utils.GotoInfoAboutStateApp(context,3);


                }
            });



            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 3,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

        }
    }
    //Este hace el envio de los datos o hace los pots
    public static void sendDataDirectivo  (Context context, final Bitmap codebase64, String complementoUrl,
                                           final String title, final String description, final SharedPreferences preferences
                                           , final String folder, final String profile, final ProgressBar progressBar) {



        StringRequest requestString;
        context = context.getApplicationContext();

        try {

            progressBar.setVisibility(View.VISIBLE);



            String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)

            final Context finalContext = context;
            requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Log.i("PostSuccesful","= " + response);

                    if (response.trim().equalsIgnoreCase("succesful")){


                        Toasty.success(finalContext, "Publicado", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(finalContext, MainActivityDirectivo.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        finalContext.startActivity(i);

                    }else if (response.trim().equalsIgnoreCase("nosuccesful")){

                        Toasty.error(finalContext,"No se pudo publicar, intente de nuevo",Toast.LENGTH_SHORT).show();

                    }else {
                        Toasty.error(finalContext,"Algo ha salido mal",Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.i("Error Volley","= " + error);
                    Toasty.error(finalContext,"No se pudo establecer conexión con el servidor",Toast.LENGTH_SHORT).show();
                }
            }){

                //POr ser metodo POST se usa params
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {



                    String pictureBitmapString = becomeToStringBitmap(codebase64);
                    Log.i("Return ", "= " + pictureBitmapString);
                    //Esta regresando algo vacio

                    String email = Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL);

                    Map<String, String> parametros = new HashMap<>();

                    parametros.put(Utils.SEARCH_EMAIL,email);
                    parametros.put(Utils.SEARCH_TITLE,title);
                    parametros.put(Utils.SEARCH_DESCRIPTION,description);
                    parametros.put(Utils.BITMAP_ENCODE,pictureBitmapString);
                    parametros.put(Utils.SEARCH_FOLDER,folder);
                    parametros.put(Utils.SEARCH_PROFILE,profile);


                    return parametros;
                }
            };

            requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 4,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

        }catch (Exception e){
            progressBar.setVisibility(View.INVISIBLE);
            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }

    private static String becomeToStringBitmap(Bitmap bitmap){

        //TODO Debo de componer esto y bajarle la resolucion al igual que hice con las otras imagenes

        try {

            //Este error tambien se presentaria en los pots para poner una imagen

                int height = bitmap.getHeight();
                int width = bitmap.getWidth();

                if (height < 2000 && width < 2000) {


                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, arrayOutputStream);

                    //Maximo 80, para q no envie tanta calidad
                    byte[] toByte = arrayOutputStream.toByteArray();


                    return Base64.encodeToString(toByte, Base64.DEFAULT);
                }else {


                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, arrayOutputStream);

                    //Maximo 50

                    byte[] toByte = arrayOutputStream.toByteArray();


                    return Base64.encodeToString(toByte, Base64.DEFAULT);

                }



        }catch (Exception e){


            return ""; //Va a devolver algo vacio
        }


    }//Componer este metodo o sea pasarle el bitmap ya filtrado

    public static void updateBackgroundApp (final Context context, final Bitmap codebase64, final Bitmap codebase64SecondImage, String complementoUrl) {



        StringRequest requestString;
        try {

            Toasty.info(context,"Procesando...",Toast.LENGTH_LONG).show();
            String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)

            final Context finalContext = context;

            requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Con el response obtengo el id


                    Log.i("UpdateBackground","= " + response);

                    if (response.trim().equalsIgnoreCase("allSuccesful")){


                        Toasty.success(finalContext, "Actualizado correctamente", Toast.LENGTH_SHORT).show();

                        Utils.intentent(finalContext,MainActivityDirectivo.class);


                        try {

                            String ms = "Si lo desea puede cerrar sesión un momento para comprobar que las iamgenes se visualicen correctamnete";
                            DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(finalContext),ms);

                        }catch (Exception e){

                            e.printStackTrace();

                        }


                    }else if (response.trim().equalsIgnoreCase("SecondPicIsBad")){

                        Toasty.error(finalContext,"Algo salió mal, intentelo de nuevo",Toast.LENGTH_SHORT).show();

                    }else if (response.trim().equalsIgnoreCase("NoCorrectFirtsImage")){

                        Toasty.error(finalContext,"La primera imagen no es vertical",Toast.LENGTH_SHORT).show();

                    }else if (response.trim().equalsIgnoreCase("NoCorrectSecondImage")){

                        Toasty.error(finalContext,"La segunda imagen no es vertical",Toast.LENGTH_SHORT).show();

                    }else if (response.trim().equalsIgnoreCase("NoImage")){

                        Toasty.error(finalContext,"El archivo seleccionado no es una imagen ",Toast.LENGTH_SHORT).show();

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

                   //Va a buscar por email

                    String pictureBitmapString = becomeToStringBitmap(codebase64);
                    String pictureBitmapStringPciture2 = becomeToStringBitmap(codebase64SecondImage);

                    Map<String, String> parametros = new HashMap<>();

                    //Solo le voy a estar enviando los datos o sea las imagenes php q haga todo

                    parametros.put("Image1",pictureBitmapString);
                    parametros.put("Image2",pictureBitmapStringPciture2);

                    Log.i("values ", "= " + parametros);

                    return parametros;

                    //El del directivo va a ser una actividad diferente

                }
            };

            requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 15,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

           // 2500 Milesegundos asi que hay algunos q no tienen internet rapido asi q puedo poner *10
            //Un minuto sera el maximo tiempo de espera

        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }


}
