package com.khalyd.cecyteapp.ActivityDirectivo;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.khalyd.cecyteapp.Adapters.AdapterDirectivo;
import com.khalyd.cecyteapp.Adapters.AdapterNotificationsStudent;
import com.khalyd.cecyteapp.Class.ComplemetsUrlandConstansFinal;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Class.VolleySingleton;
import com.khalyd.cecyteapp.Class.WebServices;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class PotsDirectivo extends Fragment {

    private ArrayList<Posts> posts;

    private FloatingActionsMenu menuFab;
    private FloatingActionButton fabEstudiante,fabDocente,fabWTFTHIS,fabParent,fabAllProfiles;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    public PotsDirectivo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pots_directivo, container, false);
        UI(v);
        setrecycler();
        checkInfoStudentGET(getContext(),recyclerView,posts,progressBar);

        return v;

    }
    private void UI(View v){

        posts = new ArrayList<>();
        menuFab = v.findViewById(R.id.fabMenuDirectivo);
        fabEstudiante = v. findViewById(R.id.fabEstudiante);
        fabDocente = v. findViewById(R.id.fabDocente);
        fabWTFTHIS= v. findViewById(R.id.fab_whatsisthis);
        fabParent = v. findViewById(R.id.fabParent);
        recyclerView = v.findViewById(R.id.recyclerDiectivo);
        progressBar = v.findViewById(R.id.progressBarDirectivo);
        fabAllProfiles = v.findViewById(R.id.fabAllProfilesPots);
        setMenu();

    }

    private void setrecycler (){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
        recyclerView.setPreserveFocusAfterLayout(true);
        recyclerView.setHasFixedSize(true);

    }

    public static void checkInfoStudentGET (Context context, final RecyclerView recyclerView,
                                            final ArrayList<Posts> minds, final ProgressBar progressBar) {

        //Le pondre el valor true si actualiza y busque false si no ha actualizado

        JsonObjectRequest jsonObjectRequest;

        //C1 C2 C3, son los campos que se les pasara para que sean encontrados por el php
        context = context.getApplicationContext();

        try {

            progressBar.setVisibility(View.VISIBLE);

            final String ip = context.getString(R.string.ip);


            String url = ip + "/AcitivitiesMain/IntoAppDirectivo/searchingAllPostsDirectivo.php" ; //Esta vez no necesita signo ya q va a regresar todos los valores

            final Context finalContext = context;

            //Va a buscar por email

            //Al parecer listener es null
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    progressBar.setVisibility(View.INVISIBLE);


                    Log.d("ResponsePots " ,"= " + response);
                        //Lo quite porq puede ser q se detengan algunos telefonos pero he aqui la solucion

                    Posts posts = null;

                    JSONArray jsonArray = response.optJSONArray("poststudents");



                    Log.i("Lengh"," = " + jsonArray); //Checar y tal vez aqui meta el if pero en el response

                    /*

                    JSONObject jsonTeds = null;
                    Posts postsTest = null;
                    posts.setTitle(jsonTeds.optString("title"));

                    if (!posts.getTitle().equals("NothingDatabase")){

                    //Como solucionar el problema de que no entra a los pots, Si no entra es q no hay nada en la base de datos

                    }*/

                    for (int i = 0; i < jsonArray.length(); i++){

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
                            posts.setProfile(json.optString("profile"));
                            posts.setDatepots(json.optString("date"));

                            minds.add(posts);

                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                        final AdapterDirectivo myAdapter = new AdapterDirectivo(minds);
                        myAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onItemRangeInserted(int positionStart, int itemCount) {
                                super.onItemRangeInserted(positionStart, itemCount);

                                recyclerView.scrollToPosition(myAdapter.getItemCount() -1);

                                //Si no mal me acuerdo esto era para mantener la vista pero si no mal recuerdo no sirve

                            }
                        });

                        recyclerView.setAdapter(myAdapter);
                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Set messages error
                    Log.i("ErroDirectivo"," = " + error);
                    progressBar.setVisibility(View.INVISIBLE);
                    Utils.GotoInfoAboutStateApp(finalContext,3);


                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(finalContext).addToRequestQueue(jsonObjectRequest);

        }catch (Exception e){

            progressBar.setVisibility(View.INVISIBLE);
            Toasty.error(context,"Comprueba tu conexión a internet"  , Toast.LENGTH_SHORT).show();
        }



    }

    private void setMenu(){


        fabWTFTHIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AlertDialog();
                    menuFab.collapse();


            }
        });

        fabEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String folder  = "potsImgsStudents";
                String toolbar = "Estudiante";

                setBundleInfo(folder,ComplemetsUrlandConstansFinal.code_Student,toolbar);


            }
        });

        fabDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IntentDocente


                String folder  = "postImgsTeachers";
                String toolbar = "Profesor";

                setBundleInfo(folder,ComplemetsUrlandConstansFinal.code_Teacher,toolbar);

            }
        });

        fabParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IntentParent

                String folder  = "potsImgsParents";
                String toolbar = "Tutor";

                setBundleInfo(folder,ComplemetsUrlandConstansFinal.code_ParentIdentifique,toolbar);

            }
        });

        fabAllProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String folder  = "potsImgsForAll";
                String toolbar = "Todos";

                setBundleInfo(folder, ComplemetsUrlandConstansFinal.code_All,toolbar);

            }
        });

    }

    private  void AlertDialog (){

        String messsage = "Las publicaciones se dividen en cuatro grupos:\n\n" +
                          "1. Estudiantes \n" +
                          "2. Padres de Familia (Tutores) \n" +
                          "3. Profesores \n" +
                          "4. Todos (Este tipo de publicación será mostrada en todos los perfiles) \n\n"+

                          "Dependiendo del tipo de publicación que elija, la publicación sólo se mostrara en el perfíl seleccionado";

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

        alertDialog.setTitle("¿Comó utilizar las publicaciones?").
                setMessage(messsage)
                .setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();


            }
        });
        AlertDialog dialog =  alertDialog.create();
        dialog.show();

    }

    private void setBundleInfo(String folder,String profile,String toolbar){

        Intent i = new Intent(getContext(),PotsAnyProfile.class);
        Bundle infoneeded = new Bundle();
        infoneeded.putString("folder",folder);
        infoneeded.putString("profile",profile);
        infoneeded.putString("toolbar",toolbar);
        i.putExtras(infoneeded);
        menuFab.collapse();
        startActivity(i);

        //Aqui se guardan los datos para los pots

    }



}
