package com.puntobat.uas.components;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.Course;

/**
 * Created by edu24 on 1/06/2016.
 */
public class CoursesComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Course auxiliar;

    public CoursesComponent(Context context, Course course){
        super(context);
        this._context = context;
        this.auxiliar = course;

        inflate();
    }

    public void inflate(){

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.item_course, this);

        Button viewSchedulesButton = (Button) findViewById(R.id.item_course_button_schedules);
        TextView name = (TextView) findViewById(R.id.item_course_name);
        TextView code = (TextView) findViewById(R.id.item_course_code);
        TextView level = (TextView) findViewById(R.id.item_course_level);

        String myString = new String(auxiliar.getName());
        SpannableString nameAux = new SpannableString(myString);
        nameAux.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        name.setText(nameAux);
        code.setText(auxiliar.getCode());
        level.setText(auxiliar.getAcademicLevel());

        viewSchedulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //view schedules
            }
        });

    }
}
