package com.khalyd.cecyteapp.ActivityDirectivo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Activities.NoInternetConnetivity;
import com.khalyd.cecyteapp.Adapters.AdapterNotificationsStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.VolleySingleton;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.Models.Students;
import com.khalyd.cecyteapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsDirectivo extends Fragment {

    private EditText editText;
    private Button btn;
    private TextView name,semestre,LastName;
    private CircleImageView circleImageView;


    public StudentsDirectivo() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_students_directivo, container, false);
        UI(v);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String matricula = editText.getText().toString();

                if (!matricula.isEmpty()){

                    if (matricula.length() == 14) {

                        checkInfoStudentGET(getContext(), circleImageView, name, semestre, LastName, matricula);

                    }else {

                        editText.setError("Debe de contener 14 digitos la matricula");

                    }

                }else {

                    Toasty.error(getContext(),"Debe haber una matricula",Toast.LENGTH_LONG).show();

                }


            }
        });

        return v;

    }

    private void UI(View v){

        editText = v.findViewById(R.id.txtxMatriculaD);
        btn = v.findViewById(R.id.getData);
        name = v.findViewById(R.id.txtNameStudentD);
        semestre = v.findViewById(R.id.txtSemestreStudent);
        LastName = v.findViewById(R.id.txtLastNameStudent);
        circleImageView = v.findViewById(R.id.circleImageViewStudent);


    }

    public static void checkInfoStudentGET (final Context context, final CircleImageView imgCircle, final TextView txtNameUser,
                                            final TextView txtSemestre,final TextView lastName,String matricula) {



        JsonObjectRequest jsonObjectRequest;

        //Obtiene los datos del studiante desde el perfil del directivo


        try {

            final String ip = context.getString(R.string.ip);


            String url = ip + "/AcitivitiesMain/IntoAppDirectivo/searchStudent.php?matricula=" + matricula ;

            final Context finalContext = context;

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
                        student.setLastName(jsonObject.optString("lastname"));
                        student.setSemestre(jsonObject.optString("groupStudent")); //Semestre se cambio a grupo
                        student.setUrl(jsonObject.optString("url"));


                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                    if (student.getUrl().equals("")){

                        String url = context.getString(R.string.img_noImageProfile);
                        Glide.with(context).load(url).into(imgCircle);

                    }else {

                        Glide.with(finalContext).load(ip + "/AcitivitiesMain/AllImages/" + student.getUrl()).into(imgCircle);

                    }

                        txtNameUser.setText(student.getName());
                        lastName.setText(student.getLastName());
                        txtSemestre.setText(student.getSemestre());











                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Set messages error

                    Log.d("errorVolley " ,"= " + error);

                    int serverOnMaintinience  = 3;
                    String  keyForServers = "serverNotWorking";
                    Utils.setContentViewForApp(finalContext,serverOnMaintinience,keyForServers,null);

                }
            });


            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 3,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);


        }catch (Exception e){

            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }
    }




}
