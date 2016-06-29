package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.components.CoursesComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Course;

import java.util.ArrayList;

/**
 * Created by edu24 on 1/06/2016.
 */
public class CoursesFragment extends Fragment {

    private LinearLayout auxiliar;
    private ImageView noFoundImg;
    private TextView noFoundText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.fragment_courses_listview);
        noFoundImg = (ImageView) rootView.findViewById(R.id.fragment_courses_no_found_img);
        noFoundText = (TextView) rootView.findViewById(R.id.fragment_courses_no_found_text);

        ArrayList<Course> listCourses = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).COURSES;
        ArrayList<Integer> listLevels = UAS.getLevelsByCourses();

        if (listCourses.size() == 0) {
            noFoundImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_cursos_plomo));
            noFoundText.setText(getResources().getString(R.string.uas_principal_nada_registrado));
        }

        UAS.ABTITLE.setText("Cursos");

        for (Integer level : listLevels) {

            ArrayList<Course> ayudin = new ArrayList<Course>();

            for (Course c : listCourses) {
                if (c.getAcademicLevel().compareTo(Integer.toString(level)) == 0) {
                    ayudin.add(c);
                }
            }

            CoursesComponent coursesComponent = new CoursesComponent(getActivity(), "" + level, ayudin);
            auxiliar.addView(coursesComponent);

        }

        return rootView;

    }
}
