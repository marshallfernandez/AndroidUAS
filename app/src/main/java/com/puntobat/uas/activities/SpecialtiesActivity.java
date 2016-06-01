package com.puntobat.uas.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.puntobat.uas.R;
import com.puntobat.uas.adapters.SpecialtyAdapter;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.controller.GetController;

/**
 * Created by edu24 on 6/05/2016.
 */
public class SpecialtiesActivity extends AppCompatActivity {

    ActionBar actionBar;
    ListView listaxurreta;
    SwipeRefreshLayout swipeLayout;

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

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_specialties);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNetworkAvailable()){
                    refreshContent();
                }
                else{
                    Toast.makeText(SpecialtiesActivity.this,R.string.uas_no_internet, Toast.LENGTH_SHORT).show();
                    swipeLayout.setRefreshing(false);
                }


            }
        });

        listaxurreta = (ListView) findViewById(R.id.list_specialties);

        SpecialtyAdapter specialtyAdapter = new SpecialtyAdapter(this, UAS.SPECIALTIES);

        listaxurreta.setAdapter(specialtyAdapter);
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

    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new HttpMySpecialtiesTask(swipeLayout).execute();
                SpecialtyAdapter specialtyAdapter = new SpecialtyAdapter(SpecialtiesActivity.this, UAS.SPECIALTIES);
                listaxurreta.setAdapter(specialtyAdapter);
                Toast.makeText(SpecialtiesActivity.this,R.string.uas_info_actualizada, Toast.LENGTH_SHORT).show();
            }
        }, 3000);
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

    private class HttpMySpecialtiesTask extends AsyncTask<Void, Integer, Void> {

        SwipeRefreshLayout srl;

        public HttpMySpecialtiesTask(SwipeRefreshLayout newSrl){
            this.srl = newSrl;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Get the current thread's token
            synchronized (this) {
                GetController getController = new GetController(SpecialtiesActivity.this);
                UAS.SPECIALTIES = getController.getSpecialties();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            srl.setRefreshing(false);
        }

    }
}

