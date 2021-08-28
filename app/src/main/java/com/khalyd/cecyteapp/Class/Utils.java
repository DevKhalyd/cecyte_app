package com.khalyd.cecyteapp.Class;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Activities.EditProfilePictureAll;
import com.khalyd.cecyteapp.Activities.NoInternetConnetivity;
import com.khalyd.cecyteapp.Activities.SplashScreen;
import com.khalyd.cecyteapp.ActivitysSubjectsMain.EditProfile;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.R;

import java.util.ArrayList;


public class Utils {

    //Estoy lo voy a usar constanemente asi q mejor se pone en una clase para no estar haciendo el metodo uno por uno
    //en cada clase

    //Al momento de ser boolean pide q regreso algo eh ahi el porq del return

    public static String errorMail = "*Introduce un correo válido";
    public static String errorEnrollmentNumber = "*Introduce los 14 dígitos de tu credencial";
    public static String errorPasswordNoSafe = "*Introduce una contraseña más segura (1,a,J)";
    public static String errorPassworNoEquals = "*Deben de coincidir las contraseñas";
    public static String errrTooLoongPass = "*La contraseña es demasiada larga";
    private static String passtooShort = "*La contraseña es muy corta";
    public static final String SEARCH_EMAIL = "email";
    public static final String BITMAP_ENCODE = "img";
    public static final String GO_FOLDER = "folder";
    public static final String SEARCH_NAME = "name";
    public static final String SEARCH_LASTANAME = "lastname";
    public static final String SEARCH_GROUP= "group";
    public static final String SEARCH_TABLE = "table";
    public static final String SEARCH_CARGO = "position"; //cargo del directivo
    public static final String SEARCH_TITLE = "title";
    public static final String SEARCH_DESCRIPTION= "description";
    public static final String SEARCH_FOLDER = "folder";
    public static final String SEARCH_PROFILE = "profile";
    public static final String VERSION_CODE = "versionInstalled";
    private static final String APP_NAME_FOR_SHARED_PREFERENCES = "CredentialsApp";
    public static final String TABLE_DIRECTIVO = "directivo";
    public static final String TABLE_PARENT = "parents";
    public static final String TABLE_STUDENTS = "students";
    public static final String TABLE_TEACHER = "teacher";
    public static final String FOLDER_STUDENTS = "imagenesStudents";//Asi se reconoceran las carpetas donde se guarden
    public static final String FOLDER_PARENTS = "imgsParents";
    public static final String FOLDER_TEACHERS = "imgsTeachers";//Igual podria guardar todas las imagenes en una carpeta, solo debo de ponerle el nombre con los milisegundos
    public static final String FOLDER_DIRECTIVO = "imgsDirectivo";//Aunque creo que asi estaria bien para una mejor organizacion de los datos
    public static int DIRECTIVO = 1;
    public static int DOCENTE = 2; //Para saber cual va a abrir
    public static final String GROUP_STUDENT= "groupStudentProfile";
    //Solo le mandaba el valor por bundle lo recuperaba con un try catach para q no se detuviera la app de ahi dependiendo
    //del q me llegara le daba abrir el fragment q tuviera q abrir

    public static boolean maxNumberStudent(String numberStudent) {

        return numberStudent.length() == 14;

    }

    public static boolean maxNumberPersonal (String numberStudent,int numbers) {

        return numberStudent.length() == numbers;

    }

    public static boolean validationEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches(); //Comprobacion de email
        //Seria para pasar el filtro no para ver si esta vacio (leer)
    }


    public static boolean validationPassword(String pass) {
        return pass.length() > 4; //Numero de caracteres maximos

    }

    public static boolean passwordSafe(String passSafe) {
        //Poner un limite de contraseña

        return passSafe.length() >= 7; //Numero de caracteres maximos

    }

    public static boolean passwordTooLong(String passSafe) {
        //Poner un limite de contraseña

        return passSafe.length() < 15; //Numero de caracteres maximos

    }

    //Se necesitan los strings para que sea verificado y el textinput para poner el correspondiente error
    public static boolean emailandPassValidation(String email, TextInputLayout txtMail, String password, TextInputLayout txtpass){

        if (!validationEmail(email)){

            txtMail.setError(Utils.errorMail);

        }else {

            txtMail.setErrorEnabled(false);

            if (!validationPassword(password)){

                txtpass.setError(passtooShort);

            }else {

                txtpass.setErrorEnabled(false);

                return true;

            }

        }

        return false;

    }

    //Este metodo afectara a los tres perfiles de registrMethod is working y ademas me costo hacerlo
    public static boolean checkIfSpacesIsCorrectSignUp(String numberCard, TextInputLayout Inputnumbercard,String tutor, String email,
                                                       TextInputLayout inputemail, String pass, TextInputLayout inputpass,
                                                       String passconfirm, TextInputLayout inputpassConfirm) {

        //Que pasa si usp demasicaod if else?
        //Sera porq es un metodo void no necesitan los else?
        //Test

        if (!maxNumberStudent(numberCard)) {  //Wata?

            if (tutor == null){

                Inputnumbercard.setError(Utils.errorEnrollmentNumber);

            }else {

                Inputnumbercard.setError(tutor);
            }

        } else if (maxNumberStudent(numberCard)) {

            Inputnumbercard.setErrorEnabled(false);

            if (!validationEmail(email)) {

                inputemail.setError(Utils.errorMail);

            } else if (validationEmail(email)) {

                inputemail.setErrorEnabled(false);

                if (!passwordSafe(pass)) {

                    inputpass.setError(Utils.errorPasswordNoSafe);


                } else if (passwordSafe(pass)) {

                    inputpass.setErrorEnabled(false);

                    if (!passwordTooLong(pass)) {

                        inputpass.setError(Utils.errrTooLoongPass);

                        //return  passSafe.length() < 15;

                    } else if (passwordTooLong(pass)) {//Error es este

                        inputpass.setErrorEnabled(false);

                        if (!pass.equals(passconfirm)) {

                            inputpassConfirm.setError(Utils.errorPassworNoEquals);

                        } else {

                            //Ctrl + Q  = More Info

                           /* context = context.getApplicationContext();

                            Toasty.success(context, Utils.registersuccess, Toast.LENGTH_SHORT).show();
                            inputpassConfirm.setErrorEnabled(false);*/

                            //MAYBE this will be change cause each one will has a different table for result
                            return true;


                        }
                    }
                }
            }
        }
        //Error arreglado
            return false;
    } //Final del metodo
    //Checar porq hice otro metedo para los directivos

    public static boolean checkIfSpacesIsCorrectSignUpPersonal(String numberCard, TextInputLayout Inputnumbercard, String email,
                                                       TextInputLayout inputemail, String pass, TextInputLayout inputpass,
                                                       String passconfirm, TextInputLayout inputpassConfirm,String docenteordirectivo,int numbersneeds,
                                                        View layout,int colors) {

    //A partir de la linea de arriba esta pidiendo los parametros del snackbar
        int sizeLetter = 15;

        //Este checa los perfiles del docente y del directivo


        if (!maxNumberPersonal(numberCard,numbersneeds)) {

            Inputnumbercard.setError(docenteordirectivo);
            WebServices.snackBarCustomSignPersonal(layout,colors,docenteordirectivo,sizeLetter);


        } else if (maxNumberPersonal(numberCard,numbersneeds)) {

            Inputnumbercard.setErrorEnabled(false);

            if (!validationEmail(email)) {


                inputemail.setError(Utils.errorMail);
                WebServices.snackBarCustomSignPersonal(layout,colors,Utils.errorMail,sizeLetter);


            } else if (validationEmail(email)) {

                inputemail.setErrorEnabled(false);

                if (!passwordSafe(pass)) {

                    inputpass.setError(Utils.errorPasswordNoSafe);
                    WebServices.snackBarCustomSignPersonal(layout,colors,Utils.errorPasswordNoSafe,sizeLetter);


                } else if (passwordSafe(pass)) {

                    inputpass.setErrorEnabled(false);

                    if (!passwordTooLong(pass)) {

                        inputpass.setError(Utils.errrTooLoongPass);
                        WebServices.snackBarCustomSignPersonal(layout,colors,Utils.errrTooLoongPass,sizeLetter);

                        //return  passSafe.length() < 15;

                    } else if (passwordTooLong(pass)) {//Error es este

                        inputpass.setErrorEnabled(false);

                        if (!pass.equals(passconfirm)) {

                            inputpassConfirm.setError(Utils.errorPassworNoEquals);
                            WebServices.snackBarCustomSignPersonal(layout,colors,Utils.errorPassworNoEquals,sizeLetter);

                        } else {

                            //Ctrl + Q  = More Info

                           /* context = context.getApplicationContext();

                            Toasty.success(context, Utils.registersuccess, Toast.LENGTH_SHORT).show();
                            inputpassConfirm.setErrorEnabled(false);*/

                            //MAYBE this will be change cause each one will has a different table for result
                            return true;


                        }
                    }
                }
            }
        }
        //Error arreglado
        return false;
    }

    public static void setDataPreferences (SharedPreferences preferences,String email,String password, String position){ //Puedo poner el id del que ingreso

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email",email);
        editor.putString("pass",password);
        editor.putString("position",position); //Estudiante,Tutor,Docente,Directivo
        editor.apply(); //No voy a meter booleans
        // if (editor.commit()) //Asi se usa el commit devuelve booleano

    }

    public static void setDataPreferencesShowToGroup (SharedPreferences preferences,String email,String password, String position,int starYear){ //Puedo poner el id del que ingreso

        //Este metodo es diferente ya que indica de que grupo viene esa matricula

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email",email);
        editor.putString("pass",password);
        editor.putString("position",position); //Estudiante,Tutor,Docente,Directivo
        editor.putInt("Year",starYear);
        editor.apply(); //No voy a meter booleans
        // if (editor.commit()) //Asi se usa el commit devuelve booleano

    }


    public static void setGroupStudent (SharedPreferences preferences,int group){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(GROUP_STUDENT,group);
        editor.apply();

    }

    public static int getGroupStudent (SharedPreferences preferences){

        return preferences.getInt(GROUP_STUDENT,0);

    }


    public static void setVersionActually (SharedPreferences preferences,String vInt){ //Puedo poner el id del que ingreso

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("VersionApp",vInt);//Esto empezara desde el 1++ = 1
        editor.apply();
        //Tambien tendre q actualizar la version en el gradle?
    }

    public static String getVersionActually (SharedPreferences preferences){



        return preferences.getString("VersionApp","0");
        //Obtiene  la verson de la app
        //Si es cero es q olvide poner la version de la app
    }

    public static void setIfBeforeSignUp(SharedPreferences preferences){ //Puedo poner el id del que ingreso

        //Para saber si ya se habia registrado
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("SignUpStudentRegister",true);
        editor.apply();

    }

    public static boolean getIfStudentBeforeSignUp (SharedPreferences preferences){



        return preferences.getBoolean("SignUpStudentRegister",false);
        //Obtiene cualquier dato de tipo String

    }

    public static SharedPreferences getMySharedPreferences (Context context){

        return context.getSharedPreferences(Utils.APP_NAME_FOR_SHARED_PREFERENCES, Context.MODE_PRIVATE); //Solo mi app;
    }

    private static void removeSharedPreferences(SharedPreferences sharedPreferences){

        sharedPreferences.edit().clear().apply(); //Metodo para borrar las preferencias


    }

    public static String getSharedPreferencesData (SharedPreferences preferences,String dataForSearch){



        return preferences.getString(dataForSearch,"");
        //Obtiene cualquier dato de tipo String

    }

    public static void logoutAnyProfile(SharedPreferences preferences, Context context){

        Utils.removeSharedPreferences(preferences);
        Intent intent = new Intent(context,SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Este metodo es muy importante
        //Ya q boora el historial de activitys para q asi no se regrese a la pantalla principal o algun otro lado q no querramos
        context.startActivity(intent);
        Utils.setIfBeforeSignUp(preferences); //Con ste metodo al cerrar sesion se pondra esto y ya no podra registrarse

    }

    public static void intentent(Context context, Class classes){

        Intent i = new Intent(context,classes);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

        //A doncde voy intent
    }

    public static void intententwithoutFLAGS(Context context, Class classes){

        Intent i = new Intent(context,classes);
        context.startActivity(i);

        //A doncde voy intent
    }

    public static void setrecycler (RecyclerView recyclerView,Context context,String profiletoSearch,
                              ProgressBar progressBar,boolean isParent,SharedPreferences preferences){
                                      //Este identifica que profiles es    //Se usara si es parent si no null

        ArrayList<Posts> postsnews = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true));
        recyclerView.setPreserveFocusAfterLayout(true);
        recyclerView.setHasFixedSize(true);

        if (isParent){ //Para q entre a un if debe ser verdadero a no ser que este ese >  !

            WebServices.checkPostForParents(context,recyclerView,postsnews,progressBar,preferences);
            //profiletoSearch este no usa ese parametro

        }else{

            WebServices.checkInfoStudentGET(context,profiletoSearch,recyclerView,postsnews,progressBar);

        }



    }

    public static void getImageFromUrl(String url, Context context,ImageView view){

        String server = context.getString(R.string.ip);


        Glide.with(context).load(server + url).into(view);

    }

    public static void setContentViewForApp(Context context,int typeofContent,String key,String url){

        Intent i =  new Intent(context, NoInternetConnetivity.class);
        Bundle info =  new Bundle();
        info.putInt(key,typeofContent);
        info.putString("UrlPlayStore",url);
        i.putExtras(info);//Aqui se supone q ya estan los 2
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    public static void saveOnlyOneDateBundle(Context context,String table,String folder){

        //Este metodo solo servira para pasar los datos de la actualizacion de la foto de perfil
        //Dont should be use on another thing
        Intent i = new Intent(context, EditProfilePictureAll.class);
        Bundle infoneeded = new Bundle();
        infoneeded.putString("tableUsers",table);
        infoneeded.putString("folderUsers",folder);
        i.putExtras(infoneeded);
        context.startActivity(i);

    }

    public static void GotoInfoAboutStateApp(Context context,int codeError){

       //Este metodo es para el volley error, practiacamente aqui iria todo
        //Code error = Lo que tiene q hacer y lo tiene q mostrar

        Intent i =  new Intent(context, NoInternetConnetivity.class);
        Bundle info =  new Bundle();
        info.putInt("serverNotWorking",codeError);
        i.putExtras(info);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    public static int getSharedPreferencesInteger (SharedPreferences preferences,String dataForSearch){

        return preferences.getInt(dataForSearch,0);
        //Si es cero es no hay nada en las shared preferences

    }


}