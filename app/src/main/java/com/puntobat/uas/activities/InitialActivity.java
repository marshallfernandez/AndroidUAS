package com.puntobat.uas.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.controller.GetController;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.User;
import com.puntobat.uas.request.LoginRequest;
import com.puntobat.uas.ui.settings.LoadViewTask;
import com.puntobat.uas.ui.settings.Loadingable;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by edu24 on 29/04/2016.
 */
public class InitialActivity extends AppCompatActivity implements Loadingable {

    Button loginButton;
    ImageView visiblePassButton;
    ImageView deleteUserButton;
    Spinner langSpinner;
    EditText userText;
    EditText passText;
    ImageView logo;
    LinearLayout bgLinear;
    boolean login;

    SharedPreferences myPref;

    private void saveSPData() {
        SharedPreferences.Editor prefsEditor = myPref.edit();
        Gson gson = new Gson();

        //saving info
        for (int i = 1; i <= UAS.SPECIALTIES.size(); i++) {
            Specialty spec = UAS.SPECIALTIES.get(i - 1);
            SpecialtyInfo specInfo = UAS.SPECIALTIESINFO.get(i - 1);
            String jsonSpec = gson.toJson(spec);
            String jsonSpecInfo = gson.toJson(specInfo);

            prefsEditor.putString("" + UAS.SPECIALTIESKEY + i, jsonSpec);
            prefsEditor.putString("" + UAS.SPECIALTIESINFOKEY + i, jsonSpecInfo);
        }

        //saving user
        String jsonUser = gson.toJson(UAS.USER);
        prefsEditor.putString(UAS.USERKEY,jsonUser);

        prefsEditor.putInt(UAS.SPECIALTIESNUMBERKEY, UAS.SPECIALTIES.size());
        prefsEditor.putString(UAS.USERNAMEKEY, userText.getText().toString());
        prefsEditor.putString(UAS.PASSWORDKEY, passText.getText().toString());
        prefsEditor.putString(UAS.LANGUAGEKEY, UAS.appLanguage);

        prefsEditor.commit();
    }

    @Override
    public void heavyTask() {

        GetController getController = new GetController(InitialActivity.this);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.password = passText.getText().toString();
        loginRequest.user = userText.getText().toString();

        login = getController.login(loginRequest);

        if (login) { //jalo todas la info de la bd en una
            UAS.SPECIALTIES = getController.getSpecialties();
            UAS.SPECIALTIESINFO = getController.getSpecialtiesInfo();

            saveSPData();
        }

    }

    @Override
    public void afterTask() {
        if (login) {
            Intent intent = new Intent(InitialActivity.this, SpecialtiesActivity.class);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(InitialActivity.this, R.string.uas_error_login, Toast.LENGTH_SHORT).show();

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void loadSPData() {
        int specNum = myPref.getInt(UAS.SPECIALTIESNUMBERKEY, 0);
        UAS.SPECIALTIES = new ArrayList<Specialty>();
        UAS.SPECIALTIESINFO = new ArrayList<SpecialtyInfo>();

        Gson gson = new Gson();

        //loading user
        String jsonUser = myPref.getString(UAS.USERKEY, "");
        UAS.USER = gson.fromJson(jsonUser, User.class);

        //loading prev info
        for (int i = 1; i <= specNum; i++) {
            Specialty spec;
            SpecialtyInfo specInfo;

            String jsonSpec = myPref.getString("" + UAS.SPECIALTIESKEY + i, "");
            String jsonSpecInfo = myPref.getString("" + UAS.SPECIALTIESINFOKEY + i, "");

            spec = gson.fromJson(jsonSpec, Specialty.class);
            specInfo = gson.fromJson(jsonSpecInfo, SpecialtyInfo.class);

            UAS.SPECIALTIES.add(spec);
            UAS.SPECIALTIESINFO.add(specInfo);
        }

        //restoring language
        Locale locale = new Locale(myPref.getString(UAS.LANGUAGEKEY, "es"));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPref = getSharedPreferences(UAS.MYSHAREDPREFERENCENAME, MODE_PRIVATE);
        if (myPref.contains(UAS.SPECIALTIESNUMBERKEY)) {
            loadSPData();
            Intent intent = new Intent(InitialActivity.this, SpecialtiesActivity.class);
            startActivity(intent);
            finish();
        }

        //establecer español por default
        String languageToLoad = UAS.appLanguage;
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_initial);

        //se obtiene el tamaño de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        UAS.screenWidth = size.x;
        UAS.screenHeight = size.y;

        loginButton = (Button) findViewById(R.id.loginButton);
        visiblePassButton = (ImageView) findViewById(R.id.ini_button_pass_visible);
        deleteUserButton = (ImageView) findViewById(R.id.ini_button_delete_username);
        langSpinner = (Spinner) findViewById(R.id.languageSpinner);
        userText = (EditText) findViewById(R.id.userTextLogin);
        passText = (EditText) findViewById(R.id.userPassLogin);
        logo = (ImageView) findViewById(R.id.logo_inicial);
        bgLinear = (LinearLayout) findViewById(R.id.activity_initial_linear);

        langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position) {
                    case 1:
                        cambiaIdioma("es");
                        break;
                    case 2:
                        cambiaIdioma("en");
                        break;
                    case 3:
                        cambiaIdioma("pt");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = userText.getText().toString();
                String contrasenha = passText.getText().toString();

                if (codigo.equals("") || contrasenha.equals("")) {
                    Toast.makeText(InitialActivity.this, R.string.uas_ini_toast_camposNoVacio, Toast.LENGTH_SHORT).show();
                } else {
                    if (isNetworkAvailable())
                        new LoadViewTask(InitialActivity.this, getResources().getString(R.string.uas_texto_cargando) + "...").execute();
                    else
                        Toast.makeText(InitialActivity.this, R.string.uas_no_internet, Toast.LENGTH_SHORT).show();
                    /*if(!UAS.esNumero(codigo)){
                        Toast.makeText(InitialActivity.this, R.string.uas_ini_toast_nombUsuarioSoloNum, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        new LoadViewTask(InitialActivity.this).execute();
                    }*/
                }
            }
        });

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userText.setText("");
            }
        });

        visiblePassButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        passText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        passText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        bgLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }

    private void cambiaIdioma(String newLanguage) {
        UAS.appLanguage = newLanguage;
        Intent refresh = new Intent(this, InitialActivity.class);
        startActivity(refresh);
        finish();
    }
}
