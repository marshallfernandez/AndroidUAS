package com.puntobat.uas.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.puntobat.uas.R;
import com.puntobat.uas.adapters.SpecialtyAdapter;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.controller.GetController;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.request.LoginRequest;

/**
 * Created by edu24 on 6/05/2016.
 */
public class SpecialtiesActivity extends AppCompatActivity {

    ActionBar actionBar;
    ListView listaxurreta;
    SwipeRefreshLayout swipeLayout;

    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialties);

        myPrefs = getSharedPreferences(UAS.MYSHAREDPREFERENCENAME, MODE_PRIVATE);

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
                if (isNetworkAvailable()) {
                    refreshContent();
                } else {
                    Toast.makeText(SpecialtiesActivity.this, R.string.uas_no_internet, Toast.LENGTH_SHORT).show();
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
                new HttpMySpecialtiesTask(swipeLayout, listaxurreta,myPrefs).execute();
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
                                SharedPreferences.Editor editor = myPrefs.edit();
                                editor.clear();
                                editor.commit();
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
        ListView list;
        SharedPreferences sp;

        public HttpMySpecialtiesTask(SwipeRefreshLayout newSrl, ListView listaxurreta, SharedPreferences newSp) {
            this.srl = newSrl;
            this.list = listaxurreta;
            this.sp = newSp;
        }

        private void saveSPData() {
            SharedPreferences.Editor prefsEditor = sp.edit();
            Gson gson = new Gson();

            //saving info
            for (int i = 1; i <= UAS.SPECIALTIES.size(); i++) {
                Specialty spec = UAS.SPECIALTIES.get(i-1);
                SpecialtyInfo specInfo = UAS.SPECIALTIESINFO.get(i-1);
                String jsonSpec = gson.toJson(spec);
                String jsonSpecInfo = gson.toJson(specInfo);

                prefsEditor.remove("" + UAS.SPECIALTIESKEY + i);
                prefsEditor.remove("" + UAS.SPECIALTIESINFOKEY + i);
                prefsEditor.putString("" + UAS.SPECIALTIESKEY + i, jsonSpec);
                prefsEditor.putString("" + UAS.SPECIALTIESINFOKEY + i, jsonSpecInfo);
            }

            //saving user
            String jsonUser = gson.toJson(UAS.USER);
            prefsEditor.remove(UAS.USERKEY);
            prefsEditor.putString(UAS.USERKEY,jsonUser);

            prefsEditor.remove(UAS.SPECIALTIESNUMBERKEY);
            prefsEditor.putInt(UAS.SPECIALTIESNUMBERKEY,UAS.SPECIALTIES.size());

            prefsEditor.commit();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Get the current thread's token
            synchronized (this) {
                GetController getController = new GetController(SpecialtiesActivity.this);

                //updating TOKEN
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.password = sp.getString(UAS.PASSWORDKEY,"");
                loginRequest.user = sp.getString(UAS.USERNAMEKEY,"");
                getController.updateToken(loginRequest);

                UAS.SPECIALTIES = getController.getSpecialties();
                UAS.SPECIALTIESINFO = getController.getSpecialtiesInfo();

                saveSPData();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            srl.setRefreshing(false);
            SpecialtyAdapter specialtyAdapter = new SpecialtyAdapter(SpecialtiesActivity.this, UAS.SPECIALTIES);
            listaxurreta.setAdapter(specialtyAdapter);
            Toast.makeText(SpecialtiesActivity.this, R.string.uas_info_actualizada, Toast.LENGTH_SHORT).show();
        }

    }
}

