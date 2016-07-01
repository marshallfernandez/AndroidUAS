package com.puntobat.uas.components;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.fragments.ScheduleDetailFragment;
import com.puntobat.uas.model.Evidence;
import com.puntobat.uas.model.Schedule;

import java.util.ArrayList;

/**
 * Created by edu24 on 23/06/2016.
 */
public class ScheduleComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Schedule auxiliar;
    public TextView schedCode;
    public Button evidButton;
    public Button teachersButton;

    public EvidencesDialog dialog;

    public ScheduleComponent(Context context, Schedule s) {
        super(context);
        this._context = context;
        this.auxiliar = s;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_schedule, this);

        this.schedCode = (TextView) findViewById(R.id.component_schedule_codigo);
        this.evidButton = (Button) findViewById(R.id.schedule_button_evidences);
        this.teachersButton = (Button) findViewById(R.id.schedule_button_teachers);

        this.schedCode.setText(auxiliar.getCode());

        dialog = new EvidencesDialog(_context);
        dialog.updateLayout(auxiliar.getEvidences());

        this.teachersButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UAS.TEACHERS = auxiliar.getTeachers();
                UAS.SCHEDULE = auxiliar;

                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new ScheduleDetailFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });

        this.evidButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public class EvidencesDialog extends Dialog {

        Context context;
        SharedPreferences sp;

        public EvidencesDialog(Context c) {
            super(c);
            context = c;
            setTitle("Evidencias del horario " + auxiliar.getCode());
            setContentView(R.layout.course_evidences_dialog);
        }

        public void updateLayout(ArrayList<Evidence> evidences) {
            LinearLayout linear = (LinearLayout) findViewById(R.id.course_evidences_list);
            for (Evidence e : evidences) {
                EvidenceComponent newEvidence = new EvidenceComponent(context, e, this);
                linear.addView(newEvidence);
            }
        }
    }
}
