package com.khalyd.cecyteapp.Class;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.khalyd.cecyteapp.Models.ForOthers;
import com.khalyd.cecyteapp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ServicesDocente {


    public static void updateProfileAllExeptioStudent (Context context, final String name, final String lastname, final String table,
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
                    parametros.put(Utils.SEARCH_EMAIL,email); //No se guardo el correo en los intents
                    parametros.put(Utils.SEARCH_NAME,name);
                    parametros.put(Utils.SEARCH_LASTANAME,lastname);
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


    public static void checkInfoAllforGET (final Context context, String complementoUrl, final SharedPreferences preferences,
                                           final CircleImageView imgCircle, final TextView txtNameUser,
                                           final TextView txtLastName, final String table, final TextView txtview) {

        //Le pondre el valor true si actualiza y busque false si no ha actualizado

        JsonObjectRequest jsonObjectRequest;

        //Me parece que este metodo checa la info del parent y del teacher

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php


        try {



            final String ip = context.getString(R.string.ip);



            String email =  Utils.getSharedPreferencesData(preferences,Utils.SEARCH_EMAIL);

            String url = ip + complementoUrl + "email" + "=" + email + "&" + "table" + "=" + table; //Signo ? (?)

            //Va a buscar por email


            //Al parecer listener es null
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    Log.d("response " ,"= " + response);
                    ForOthers other = new ForOthers();

                    JSONArray jsonArray = response.optJSONArray(table); //Obten el JSONArray called
                    JSONObject jsonObject = null;
                    //Aqui me va a regresar tres cosas

                    try {

                        jsonObject = jsonArray.getJSONObject(0); //Supongo q 0 porq pueden haber mas
                        other.setName(jsonObject.optString("name"));
                        other.setLastname(jsonObject.optString("lastname"));
                        other.setUrl(jsonObject.optString("url"));


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }




                    if (other.getName().equals("") && other.getLastname().equals("")) {

                        txtNameUser.setText("Actualiza tus datos");
                        txtLastName.setText("Toca el botón flotante");

                        if (other.getUrl().equals("")) {

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


                        if (!other.getUrl().equals("")) {

                            Glide.with(context).load(ip + "/AcitivitiesMain/AllImages/" + other.getUrl()).into(imgCircle);

                            txtview.setVisibility(View.INVISIBLE);

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

                    Log.d("errorVolley " ,"= " + error);
                    Intent i =  new Intent(context, NoInternetConnetivity.class);
                    Bundle info =  new Bundle();
                    info.putInt("serverNotWorking",3);
                    i.putExtras(info);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                }
            });



            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

        }
    }



}
