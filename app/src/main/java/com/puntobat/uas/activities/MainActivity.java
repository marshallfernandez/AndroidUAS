package com.puntobat.uas.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.fragments.AspectsFragment;
import com.puntobat.uas.fragments.ContinuousImprovementFragment;
import com.puntobat.uas.fragments.CoursesFragment;
import com.puntobat.uas.fragments.EducationalObjectivesFragment;
import com.puntobat.uas.fragments.HomeFragment;
import com.puntobat.uas.fragments.MaintFragment;
import com.puntobat.uas.fragments.ReportFragment;
import com.puntobat.uas.fragments.StudentResultsFragment;

public class MainActivity extends AppCompatActivity {

    TextView nameProfile;
    TextView roleProfile;
    TextView facultyProfile;

    TextView abTitle;

    TextView navNumSpec;

    LinearLayout homeLinear;
    LinearLayout specialtiesLinear;
    LinearLayout objectivesLinear;
    LinearLayout resultStudLinear;
    LinearLayout aspectsLinear;
    LinearLayout coursesLinear;
    LinearLayout improveLinear;
    LinearLayout resultEvalLinear;
    LinearLayout logoutLinear;

    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);

        myPrefs = getSharedPreferences(UAS.MYSHAREDPREFERENCENAME, MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        abTitle = (TextView) findViewById(R.id.uas_action_bar_title);
        abTitle.setText(R.string.uas_ab_inicio);

        UAS.ABTITLE = abTitle;

        nameProfile = (TextView) findViewById(R.id.name_profile);
        roleProfile = (TextView) findViewById(R.id.role_profile);
        facultyProfile = (TextView) findViewById(R.id.faculty_profile);
        navNumSpec = (TextView) findViewById(R.id.nav_number_specialties);

        homeLinear = (LinearLayout) findViewById(R.id.home_linear);
        specialtiesLinear = (LinearLayout) findViewById(R.id.specialties_linear);
        objectivesLinear = (LinearLayout) findViewById(R.id.objectives_linear);
        resultStudLinear = (LinearLayout) findViewById(R.id.result_stu_linear);
        aspectsLinear = (LinearLayout) findViewById(R.id.aspects_linear);
        coursesLinear = (LinearLayout) findViewById(R.id.courses_linear);
        improveLinear = (LinearLayout) findViewById(R.id.improve_linear);
        resultEvalLinear = (LinearLayout) findViewById(R.id.result_eval_linear);
        logoutLinear = (LinearLayout) findViewById(R.id.logout_linear);

        UAS.IDCONTAINER = R.id.container;
        UAS.FRAGMENTMNGR = getSupportFragmentManager();

        String name;
        String role;
        String faculty;

        if (UAS.USER.getTeacher() != null) {
            name = UAS.USER.getTeacher().getName() + " " + UAS.USER.getTeacher().getLastName();
            role = UAS.USER.getTeacher().getCharge();
            faculty = UAS.getSpecialtyById(UAS.USER.getTeacher().getIdSpecialty()).getName();
        } else if(UAS.USER.getAccreditor() != null){
            name = UAS.USER.getAccreditor().getName() + " " + UAS.USER.getAccreditor().getLastName();
            role = "Acreditador";
            faculty = UAS.getSpecialtyById(UAS.USER.getAccreditor().getIdSpecialty()).getName();
        }
        else{
            name = "Admin";
            role = "Administrador";
            faculty = "";
        }

        nameProfile.setText(name);
        roleProfile.setText(role);
        facultyProfile.setText(faculty);

        navNumSpec.setText(String.valueOf(UAS.SPECIALTIES.size()));

        homeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                homeLinear.setSelected(true);
                abTitle.setText(R.string.uas_ab_inicio);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new HomeFragment())
                        .commitAllowingStateLoss();
            }
        });

        specialtiesLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpecialtiesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        objectivesLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                objectivesLinear.setSelected(true);
                abTitle.setText(R.string.uas_nav_objEd);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new EducationalObjectivesFragment())
                        .commitAllowingStateLoss();
            }
        });

        resultStudLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                resultStudLinear.setSelected(true);
                abTitle.setText(R.string.uas_nav_resEst);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new StudentResultsFragment())
                        .commitAllowingStateLoss();
            }
        });

        aspectsLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                aspectsLinear.setSelected(true);
                abTitle.setText(R.string.uas_nav_aspectos);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new AspectsFragment())
                        .commitAllowingStateLoss();
            }
        });

        coursesLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                coursesLinear.setSelected(true);
                abTitle.setText(R.string.uas_nav_cursos);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new CoursesFragment())
                        .commitAllowingStateLoss();
            }
        });

        improveLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                improveLinear.setSelected(true);
                abTitle.setText(R.string.uas_nav_mejora);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new ContinuousImprovementFragment())
                        .commitAllowingStateLoss();
            }
        });

        resultEvalLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectMenu();
                resultEvalLinear.setSelected(true);
                abTitle.setText(R.string.uas_nav_resEval);

                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                    UAS.FRAGMENTMNGR.popBackStack();

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new ReportFragment())
                        .commitAllowingStateLoss();
            }
        });

        logoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.uas_alert_cerrarSesion_titulo)
                        .setMessage(R.string.uas_alert_salir_pregunta)
                        .setPositiveButton(R.string.uas_alert_si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, InitialActivity.class);
                                startActivity(intent);
                                SharedPreferences.Editor editor = myPrefs.edit();
                                editor.putBoolean(UAS.ISLOGGEDKEY, false);
                                editor.commit();
                                finish();
                            }

                        })
                        .setNegativeButton(R.string.uas_alert_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0)
                                    UAS.FRAGMENTMNGR.popBackStack();
                            }

                        })
                        .show();
            }
        });

        //fragment seleccionado inicialmente
        homeLinear.setSelected(true);
        UAS.FRAGMENTMNGR.beginTransaction()
                .replace(UAS.IDCONTAINER, new HomeFragment())
                .commitAllowingStateLoss();
    }

    private void deselectMenu() {
        homeLinear.setSelected(false);
        objectivesLinear.setSelected(false);
        resultStudLinear.setSelected(false);
        aspectsLinear.setSelected(false);
        coursesLinear.setSelected(false);
        improveLinear.setSelected(false);
        resultEvalLinear.setSelected(false);
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
        if (UAS.FRAGMENTMNGR.getBackStackEntryCount() > 0) {
            UAS.FRAGMENTMNGR.popBackStack();
        } else {
            Intent intent = new Intent(MainActivity.this, SpecialtiesActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
