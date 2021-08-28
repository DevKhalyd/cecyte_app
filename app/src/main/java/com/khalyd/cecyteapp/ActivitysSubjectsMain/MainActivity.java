package com.khalyd.cecyteapp.ActivitysSubjectsMain;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Class.DesignForAppMethods;
import com.khalyd.cecyteapp.Class.ServicesStudent;
import com.khalyd.cecyteapp.Class.Utils;
import com.khalyd.cecyteapp.Fragments.Calificaciones;
import com.khalyd.cecyteapp.Fragments.Schoolships;
import com.khalyd.cecyteapp.Fragments.Notifications;
import com.khalyd.cecyteapp.Fragments.SubjectsFragment;
import com.khalyd.cecyteapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView; //Es el que abre y cierra
    private SharedPreferences sharedPreferences;
    private CircleImageView imgUserf;
    private LayoutInflater inflater;

    //Todo Bueno se le movio los fragments de posicion 1/10/17 si surge algo hay q cambiar
    //A veces el metodo no esta mal sino en el lugar q se coloca

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UI();
        sharedPreferences = Utils.getMySharedPreferences(this);
        allItems();
    }

    private void setTextandImage(){


        View headerView = navigationView.getHeaderView(0); //Con esto consigo todo lo que haya adentro
        TextView txtNameStudent = headerView.findViewById(R.id.nameStudent);
        TextView txtSemestre = headerView.findViewById(R.id.semestre);
        ImageView imgBackGrounf = headerView.findViewById(R.id.GGIZI);
        Glide.with(this).load("https://wallpaperbrowse.com/media/images/NGIlEC.jpg").into(imgBackGrounf);
        imgUserf = headerView.findViewById(R.id.headProfile);
        imgUserf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlertDialog();
            }
        });
        ImageView img = headerView.findViewById(R.id.edit_profile_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,EditProfile.class);
                startActivity(i);

            }
        });

        TextView touchImage = headerView.findViewById(R.id.touch_here_and_hide);
        String url = "/AcitivitiesMain/IntoAppStudent/checkinInfoStudent.php?";
        ServicesStudent.checkInfoStudentGET(this,url,sharedPreferences,imgUserf,txtNameStudent,txtSemestre,img,touchImage);

    }

    private void setAlertDialog() {

        //Para escoger la foto de perfil del alumno

        final CharSequence[] options = {"Galería","Cancelar"};
        AlertDialog.Builder build =  new AlertDialog.Builder(this);
        build.setTitle("Escoge tu foto de perfil");
        build.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                if (options[position].equals("Galería")){



                    Utils.saveOnlyOneDateBundle(MainActivity.this,Utils.TABLE_STUDENTS,Utils.FOLDER_STUDENTS);


                }else {

                    dialogInterface.dismiss();

                }
            }
        });

        build.show();

    }

    private void allItems(){
        setToolbar();
        setTextandImage();
        navDra();
        fragmentDefalut();

    }

    private void UI (){

        drawerLayout =  findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.navView);

    }

    private void navDra(){


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                boolean fragTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()){

                    case R.id.News:
                        fragment = new Notifications();
                        fragTransaction = true;
                        break;
                    case R.id.Materias:
                        fragment = new SubjectsFragment();
                        fragTransaction = true;
                        break;
                    case R.id.Calificaciones:
                        fragment = new Calificaciones();
                        fragTransaction = true;
                        break;
                    case R.id.Becas:
                        fragment = new Schoolships();
                        fragTransaction = true;
                        break;
                    case R.id.Web:
                        //Aqui va ir un alertDialog donde indique la pag is Building
                        String ms = "La página web del Cecyte esta siendo construida.\n\n" +
                                "Al igual que la app, la página web tendra las mismas funciones.";
                        DesignForAppMethods.AlertDialogInfo(new AlertDialog.Builder(MainActivity.this),ms);
                        break;
                    case R.id.SendComments:
                        openAlertDialog();
                        break;

                }
                if (fragTransaction){
                    changeFragment(fragment,item);
                    drawerLayout.closeDrawers();
                }


                return true;
            }
        });
    }

    private void openAlertDialog() {

        inflater = this.getLayoutInflater();
        View content = inflater.inflate(R.layout.alertdialog_send_comments_,null);
        final EditText title = content.findViewById(R.id.title_txt_alert);
        final EditText descrition = content.findViewById(R.id.description_alertDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(content)
                .setTitle("Envia tus recomendaciones")
                .setMessage("\nPuedes enviar ideas, fallos o algún otro asunto relacionado con la app")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String firts =  title.getText().toString();
                        String second = descrition.getText().toString();

                        if (!firts.isEmpty() && !second.isEmpty()){

                                if (!lenghtText(firts)) {


                                    String url ="/AcitivitiesMain/IntoAppStudent/sendComments.php?";

                                    ServicesStudent.sendComments(getApplicationContext(),url,sharedPreferences,firts,second);


                                }else {

                                    Toasty.error(getApplicationContext(),"El asunto es muy largo...",Toast.LENGTH_LONG).show();

                                }

                        }else{

                            Toasty.error(getApplicationContext(),"Deben de estar llenos todos los campos",Toast.LENGTH_LONG).show();

                        }

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                   @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                     dialogInterface.dismiss();
                   }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public boolean lenghtText (String title){


        return title.length() > 30;

    }

    private void changeFragment(Fragment fragment, MenuItem item){

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());


    }

    private void fragmentDefalut(){
        //Este metodo sirve para abrir un fragment por default
        changeFragment(new Notifications(),navigationView.getMenu().getItem(0));

    }

    public void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menuss);//Icono de la hamburgesa(Son las tres lineas)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //orden para habilitar un icono en la toolbar, en este caso se le puso el icono de la hamburguesa
        //Metodo para poner la toolbar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Aqui solo se infla el menu en si inflar es hacer q lo q inflamos sea algo q ya existe y se pueda manejar en la UI
        getMenuInflater().inflate(R.menu.menulog, menu);
        //Se le debe pasar el menu
        return super.onCreateOptionsMenu(menu);
    }
//El siguiente metodo es importante ya q nos ayudara a darles una utilidad a las opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Recordad q siempre hay q pasarle el 2do valor q aparezca dentro (del metodo)de los
        // parentesis no se como se llama y el punto q le sigue es la instruccion
        switch (item.getItemId()){
            case R.id.LogOut:
                Utils.logoutAnyProfile(sharedPreferences,this);
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

               // setBackgroundStart(); Esto ponia las iamgenes

                return true;


        }
        return super.onOptionsItemSelected(item);
    }
    //Un metodo es importante ya q puedo tener un codigo mas limpio y asi no tener q escribir la instrucion una y otra vez
    //Es importante q me memorize esto
    @Override
    public void onBackPressed() { // Ese metodo regresa hacia atras

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {//Si hay algo q debe ser con el BackPressed se hace eso en vez de super.onBackPressed
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); //Este ejecuta ese metodo hacia atras
        }
    }

}

