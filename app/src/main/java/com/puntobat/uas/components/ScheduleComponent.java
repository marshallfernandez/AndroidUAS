package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.fragments.ScheduleDetailFragment;
import com.puntobat.uas.model.Schedule;

/**
 * Created by edu24 on 23/06/2016.
 */
public class ScheduleComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Schedule auxiliar;
    public TextView schedCode;
    public TextView schedTotal;

    View linear;

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
        this.schedTotal = (TextView) findViewById(R.id.component_schedule_total_alumnos);

        this.schedCode.setText(auxiliar.getCode());
        this.schedTotal.setText("" + auxiliar.getTotalStudents());

        linear = findViewById(R.id.component_schedule_linear);

        this.linear.setOnClickListener(new OnClickListener() {
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
    }
}
