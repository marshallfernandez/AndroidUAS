package com.puntobat.uas.components;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.puntobat.uas.R;
import com.puntobat.uas.activities.InitialActivity;
import com.puntobat.uas.activities.SpecialtiesActivity;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.User;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by edu24 on 3/06/2016.
 */
public class IniUsersComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public String auxiliar;

    private InitialActivity.UsersDialog userDialog;
    private ConfirmPassDialog confirmDialog;

    SharedPreferences myPref;

    int userIndex;

    public IniUsersComponent(Context context, String string, InitialActivity.UsersDialog dg, SharedPreferences sp) {
        super(context);
        this._context = context;
        this.auxiliar = string;
        this.userDialog = dg;
        this.myPref = sp;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.item_ini_users, this);

        Button user = (Button) findViewById(R.id.item_ini_users_name);
        Button delete = (Button) findViewById(R.id.item_ini_users_delete);

        user.setText(auxiliar);

        confirmDialog = new ConfirmPassDialog(_context, user.getText().toString(), myPref);
        confirmDialog.updateLayout();

        user.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userDialog.dismiss();
                confirmDialog.show();
            }
        });

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(_context)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.uas_ini_alert_borrar_info)
                        .setMessage(R.string.uas_ini_alert_borrar_info_pregunta)
                        .setPositiveButton(R.string.uas_alert_si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UAS.deleteUserInfo(myPref, UAS.getUserIndex(myPref, auxiliar));
                                userDialog.dismiss();
                                Intent intent = new Intent(_context, InitialActivity.class);
                                ((Activity)_context).startActivity(intent);
                            }

                        })
                        .setNegativeButton(R.string.uas_alert_no, null)
                        .show();
            }
        });

    }

    public class ConfirmPassDialog extends Dialog {

        Context context;
        String userName;
        SharedPreferences sp;

        public ConfirmPassDialog(Context c, String userName, SharedPreferences sp) {
            super(c);
            this.context = c;
            this.userName = userName;
            this.sp = sp;
            setTitle(c.getResources().getString(R.string.uas_ini_dialog_titulo_confirm_pass));
            setContentView(R.layout.ini_dialog_confirm_pass_layout);
        }

        private void dialogLoadSPData(int userNum) {
            int specNum = sp.getInt("User" + userNum + UAS.SPECIALTIESNUMBERKEY, 0);
            UAS.SPECIALTIES = new ArrayList<Specialty>();
            UAS.SPECIALTIESINFO = new ArrayList<SpecialtyInfo>();

            Gson gson = new Gson();

            //loading user
            String jsonUser = sp.getString("User" + userNum + UAS.USERKEY, "");
            UAS.USER = gson.fromJson(jsonUser, User.class);

            //loading prev info
            for (int i = 1; i <= specNum; i++) {
                Specialty spec;
                SpecialtyInfo specInfo;

                String jsonSpec = sp.getString("User" + userNum + UAS.SPECIALTIESKEY + i, "");
                String jsonSpecInfo = sp.getString("User" + userNum + UAS.SPECIALTIESINFOKEY + i, "");

                spec = gson.fromJson(jsonSpec, Specialty.class);
                specInfo = gson.fromJson(jsonSpecInfo, SpecialtyInfo.class);

                UAS.SPECIALTIES.add(spec);
                UAS.SPECIALTIESINFO.add(specInfo);
            }

            //restoring language
            Locale locale = new Locale(sp.getString("User" + userNum + UAS.LANGUAGEKEY, "es"));
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getResources().updateConfiguration(config,
                    context.getResources().getDisplayMetrics());

            SharedPreferences.Editor prefsEditor = sp.edit();
            prefsEditor.remove(UAS.LOGGEDUSERKEY);
            prefsEditor.putInt(UAS.LOGGEDUSERKEY, userNum);

            prefsEditor.commit();

            Intent intent = new Intent(context, SpecialtiesActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();

            this.dismiss();
        }

        public void updateLayout() {
            EditText passText = (EditText) findViewById(R.id.ini_dialog_confirm_pass_text);
            Button confirmar = (Button) findViewById(R.id.ini_dialog_confirm_pass_boton);

            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int userIndex = UAS.getUserIndex(sp, userName);
                    dialogLoadSPData(userIndex);
                }
            });
        }
    }
}
