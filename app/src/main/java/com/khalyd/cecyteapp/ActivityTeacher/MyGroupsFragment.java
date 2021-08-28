package com.khalyd.cecyteapp.ActivityTeacher;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupsFragment extends Fragment {

    private TextView txtInfo;

    public MyGroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my_groups, container, false);
        UI(v);
        //TODO ESTO SERA ELIMINADO

        String message = "En cuantó a su perfíl (Docente) esperamos que desde esta pestaña (Mis grupos) pueda crear grupos por medio de códigos hechos por usted" +
                " de tal manera que los comparta con sus alumnos y ellos desde su perfíl se puedan unir dando como resultado: compartir archivos,videos " +
                "o algún otro material educativo. También se pretende agregar una pestaña en la cual se puedan agregar las calificaciones de sus alumnos (Esto último depende del directivo)" +
                " Esperamos contar con su apoyo para que la app siga en desarrollo.";

        setAlerDialog(txtInfo,getContext(),message);
        return v;

    }

    private void UI(View v){

        txtInfo = v.findViewById(R.id.touchHerMoreInfoTeachers);

    }

    public static void setAlerDialog(TextView txt, final Context context,final String message){

        //Este metdodo sera publico porq se ocupara en diferentes pantallas pero sera eliminado en futuras actualizaciones


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = "¿Qué se espera para futuras actualizaciones?";

                String btnPositive  = "ENTENDIDO";

                DesignForAppMethods.AlertDialogCustom(new AlertDialog.Builder(context), title, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                },btnPositive);


            }
        });



    }

}
