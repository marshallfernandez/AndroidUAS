package com.puntobat.uas;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.puntobat.uas.controller.GetController;
import com.puntobat.uas.request.LoginRequest;
import com.puntobat.uas.ui.settings.LoadViewTask;
import com.puntobat.uas.ui.settings.Loadingable;

import java.util.Locale;

/**
 * Created by edu24 on 29/04/2016.
 */
public class InitialActivity extends AppCompatActivity implements Loadingable {

    @Override
    public void heavyTask() {

        GetController getController = new GetController(InitialActivity.this);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.password = passText.getText().toString();
        loginRequest.user = userText.getText().toString();

        login = getController.login(loginRequest);

    }

    @Override
    public void afterTask() {
        if (login) {
            Intent intent = new Intent(InitialActivity.this, SpecialtiesActivity.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(InitialActivity.this,R.string.uas_error_login,Toast.LENGTH_SHORT).show();

    }

    Button loginButton;
    Spinner langSpinner;
    EditText userText;
    EditText passText;
    ImageView logo;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //establecer español por default
        String languageToLoad = UAS.appLanguage;
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.initial_activity);

        //se obtiene el tamaño de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        UAS.screenWidth = size.x;
        UAS.screenHeight = size.y;

        loginButton = (Button) findViewById(R.id.loginButton);
        langSpinner = (Spinner) findViewById(R.id.languageSpinner);
        userText = (EditText) findViewById(R.id.userTextLogin);
        passText = (EditText) findViewById(R.id.userPassLogin);
        logo = (ImageView) findViewById(R.id.logo_inicial);

        langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 1: cambiaIdioma("es");
                        break;
                    case 2: cambiaIdioma("en");
                        break;
                    case 3: cambiaIdioma("pt");
                        break;
                    default: break;
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
                    new LoadViewTask(InitialActivity.this,getResources().getString(R.string.uas_texto_cargando)).execute();
                    /*if(!UAS.esNumero(codigo)){
                        Toast.makeText(InitialActivity.this, R.string.uas_ini_toast_nombUsuarioSoloNum, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        new LoadViewTask(InitialActivity.this).execute();
                    }*/
                }
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
