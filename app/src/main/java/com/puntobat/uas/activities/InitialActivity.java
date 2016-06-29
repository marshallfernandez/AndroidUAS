package com.puntobat.uas.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.puntobat.uas.R;
import com.puntobat.uas.components.CoursesComponent;
import com.puntobat.uas.components.IniUsersComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.controller.GetController;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Course;
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
    Button usersButton;
    ImageButton alertButton;
    ImageView visiblePassButton;
    ImageView deleteUserButton;
    Spinner langSpinner;
    EditText userText;
    EditText passText;
    ImageView logo;
    LinearLayout bgLinear;
    boolean login;
    UsersDialog usersDialog;

    SharedPreferences myPref;

    private void saveSPData() {
        SharedPreferences.Editor prefsEditor = myPref.edit();
        Gson gson = new Gson();
        int usersNum = UAS.getExistsUser(myPref, userText.getText().toString());

        if (usersNum == -1) {
            usersNum = myPref.getInt(UAS.LOGGEDUSERSNUMBERKEY, 0);
            usersNum++;
        }

        //saving info
        for (int i = 1; i <= UAS.SPECIALTIES.size(); i++) {
            Specialty spec = UAS.SPECIALTIES.get(i - 1);
            SpecialtyInfo specInfo = UAS.SPECIALTIESINFO.get(i - 1);
            String jsonSpec = gson.toJson(spec);
            String jsonSpecInfo = gson.toJson(specInfo);

            prefsEditor.remove("User" + usersNum + UAS.SPECIALTIESKEY + i);
            prefsEditor.remove("User" + usersNum + UAS.SPECIALTIESINFOKEY + i);
            prefsEditor.putString("User" + usersNum + UAS.SPECIALTIESKEY + i, jsonSpec);
            prefsEditor.putString("User" + usersNum + UAS.SPECIALTIESINFOKEY + i, jsonSpecInfo);
        }

        //saving user
        String jsonUser = gson.toJson(UAS.USER);
        prefsEditor.remove("User" + usersNum + UAS.USERKEY);
        prefsEditor.putString("User" + usersNum + UAS.USERKEY, jsonUser);

        prefsEditor.remove("User" + usersNum + UAS.SPECIALTIESNUMBERKEY);
        prefsEditor.remove("User" + usersNum + UAS.USERNAMEKEY);
        prefsEditor.remove("User" + usersNum + UAS.PASSWORDKEY);
        prefsEditor.remove("User" + usersNum + UAS.LANGUAGEKEY);
        prefsEditor.putInt("User" + usersNum + UAS.SPECIALTIESNUMBERKEY, UAS.SPECIALTIES.size());
        prefsEditor.putString("User" + usersNum + UAS.USERNAMEKEY, userText.getText().toString());
        prefsEditor.putString("User" + usersNum + UAS.PASSWORDKEY, passText.getText().toString());
        prefsEditor.putString("User" + usersNum + UAS.LANGUAGEKEY, UAS.appLanguage);

        prefsEditor.remove(UAS.LOGGEDUSERSNUMBERKEY);
        prefsEditor.putInt(UAS.LOGGEDUSERSNUMBERKEY, usersNum);

        prefsEditor.remove(UAS.ISLOGGEDKEY);
        prefsEditor.putBoolean(UAS.ISLOGGEDKEY, true);

        prefsEditor.remove(UAS.LOGGEDUSERKEY);
        prefsEditor.putInt(UAS.LOGGEDUSERKEY, usersNum);

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
            int profileAux = UAS.USER.getIdProfile();
            if (profileAux == 3 || profileAux == 4 || profileAux == 5) {
                UAS.SPECIALTIES = getController.getSpecialties();
                UAS.SPECIALTIESINFO = getController.getSpecialtiesInfo();

                saveSPData();
            }
        }

    }

    @Override
    public void afterTask() {
        if (login) {
            int profileAux = UAS.USER.getIdProfile();
            if (profileAux == 3 || profileAux == 4 || profileAux == 5) {
                Intent intent = new Intent(InitialActivity.this, SpecialtiesActivity.class);
                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(InitialActivity.this, "La aplicación solo admite acreditadores", Toast.LENGTH_LONG).show();
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

    private void loadSPData(int userNum) {
        int specNum = myPref.getInt("User" + userNum + UAS.SPECIALTIESNUMBERKEY, 0);
        UAS.SPECIALTIES = new ArrayList<Specialty>();
        UAS.SPECIALTIESINFO = new ArrayList<SpecialtyInfo>();

        Gson gson = new Gson();

        //loading user
        String jsonUser = myPref.getString("User" + userNum + UAS.USERKEY, "");
        UAS.USER = gson.fromJson(jsonUser, User.class);

        //loading prev info
        for (int i = 1; i <= specNum; i++) {
            Specialty spec;
            SpecialtyInfo specInfo;

            String jsonSpec = myPref.getString("User" + userNum + UAS.SPECIALTIESKEY + i, "");
            String jsonSpecInfo = myPref.getString("User" + userNum + UAS.SPECIALTIESINFOKEY + i, "");

            spec = gson.fromJson(jsonSpec, Specialty.class);
            specInfo = gson.fromJson(jsonSpecInfo, SpecialtyInfo.class);

            UAS.SPECIALTIES.add(spec);
            UAS.SPECIALTIESINFO.add(specInfo);
        }

        //restoring language
        Locale locale = new Locale(myPref.getString("User" + userNum + UAS.LANGUAGEKEY, "es"));
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

        if (myPref.contains(UAS.LOGGEDUSERSNUMBERKEY)) {
            boolean isLogged = myPref.getBoolean(UAS.ISLOGGEDKEY, false);

            if (isLogged) {
                int userLogged = myPref.getInt(UAS.LOGGEDUSERKEY, 1);
                loadSPData(userLogged);
                Intent intent = new Intent(InitialActivity.this, SpecialtiesActivity.class);
                startActivity(intent);
                finish();
            }
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
        usersButton = (Button) findViewById(R.id.users_button);
        alertButton = (ImageButton) findViewById(R.id.ini_alert_button);
        visiblePassButton = (ImageView) findViewById(R.id.ini_button_pass_visible);
        deleteUserButton = (ImageView) findViewById(R.id.ini_button_delete_username);
        langSpinner = (Spinner) findViewById(R.id.languageSpinner);
        userText = (EditText) findViewById(R.id.userTextLogin);
        passText = (EditText) findViewById(R.id.userPassLogin);
        logo = (ImageView) findViewById(R.id.logo_inicial);
        bgLinear = (LinearLayout) findViewById(R.id.activity_initial_linear);

        ArrayList<String> listLoggedUsers = UAS.getLoggedUsers(myPref);

        usersDialog = new UsersDialog(this, myPref);
        usersDialog.updateLayout(listLoggedUsers);

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v);
            }
        });

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

        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersDialog.show();
                //You can dialog.dismiss() to close the dialog.
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

        alertButton.setVisibility(View.GONE);
        langSpinner.setVisibility(View.GONE);
    }

    private void displayPopupWindow(View anchorView) {
        PopupWindow popup = new PopupWindow(InitialActivity.this);
        View layout = getLayoutInflater().inflate(R.layout.ini_popup_content, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView, 0, 10);
    }

    private void cambiaIdioma(String newLanguage) {
        UAS.appLanguage = newLanguage;
        Intent refresh = new Intent(this, InitialActivity.class);
        startActivity(refresh);
        finish();
    }

    public class UsersDialog extends Dialog {

        Context context;
        SharedPreferences sp;

        public UsersDialog(Context c, SharedPreferences sp) {
            super(c);
            context = c;
            this.sp = sp;
            setTitle(c.getResources().getString(R.string.uas_ini_dialog_titulo));
            setContentView(R.layout.ini_users_layout);
        }

        public void updateLayout(ArrayList<String> lines) {
            LinearLayout linear = (LinearLayout) findViewById(R.id.ini_users_layout_linear);
            for (String s : lines) {
                IniUsersComponent newUser = new IniUsersComponent(context, s, this, sp);
                linear.addView(newUser);
            }
        }
    }
}
