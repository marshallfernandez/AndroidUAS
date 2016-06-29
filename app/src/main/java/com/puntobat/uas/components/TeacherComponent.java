package com.puntobat.uas.components;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.Teacher;

/**
 * Created by edu24 on 24/06/2016.
 */
public class TeacherComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Teacher auxiliar;
    public TextView teacherCode;
    public TextView teacherName;

    View linear;

    TeacherDialog dialog;

    public TeacherComponent(Context context, Teacher t) {
        super(context);
        this._context = context;
        this.auxiliar = t;
        this.dialog = new TeacherDialog(context,t);

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_teacher, this);

        this.teacherCode = (TextView) findViewById(R.id.component_teacher_codigo);
        this.teacherName = (TextView) findViewById(R.id.component_teacher_nombre);

        this.teacherCode.setText(auxiliar.getCode());
        this.teacherName.setText(auxiliar.getName() + " " + auxiliar.getLastName());

        linear = findViewById(R.id.component_teacher_linear);

        dialog.updateLayout();

        this.linear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public class TeacherDialog extends Dialog {

        Context context;
        SharedPreferences sp;
        Teacher teacher;

        public TeacherDialog(Context c, Teacher t) {
            super(c);
            context = c;
            teacher = t;
            setTitle(teacher.getCharge());
            setContentView(R.layout.teacher_detail_layout);
        }

        public void updateLayout() {
            TextView tCode = (TextView) findViewById(R.id.teacher_detail_codigo);
            TextView tName = (TextView) findViewById(R.id.teacher_detail_nombre);
            TextView tEmail = (TextView) findViewById(R.id.teacher_detail_email);
            TextView tRef = (TextView) findViewById(R.id.teacher_detail_referencias);

            tCode.setText(teacher.getCode());
            tName.setText(teacher.getName()+" "+teacher.getLastName());
            tEmail.setText(teacher.getEmail());
            tRef.setText(teacher.getDescription());
        }
    }
}
