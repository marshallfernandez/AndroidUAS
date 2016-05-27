package com.puntobat.uas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextView nameProfile;
    TextView roleProfile;
    TextView facultyProfile;

    LinearLayout homeLinear;
    LinearLayout specialtiesLinear;
    LinearLayout objectivesLinear;
    LinearLayout resultStudLinear;
    LinearLayout rubricsLinear;
    LinearLayout aspectsLinear;
    LinearLayout coursesLinear;
    LinearLayout improveLinear;
    LinearLayout resultEvalLinear;
    LinearLayout logoutLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.uas_ab_inicio);

        nameProfile = (TextView) findViewById(R.id.name_profile);
        roleProfile = (TextView) findViewById(R.id.role_profile);
        facultyProfile = (TextView) findViewById(R.id.faculty_profile);

        homeLinear = (LinearLayout) findViewById(R.id.home_linear);
        specialtiesLinear = (LinearLayout) findViewById(R.id.specialties_linear);
        objectivesLinear = (LinearLayout) findViewById(R.id.objectives_linear);
        resultStudLinear = (LinearLayout) findViewById(R.id.result_stu_linear);
        rubricsLinear = (LinearLayout) findViewById(R.id.rubrics_linear);
        aspectsLinear = (LinearLayout) findViewById(R.id.aspects_linear);
        coursesLinear = (LinearLayout) findViewById(R.id.courses_linear);
        improveLinear = (LinearLayout) findViewById(R.id.improve_linear);
        resultEvalLinear = (LinearLayout) findViewById(R.id.result_eval_linear);
        logoutLinear = (LinearLayout) findViewById(R.id.logout_linear);

        nameProfile.setText(UAS.USER.getTeacher().getName() + " " + UAS.USER.getTeacher().getLastName());
        roleProfile.setText(UAS.USER.getTeacher().getCharge());
        facultyProfile.setText(UAS.SPECIALTY.getName());

        homeLinear.setPressed(true);

        //fragment seleccionado inicialmente
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        /*new AlertDialog.Builder(this)
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
                .show();*/
    }
}
