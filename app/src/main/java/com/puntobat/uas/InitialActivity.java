package com.puntobat.uas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by edu24 on 29/04/2016.
 */
public class InitialActivity extends AppCompatActivity {

    Button loginButton;
    Spinner langSpinner;
    EditText userText;
    EditText passText;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //establecer español por default
        String languageToLoad  = UAS.appLanguage;
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

        estableceProporciones();
        cambiaProporcion();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = userText.getText().toString();
                String contrasenha = passText.getText().toString();

                if (codigo.equals("") || contrasenha.equals("")) {
                    Toast.makeText(InitialActivity.this, R.string.uas_ini_toast_camposNoVacio, Toast.LENGTH_SHORT).show();
                } else {
                    if(!UAS.esNumero(codigo)){
                        Toast.makeText(InitialActivity.this, R.string.uas_ini_toast_nombUsuarioSoloNum, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(InitialActivity.this, SpecialtiesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });


    }

    private void cambiaIdioma(String newLanguage){
        UAS.appLanguage = newLanguage;
        Intent refresh = new Intent(this, InitialActivity.class);
        startActivity(refresh);
        finish();
    }

    private void estableceProporciones(){
        UAS.fontSize = UAS.screenHeight/75;
    }

    private void cambiaProporcion(){
        logo.getLayoutParams().width = UAS.screenWidth / 4;
        userText.getLayoutParams().width = UAS.screenWidth / 5;
        passText.getLayoutParams().width = UAS.screenWidth / 5;
        loginButton.getLayoutParams().width = UAS.screenWidth / 8;
        langSpinner.getLayoutParams().width = UAS.screenWidth / 8;
        userText.getLayoutParams().height = UAS.screenHeight / 12;
        passText.getLayoutParams().height = UAS.screenHeight / 12;
        loginButton.getLayoutParams().height = UAS.screenHeight / 15;

        userText.setTextSize(UAS.fontSize);
        passText.setTextSize(UAS.fontSize);
        loginButton.setTextSize(UAS.fontSize);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.uas_alert_salirUAS_titulo)
                .setMessage(R.string.uas_alert_salir_pregunta)
                .setPositiveButton(R.string.uas_alert_si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(R.string.uas_alert_no, null)
                .show();
    }

}
