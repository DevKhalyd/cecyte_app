package com.khalyd.cecyteapp.Class;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Models.StudentsForParents;
import com.khalyd.cecyteapp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class ServicesParent {



    public static void checkInfoStudentForParentProfile(Context context, String complementoUrl, final SharedPreferences preferences,
                                            final CircleImageView imgCircle, final TextView txtNameUser, final TextView txtLastName,
                                            final TextView group) {

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

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d("responseServicesParent " ,"= " + response);

                    StudentsForParents student = new StudentsForParents();
                    JSONArray jsonArray = response.optJSONArray("students"); //Obten el JSONArray called
                    JSONObject jsonObject = null;

                    try {

                        jsonObject = jsonArray.getJSONObject(0); //Supongo q 0 porq pueden haber mas
                        student.setName(jsonObject.optString("nombre"));
                        student.setLastName(jsonObject.optString("lastname"));
                        student.setGroup(jsonObject.optString("groupStudent"));
                        student.setUrl(jsonObject.optString("url"));


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                    //semestre es igual a group

                    if (student.getName().equals("") && student.getGroup().equals("0")) {

                        txtNameUser.setText("El alumno no ha");
                        txtLastName.setText("actualizado sus datos");
                        group.setText("");
                        //Aqui le voy a pasar un boton para habitarlo e inabilitarlo dependiendo del caso
                        if (student.getUrl().equals("")) {

                            String img = finalContext.getString(R.string.img_noImageProfile);
                            Glide.with(finalContext).load(img).into(imgCircle);

                        } else {


                            Glide.with(finalContext).load(ip + "/AcitivitiesMain/AllImages/" + student.getUrl()).into(imgCircle);

                        }

                    } else {

                        //Aqui se supone q ya deberia de trer todos los datos

                        if (student.getName().length() < 20 && student.getLastName().length() < 20){

                            txtNameUser.setText(student.getName());
                            txtLastName.setText(student.getLastName());
                            group.setText(student.getGroup());

                            if (!student.getUrl().equals("")) {

                                Glide.with(finalContext).load(ip + "/AcitivitiesMain/AllImages/" + student.getUrl()).into(imgCircle);

                            } else {

                                String img = finalContext.getString(R.string.img_noImageProfile);

                                Glide.with(finalContext).load(img).into(imgCircle);

                            }


                        }else {


                            txtLastName.setTextSize(10);
                            txtNameUser.setTextSize(10);
                            group.setTextSize(10);
                            txtNameUser.setText(student.getName());
                            txtLastName.setText(student.getLastName());
                            group.setText(student.getGroup());

                            if (!student.getUrl().equals("")) {

                                Glide.with(finalContext).load(ip + "/AcitivitiesMain/AllImages/" + student.getUrl()).into(imgCircle);

                            } else {

                                String img = finalContext.getString(R.string.img_noImageProfile);

                                Glide.with(finalContext).load(img).into(imgCircle);

                            }



                        }

                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Set messages error

                    Log.d("errorVolley " ,"= " + error);

                    Utils.GotoInfoAboutStateApp(finalContext,3);

                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);

        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();

        }

    }
}
