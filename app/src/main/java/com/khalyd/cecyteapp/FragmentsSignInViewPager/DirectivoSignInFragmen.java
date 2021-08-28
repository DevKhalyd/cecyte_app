package com.khalyd.cecyteapp.FragmentsSignInViewPager;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.khalyd.cecyteapp.Activities.FirtsActivtiChooseTwo;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.BlankFragmentEmptyuse;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.FragmentDirectivo;
import com.khalyd.cecyteapp.FragmentsDocenteSignIn.FragmentDocente;
import com.khalyd.cecyteapp.R;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectivoSignInFragmen extends Fragment {


    TextView txtStar;
    ImageView viewImgMain;


    public DirectivoSignInFragmen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_login_teacher, container, false);
        // Inflate the layout for this fragment
        UI(v);
        all(v);
        return v;
    }


    private void UI(View v){
        txtStar = v.findViewById(R.id.textStart);
        viewImgMain = v.findViewById(R.id.imageView3);

    }

    private void all(View v) {
        // goToMain();
        goToBck(v);
        setSpinner(v);
        ImageView view = v.findViewById(R.id.loadingViewTeacher);
        String img = getString(R.string.SecondImage); //Para los logins y registros
        Utils.getImageFromUrl(img,getContext(),view);

    }



    private void goToBck(View v) {
        ImageView imageView =  v.findViewById(R.id.backToSIGN);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FirtsActivtiChooseTwo.class);
                startActivity(intent);
            }
        });

    }

    private void setSpinner(final View v){

        final Spinner spinner =  v.findViewById(R.id.spinner);

        ArrayList<String> profilesAvaibles = new ArrayList<>();
        profilesAvaibles.add("Ninguno");
        profilesAvaibles.add("Docente");
        profilesAvaibles.add("Directivo");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_model,profilesAvaibles);
        spinner.setAdapter(spinnerAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                    FragmentManager fragmentManager = getFragmentManager(); //getSupportFragmentManager() Estaba asi antes
                    //Supongo q ahora solo obtenfo el manager ya q esto esta dentro de un view pager
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    switch (position){

                        case 0:
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                            fragmentTransaction.replace(R.id.containerDocente,new BlankFragmentEmptyuse()).commit();
                            txtStar.setVisibility(View.VISIBLE);
                            viewImgMain.setBackgroundResource(R.drawable.ic_whoareyou);
                            break;
                        case 1:
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.replace(R.id.containerDocente,new FragmentDocente()).commit();
                            txtStar.setVisibility(View.INVISIBLE);
                            viewImgMain.setBackgroundResource(R.mipmap.ic_teacher);
                            break;
                        case 2:
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.replace(R.id.containerDocente,new FragmentDirectivo()).commit();
                            txtStar.setVisibility(View.INVISIBLE);
                            viewImgMain.setBackgroundResource(R.mipmap.ic_teacher);
                            break;
                    }

                    //Tengo q rehacer o volver a a rehacer la manera en q acceden los estudiantes para que asi haya un control

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });
    }
}
