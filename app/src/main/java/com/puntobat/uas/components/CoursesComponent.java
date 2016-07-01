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
import com.puntobat.uas.helpers.CourseWithReport;
import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.Course;

import java.util.ArrayList;

/**
 * Created by edu24 on 1/06/2016.
 */
public class CoursesComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public String title;
    public ArrayList<CourseWithReport> listCourses;
    public TextView txtTitulo;
    public LinearLayout auxiliar;


    public CoursesComponent(Context context, String titulo, ArrayList<CourseWithReport> lista) {
        super(context);
        this._context = context;
        this.title = titulo;
        this.listCourses = lista;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_courses, this);
        this.txtTitulo = (TextView) findViewById(R.id.component_courses_nombre_titulo);
        this.txtTitulo.setText(getResources().getString(R.string.uas_cursos_nivel_titulo) + " " + title);

        this.auxiliar = (LinearLayout) findViewById(R.id.component_courses_list);

        for (CourseWithReport c : listCourses) {
            CourseComponent component = new CourseComponent(_context, c.course,c.reportBySemester);
            this.auxiliar.addView(component);
        }

    }
}
