package com.khalyd.cecyteapp.Class;


import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.khalyd.cecyteapp.Activities.SplashScreen;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.EditProfile;
import com.khalyd.cecyteapp.Adapters.AdapterNotificationsStudent;
import com.khalyd.cecyteapp.CheckingInternetClasses.ConnectivityReceiver;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;
import es.dmoral.toasty.Toasty;

public class WebServices implements ConnectivityReceiver.ConnectivityReceiverListener {


    public static void signUpAll (Context context, final String matricula, final String email, final String pass, String complementoUrl, final String c1, final String c2, final String c3, final CircularProgressButton progressDialog,
                                  final String allsuccesful, final String noSuccesful, final String accountBusy, final String emailInUse, final String errorInesperado, final String noexitsMatricula, final View layout, final int colornegative,final int colorPostive
                                , final int txtbig, final int txtlitle, final String anotherProblem,
                                  final AlertDialog.Builder builder, final String mensajePHP,
                                  final SharedPreferences preferences, final String position) {


       // colorPostive no se esta usando pero si lo quito tendre q mover todos los metodos

        //Este metodo lo usa tanto el Tutor y el Estudiante

                                //PHP= Dependiendo del perfil sera puesto el mensaje
        //   txtbig == Pocas letreas txtlitle = Bastante letras

        //Aqui tanto como en el signUpPersonal tengo que enviar a la activity correspondiente segun corresponda en vez de enviar
        //los datos

        StringRequest requestString;

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php

        context = context.getApplicationContext();

        try {

            String ip = context.getString(R.string.ip);

             String url = ip + complementoUrl; //Signo ? (?)


            if (!matricula.isEmpty() && !email.isEmpty() && !pass.isEmpty()){



                progressDialog.startAnimation();

                final Context finalContext = context;


                requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                       progressDialog.revertAnimation();

                       //Use a method where save data about the group to show

                        Log.i("Info","= " + response);

                        if(position.equals("Estudiante" )&& response.trim().equalsIgnoreCase("Top")){//Registrado ult year

                            Intent i = new Intent(finalContext,EditProfile.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setDataPreferencesShowToGroup(preferences,email,pass,position,3);//3 = Ultimo año o tercer año
                            finalContext.startActivity(i);

                        }else if (position.equals("Estudiante" )&& response.trim().equalsIgnoreCase("Mid")){

                            Intent i = new Intent(finalContext,EditProfile.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setDataPreferencesShowToGroup(preferences,email,pass,position,2);//2 = Segundo año
                            finalContext.startActivity(i);


                        }else if (position.equals("Estudiante" )&& response.trim().equalsIgnoreCase("Bot")){

                            Intent i = new Intent(finalContext,EditProfile.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setDataPreferencesShowToGroup(preferences,email,pass,position,1);//1 = Primer año
                            finalContext.startActivity(i);


                        }else if (position.equalsIgnoreCase("Tutor") && response.trim().equalsIgnoreCase(allsuccesful)){

                            Intent i = new Intent(finalContext,SplashScreen.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setDataPreferences(preferences,email,pass,position);
                            finalContext.startActivity(i);
                            //Aqui iria al SplashScreen y ya ese se encargaria de Reedireccionar
                            //Se supone q hasta aqui ya estaria guardando los datos sin tener q volver a ingresar los datos
                        }else if (response.trim().equalsIgnoreCase(noSuccesful)){//Hasta aqui termina el primero


                            snackBarCustomSign(layout,colornegative,"Hubo un error, por favor intente de nuevo",txtbig,null);
                            //Esto no lo puedo provocar

                        }else if (response.trim().equalsIgnoreCase(accountBusy)){


                            snackBarCustomSign(layout, colornegative, "Ingrese otra matrícula (Esa matrícula ya esta registrada)", txtlitle, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    AlertDialog(builder,mensajePHP);
                                }
                            });



                        }else if (response.trim().equalsIgnoreCase(emailInUse)){

                            snackBarCustomSign(layout, colornegative, "El correo ingresado ya esta registrado", txtbig, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String message = "Ya hay otra cuenta registrada con el mismo correo. Por favor ingrese otro correo";

                                    AlertDialog(builder,message);

                                }
                            });




                        }else if (response.trim().equalsIgnoreCase(errorInesperado)){

                            snackBarCustomSign(layout,colornegative,"Hubo un problema al conectar con el servidor",txtbig,null);


                        }else if (response.trim().equalsIgnoreCase(noexitsMatricula)){//Este se usara para mas info, con la tabla de estudiantes


                            if (position.equals("Tutor")){

                                snackBarCustomSign(layout, colornegative, "Esa matrícula no existe", txtbig, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String message = "1.La matricula no se encontró en la base de datos  \n" +
                                                " 2. Asegúrese que ya se haya registrado el alumno, si ya esta registrado use la misma matricula que haya ingresado el alumno";

                                        AlertDialog(builder,message);
                                    }
                                });


                            }else if (position.equals("Estudiante")){

                                snackBarCustomSign(layout, colornegative, "Esa matrícula no existe", txtbig, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String message = "1.La matricula no se encontró en la base de datos  \n" +
                                                "2. Al encontrarse la app en fase beta, todavía no se han agregado todas las matrículas \n" +
                                                "3. Esta app solo esta disponible en Plantel 05 Etla, Oaxaca (Por el momento)";

                                        AlertDialog(builder,message);
                                    }
                                });

                            }



                        }else if (response.trim().equalsIgnoreCase(anotherProblem)){//Este ya no se estaria usando ya que se cambio el php pero lo dejare

                            snackBarCustomSign(layout, colornegative, "El alumno no se ha registrado", txtbig, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String message = "Primero se debe de registrar el alumno";

                                    AlertDialog(builder,message);
                                }
                            });
                            //Usar con boton


                        }else {

                            snackBarCustomSign(layout, colornegative, "Error desconocido, por favor informe sobre este error!!", txtlitle, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    String message = "Por favor informe sobre este error, para una mejor experiencia de la app " + response + " Gracias...";

                                 AlertDialogImportant(builder,message);

                                 //Crear tabla para los errores etc

                                }
                            });


                        } //Podria hacer mas else if para que no solo dependa de esos mensajes


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                        Log.i("Error Volley","= " + error);
                        progressDialog.revertAnimation();
                        snackBarCustomSign(layout,colornegative,"No se pudo establecer conexión con el servidor",txtbig,null);

                    }

                }){

                    //POr ser metodo POST se usa params
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        Map<String, String> parametros = new HashMap<>();

                        parametros.put(c1,matricula); //Derecha los valores
                        parametros.put(c2,email);
                        parametros.put(c3,pass);
                        //Izquierda (k) = Esos van a ser los nombres por los cual el metodo post buscara

                        //Nombre de las varibales en las que revisara del archivo php


                        return parametros;

                    }
                };

                requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

            }else {

                Toast.makeText(context,"Completa todos los datos" , Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

            progressDialog.revertAnimation();

            snackBarCustomSign(layout,colornegative,"Comprueba tu conexiónn a Internet",txtbig,null);
        }


    }


    public static void loginAll (Context context, final String table, final String email, final String pass, String complementoUrl,
                                 final String c1, final String c2, final String c3table, final CircularProgressButton btncircle,
                                 final SharedPreferences preferences, final String position, final View layout) {


        StringRequest requestString;
        final int sizeLetter  = 15;
        final int  color = context.getResources().getColor(R.color.red2);

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php

        context = context.getApplicationContext();





            if (!email.isEmpty() && !pass.isEmpty()){

                try {

                    String ip = context.getString(R.string.ip);

                    String url = ip + complementoUrl; //Signo ? (?)

                    btncircle.startAnimation();


                final Context finalContext = context;


                requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Con el response obtengo el id


                        reverAnimationCustom(btncircle);

                        Log.i("Info","= " + response);

                        //Tengo que cambiar los valores

                        //Primer parametro
                        //Segundo parametro
                        //Tercer parametro
                        //Checar cuantos response van a llegar de cada uno

                        //Reorganizar para lanzamiento
                        if (response.trim().equalsIgnoreCase("AccesesSuccesful")){

                            //Cuando actualice sus datos se pondra el +, datos que seran guardados en las shared preferences



                        //    Toasty.success(finalContext, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(finalContext,SplashScreen.class); //A todas se les cambio
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setDataPreferences(preferences,email,pass,position); //Set Data
                            finalContext.startActivity(i);


                        }else if (response.trim().equalsIgnoreCase("passwordIncorrect")){



                            snackBarCustomSignPersonal(layout,color,"Contraseña incorrecta",sizeLetter);


                        }else if (response.trim().equalsIgnoreCase("NoexitsAcc")){


                            snackBarCustomSignPersonal(layout,color,"Correo o contraseña incorrectos",sizeLetter);


                        }else {


                            snackBarCustomSignPersonal(layout,color,"Algo ha salido mal",sizeLetter);


                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                        Log.i("Error Volley","= " + error);
                        reverAnimationCustom(btncircle);
                        Toasty.error(finalContext,"No se pudo establecer conexión con el servidor",Toast.LENGTH_SHORT).show();

                    }

                }){

                    //POr ser metodo POST se usa params
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        Map<String, String> parametros = new HashMap<>();
                        //Derecha los valores
                        parametros.put(c1,email);
                        parametros.put(c2,pass);
                        parametros.put(c3table,table);

                        //Izquierda (k) = Esos van a ser los nombres por los cual el metodo post buscara

                        //Nombre de las varibales en las que revisara del archivo ph

                        return parametros;

                        //El del directivo va a ser una actividad diferente

                    }
                };

                requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

            }catch (Exception e){

                    reverAnimationCustom(btncircle);
                    Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

           }}else {

                Toast.makeText(context,"Completa todos los datos" , Toast.LENGTH_SHORT).show();
            }
        }

    private static void snackBarCustomSign (final View layout, int color, String message, int txtSize, View.OnClickListener lsitener){

        Snackbar snackbar = Snackbar
                .make(layout, message, Snackbar.LENGTH_LONG)
                .setAction("INFO",lsitener);


        snackbar.setActionTextColor(Color.WHITE);//Color text of btn
        View snakbarView = snackbar.getView();
        snakbarView.setBackgroundColor(color);
        TextView txt = snakbarView.findViewById(android.support.design.R.id.snackbar_text);
        txt.setTextColor(Color.WHITE);
        txt.setTextSize(txtSize);
        snackbar.show();

    }

    public static void snackBarCustomSignPersonal (final View layout, int color, String message, int txtSize){

        Snackbar snackbar = Snackbar
                .make(layout, message, Snackbar.LENGTH_LONG);

        View snakbarView = snackbar.getView();
        snakbarView.setBackgroundColor(color);
        TextView txt = snakbarView.findViewById(android.support.design.R.id.snackbar_text);
        txt.setTextColor(Color.WHITE);
        txt.setTextSize(txtSize);
        snackbar.show();

    }


    private static void reverAnimationCustom(final CircularProgressButton btn){

            btn.revertAnimation(new OnAnimationEndListener() {
                @Override
                public void onAnimationEnd() {

                    btn.setText("Entrar");
                }
            });

        }


    public static void AlertDialog (AlertDialog.Builder alertDialog,String messageCustom){

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

    private static void AlertDialogImportant (AlertDialog.Builder alertDialog,String messageCustom){

        //  AlertDialog.Builder =  new AlertDialog.Builder()Lo creare en la clase

        alertDialog.setTitle("INFORME").setMessage(messageCustom).setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });

        AlertDialog dialog =  alertDialog.create();
        dialog.show();

    }

    public static void CheckIfInternetIsWorking(View layout,Context context){


        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected,layout,context);


    }

    private static void showSnack(boolean isConnected,View layout,Context context) {


        String message;
        int color;
        int colorBackgorund;


        if (isConnected){

            message = "Conexión establecida";
            int colroverde = context.getResources().getColor(R.color.coloPrymaryDarkToLocate);
            color = Color.WHITE;

            colorBackgorund = colroverde;

        }else {
            message = "No se detecto conexión a internet";
            color = Color.WHITE;
            colorBackgorund = Color.RED;
        }

        Snackbar snackbar = Snackbar.make(layout,message,Snackbar.LENGTH_LONG);




        View snakbarView = snackbar.getView();
        snakbarView.setBackgroundColor(colorBackgorund);
        snackbar.setActionTextColor(Color.WHITE);
        View sbView = snackbar.getView();
        TextView textView =  sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();


    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

       // showSnack(isConnected);


    }

    public static void signUpPersonal (Context context, final String matricula, final String email, final String pass, String complementoUrl,final String c1, final String c2, final String c3, final String c4table, final String c5where, final String valuec4, final String valuec5,
                                       final CircularProgressButton progressDialog, final String allsuccesful, final String noSuccesful, final String accountBusy, final String emailInUse, final String noexitsMatricula, final View layout, final int colornegative,
                                       final int txtbig, final int txtlitle, final AlertDialog.Builder builder, final String mensajePHP, final String messagePerosnal ,
                                       final String messageAlert, final String position, final SharedPreferences preferences) {


        //Este sirve para registrar al docente
        //Position = Directivo o Docente


        //PHP= Dependiendo del perfil sera puesto el mensaje
        //   txtbig == Pocas letreas txtlitle = Bastante letras

        StringRequest requestString;

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php

        context = context.getApplicationContext();

        try {

            String ip = context.getString(R.string.ip);

            String url = ip + complementoUrl; //Signo ? (?)


            if (!matricula.isEmpty() && !email.isEmpty() && !pass.isEmpty()){



                progressDialog.startAnimation();

                final Context finalContext = context;


                requestString = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {


                        progressDialog.revertAnimation();

                        Log.i("Info","= " + response);



                        if (response.trim().equalsIgnoreCase(allsuccesful)){

                            Intent i = new Intent(finalContext, SplashScreen.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Utils.setDataPreferences(preferences,email,pass,position);
                            finalContext.startActivity(i);



                        }else if (response.trim().equalsIgnoreCase(noSuccesful)){


                            snackBarCustomSign(layout,colornegative,"Hubo un error, por favor intente de nuevo",txtbig,null);

                            //Esto no lo puedo provocar


                        }else if (response.trim().equalsIgnoreCase(accountBusy)){


                            snackBarCustomSign(layout, colornegative, "Ingrese otro número asignado (Ese número ya esta registrado)", txtlitle, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    AlertDialog(builder,mensajePHP);

                                }
                            });



                        }else if (response.trim().equalsIgnoreCase(emailInUse)){

                            snackBarCustomSign(layout, colornegative, "El correo ingresado ya esta registrado", txtbig, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String message = "Ya hay otra cuenta registrada con el mismo correo. Por favor ingrese otro correo";

                                    AlertDialog(builder,message);

                                }
                            });




                        }else if (response.trim().equalsIgnoreCase(noexitsMatricula)){



                            snackBarCustomSign(layout, colornegative, messagePerosnal, txtbig, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    AlertDialog(builder,messageAlert);


                                }
                            });

                        }else {

                            snackBarCustomSign(layout, colornegative, "Error desconocido, por favor informe sobre este error!!", txtlitle, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {



                                    String message = "Por favor informe sobre este error, para una mejor experiencia de la app " + response + " Gracias...";

                                    AlertDialogImportant(builder,message);

                                    //Crear tabla para los errores etc //Me parece que este alert dialog debe ser diferente a los demas

                                }
                            }); //Poner uno con accio


                        } //Podria hacer mas else if para que no solo dependa de esos mensajes


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Aqui solo entraria en caso de que hubiera un problema de conexiocn con php o el servidor

                        Log.i("Error Volley","= " + error);
                        progressDialog.revertAnimation();
                        snackBarCustomSign(layout,colornegative,"No se pudo establecer conexión con el servidor",txtbig,null);

                    }

                }){

                    //POr ser metodo POST se usa params
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        Map<String, String> parametros = new HashMap<>();

                        parametros.put(c1,matricula); //Derecha los valores
                        parametros.put(c2,email);
                        parametros.put(c3,pass);
                        parametros.put(c4table,valuec4);
                        parametros.put(c5where,valuec5);
                        //Izquierda (k) = Esos van a ser los nombres por los cual el metodo post buscara
                        //Nombre de las varibales en las que revisara del archivo php

                        return parametros;

                    }
                };

                requestString.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton.getInstance(finalContext).addToRequestQueue(requestString);

            }else {

                Toast.makeText(context,"Completa todos los datos" , Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

            progressDialog.revertAnimation();

            snackBarCustomSign(layout,colornegative,"Comprueba tu conexiónn a Internet",txtbig,null);
        }


    }



    public static void checkInfoStudentGET (Context context, final String profiletoSearch, final RecyclerView recyclerView,
                                            final ArrayList<Posts> minds, final ProgressBar progressBar) {

        //Esto lo usan los teachers y los estudiantes
        //Tal vez pueda usar este mismo metodo para los padres

        JsonObjectRequest jsonObjectRequest;


        context = context.getApplicationContext();

        try {

            progressBar.setVisibility(View.VISIBLE);

            final String ip = context.getString(R.string.ip);


            String url = ip + "/AcitivitiesMain/IntoAppStudent/searchingAllPosts.php?profile=" + profiletoSearch ; //Esta vez no necesita signo ya q va a regresar todos los valores

            final Context finalContext = context;

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    progressBar.setVisibility(View.INVISIBLE);

                    Log.d("ResponsePots " ,"= " + response);
                    Posts posts = null;
                    JSONArray jsonArray = response.optJSONArray(profiletoSearch);

                    for (int i = 0; i <jsonArray.length(); i++){

                        posts = new Posts();
                        JSONObject json = null;

                        try {
                            json = jsonArray.getJSONObject(i);

                            posts.setTitle(json.optString("title"));
                            posts.setDescription(json.optString("description"));
                            posts.setUrl(json.optString("url"));
                            posts.setNameDirectivo(json.optString("namedirectivo"));
                            posts.setPosition(json.optString("position"));
                            posts.setUrlDirectivo(json.optString("urldirectivo"));
                            //Add two
                            posts.setProfile(json.optString("profile"));
                            posts.setDatepots(json.optString("date"));

                            minds.add(posts);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        final AdapterNotificationsStudent adapterNotificationsStudent = new AdapterNotificationsStudent(minds);
                        adapterNotificationsStudent.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onItemRangeInserted(int positionStart, int itemCount) {
                                super.onItemRangeInserted(positionStart, itemCount);

                                recyclerView.scrollToPosition(adapterNotificationsStudent.getItemCount() -1);

                            }
                        });

                        recyclerView.setAdapter(adapterNotificationsStudent);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Utils.GotoInfoAboutStateApp(finalContext,3);//Code 3 more use

                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 6,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);

        }catch (Exception e){

            progressBar.setVisibility(View.INVISIBLE);
            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }

    public static void checkPostForParents (Context context, final RecyclerView recyclerView,
                                            final ArrayList<Posts> minds, final ProgressBar progressBar,SharedPreferences preferences) {

        //Si el direct pide para cada grupo (Estudiante) podria usar este mismo y enviar el email y el grupo y hacer todo desde php

        JsonObjectRequest jsonObjectRequest;


        context = context.getApplicationContext();

        try {

            progressBar.setVisibility(View.VISIBLE);

            final String ip = context.getString(R.string.ip);

             final String email = Utils.getSharedPreferencesData(preferences,"email");

            String url = ip + "/AcitivitiesMain/IntoAppParent/getPotsForParents.php?email=" + email ; //Esta vez no necesita signo ya q va a regresar todos los valores

            final Context finalContext = context;

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    progressBar.setVisibility(View.INVISIBLE);

                    Log.d("ResponsePots " ,"= " + response);
                    Posts posts = null;
                    JSONArray jsonArray = response.optJSONArray("parentOnly");

                    for (int i = 0; i <jsonArray.length(); i++){

                        posts = new Posts();
                        JSONObject json = null;

                        try {
                            json = jsonArray.getJSONObject(i);

                            posts.setTitle(json.optString("title"));
                            posts.setDescription(json.optString("description"));
                            posts.setUrl(json.optString("url"));
                            posts.setNameDirectivo(json.optString("namedirectivo"));
                            posts.setPosition(json.optString("position"));
                            posts.setUrlDirectivo(json.optString("urldirectivo"));
                            //Add two
                            posts.setProfile(json.optString("profile"));
                            posts.setDatepots(json.optString("date"));

                            minds.add(posts);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        final AdapterNotificationsStudent adapterNotificationsStudent = new AdapterNotificationsStudent(minds);
                        adapterNotificationsStudent.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onItemRangeInserted(int positionStart, int itemCount) {
                                super.onItemRangeInserted(positionStart, itemCount);

                                recyclerView.scrollToPosition(adapterNotificationsStudent.getItemCount() -1);

                            }
                        });

                        recyclerView.setAdapter(adapterNotificationsStudent);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Utils.GotoInfoAboutStateApp(finalContext,3);//Code 3 more use

                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 6,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);

        }catch (Exception e){

            progressBar.setVisibility(View.INVISIBLE);
            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }


}
