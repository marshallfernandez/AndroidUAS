package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puntobat.uas.R;
import com.puntobat.uas.components.CoursesComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Course;

import java.util.ArrayList;

/**
 * Created by edu24 on 1/06/2016.
 */
public class CoursesFragment extends Fragment {
    public LinearLayout auxiliar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        auxiliar= (LinearLayout) rootView.findViewById(R.id.fragment_courses_listview);

        ArrayList<Course> listCoursesAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).COURSES;

        for (Course course : listCoursesAux) {

            CoursesComponent coursesComponent = new CoursesComponent(getActivity(), course);
            auxiliar.addView(coursesComponent);

        }

        return rootView;

    }
}
