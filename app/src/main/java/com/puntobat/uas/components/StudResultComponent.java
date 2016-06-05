package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.StudentResult;

/**
 * Created by edu24 on 1/06/2016.
 */
public class StudResultComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public StudentResult auxiliar;
    public TextView educObjName;

    View linear;

    public StudResultComponent(Context context, StudentResult studentResult) {
        super(context);
        this._context = context;
        this.auxiliar = studentResult;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_student_result, this);

        this.educObjName = (TextView) findViewById(R.id.component_stud_result_name);
        this.educObjName.setText(getResources().getString(R.string.uas_aspectos_titulo_resultado) + " " + auxiliar.getIdentificator());

        linear = findViewById(R.id.component_stud_result_linear);

        this.linear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}
