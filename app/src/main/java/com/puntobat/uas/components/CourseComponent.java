package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.fragments.CourseDetailFragment;
import com.puntobat.uas.model.Course;

/**
 * Created by edu24 on 23/06/2016.
 */
public class CourseComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Course auxiliar;
    public TextView courseCode;
    public TextView courseName;

    View linear;

    public CourseComponent(Context context, Course course) {
        super(context);
        this._context = context;
        this.auxiliar = course;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_course, this);

        this.courseCode = (TextView) findViewById(R.id.component_course_codigo);
        this.courseName = (TextView) findViewById(R.id.component_course_nombre);

        this.courseCode.setText(auxiliar.getCode());
        this.courseName.setText(auxiliar.getName());

        linear = findViewById(R.id.component_course_linear);

        this.linear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UAS.COURSE = auxiliar;
                UAS.ABTITLE.setText(auxiliar.getName());
                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new CourseDetailFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });


    }
}