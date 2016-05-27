package com.puntobat.uas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.puntobat.uas.adapters.SpecialtyAdapter;
import com.puntobat.uas.controller.GetController;
import com.puntobat.uas.ui.settings.LoadViewTask;
import com.puntobat.uas.ui.settings.Loadingable;

/**
 * Created by edu24 on 6/05/2016.
 */
public class SpecialtiesActivity extends AppCompatActivity implements Loadingable {

    ActionBar actionBar;
    ListView listaxurreta;

    @Override
    public void heavyTask() {

        GetController getController = new GetController(SpecialtiesActivity.this);
        UAS.SPECIALTIES = getController.getSpecialties();

    }

    @Override
    public void afterTask() {
        listaxurreta = (ListView) findViewById(R.id.list_specialties);

        SpecialtyAdapter specialtyAdapter = new SpecialtyAdapter(this, UAS.SPECIALTIES);

        listaxurreta.setAdapter(specialtyAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialties);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_specialties);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_logout);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.uas_ab_especialidades);


        new LoadViewTask(SpecialtiesActivity.this, getResources().getString(R.string.uas_texto_cargando)).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.uas_alert_cerrarSesion_titulo)
                        .setMessage(R.string.uas_alert_salir_pregunta)
                        .setPositiveButton(R.string.uas_alert_si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SpecialtiesActivity.this, InitialActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        })
                        .setNegativeButton(R.string.uas_alert_no, null)
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

