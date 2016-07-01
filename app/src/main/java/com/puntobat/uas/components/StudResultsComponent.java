package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.EducationalObjective;
import com.puntobat.uas.model.StudentResult;

import java.util.ArrayList;

/**
 * Created by edu24 on 1/06/2016.
 */
public class StudResultsComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public ArrayList<StudentResult> list;
    public LinearLayout auxiliar;


    public StudResultsComponent(Context context, ArrayList<StudentResult> list) {
        super(context);
        this._context = context;
        this.list = list;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_student_results, this);

        this.auxiliar = (LinearLayout) findViewById(R.id.component_stud_results_linear);

        for (StudentResult studentResult : list) {
            StudResultComponent component = new StudResultComponent(_context, studentResult,true);
            this.auxiliar.addView(component);
        }

    }
}
