package com.puntobat.uas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;
    DrawerLayout drawerLayout;
    LinearLayout navDrawerTablet;
    TextView nameProfile;
    TextView roleProfile;
    TextView facultyProfile;
    ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_media_route_disabled_mono_dark);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.uas_ab_inicio);

        navDrawerTablet = (LinearLayout) findViewById(R.id.listview_drawer);
        profileImg = (ImageView) findViewById(R.id.profile_image);
        nameProfile = (TextView) findViewById(R.id.name_profile);
        roleProfile = (TextView) findViewById(R.id.role_profile);
        facultyProfile = (TextView) findViewById(R.id.faculty_profile);

        cambiaProporciones();

        //fragment seleccionado inicialmente
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();

    }

    private void cambiaProporciones(){
        navDrawerTablet.getLayoutParams().width = UAS.screenWidth / 4;
        profileImg.getLayoutParams().width = UAS.screenWidth / 8;

        nameProfile.setTextSize(UAS.fontSize);
        roleProfile.setTextSize(UAS.fontSize);
        facultyProfile.setTextSize(UAS.fontSize);
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
                Intent intent = new Intent(MainActivity.this, SpecialtiesActivity.class);
                startActivity(intent);
                finish();
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
