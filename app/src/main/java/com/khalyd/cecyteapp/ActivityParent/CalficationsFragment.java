package com.khalyd.cecyteapp.ActivityParent;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khalyd.cecyteapp.Class.ComplemetsUrlandConstansFinal;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesParent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class CalficationsFragment extends Fragment {

    private TextView name,surname,group;
    private CircleImageView circleImageSon;
    private SharedPreferences preferences;

    public CalficationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      preferences = Utils.getMySharedPreferences(getContext());
      View view  = inflater.inflate(R.layout.fragment_calfications, container, false);

        // Inflate the layout for this fragment
        UI(view);

        ServicesParent.checkInfoStudentForParentProfile(getContext(), ComplemetsUrlandConstansFinal.getInfoStudentForParentProfile,preferences,circleImageSon,name,surname,group);
        TextView txt = view.findViewById(R.id.douwantknowmore);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "¿Qué se espera para futuras actualizaciones?";
                String message = "En cuantó a su perfíl (Tutor) esperamos que en la pestaña \"Alumno\" aparezcan las calificaciones del " +
                        "alumno (Esto último dependería del directivo) Además agregar una nueva pestaña donde indique el desempeño del alumno. " +
                        "Esperamos contar con su apoyo para que la app siga en desarrollo.";

                String btnPositive  = "ENTENDIDO";

                DesignForAppMethods.AlertDialogCustom(new AlertDialog.Builder(getContext()), title, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                },btnPositive);
            }
        });
        return view;
    }

    //Alumno en el bottomNavigation y Calificaiones en

    private void UI(View v){

        name = v.findViewById(R.id.txtNameSoonForParent);
        surname = v.findViewById(R.id.txtSurNameSoonForParent);
        group = v.findViewById(R.id.txtGroupSoonForParent);
        circleImageSon = v.findViewById(R.id.circleImageSonParent);


    }

}
