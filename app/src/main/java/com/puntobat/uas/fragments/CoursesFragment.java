package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.puntobat.uas.R;
import com.puntobat.uas.components.CoursesComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.helpers.CourseWithReport;
import com.puntobat.uas.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edu24 on 1/06/2016.
 */
public class CoursesFragment extends Fragment {

    private LinearLayout auxiliar;
    private ImageView noFoundImg;
    private TextView noFoundText;
    private TextView semesterTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.fragment_courses_listview);
        noFoundImg = (ImageView) rootView.findViewById(R.id.fragment_courses_no_found_img);
        noFoundText = (TextView) rootView.findViewById(R.id.fragment_courses_no_found_text);
        semesterTitle = (TextView) rootView.findViewById(R.id.fragment_courses_semester_title);

        ArrayList<String> spinnerArray =  new ArrayList<String>(UAS.getSemestersNames(UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).PERIODSEMESTERS));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) rootView.findViewById(R.id.fragment_courses_spinner_semesters);
        sItems.setAdapter(adapter);

        if(!UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).PERIODSEMESTERS.isEmpty()) {

            sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    switch (position) {
                        default:
                            updateCoursesList(sItems.getSelectedItem().toString());
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


            noFoundImg.setVisibility(View.GONE);
            noFoundText.setVisibility(View.GONE);

        }
        else{
            sItems.setVisibility(View.GONE);
            semesterTitle.setVisibility(View.GONE);
        }

        noFoundImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_cursos_plomo));
        noFoundText.setText(getResources().getString(R.string.uas_principal_nada_registrado));

        UAS.ABTITLE.setText("Cursos");

        return rootView;

    }

    private void updateCoursesList(String semester){
        ArrayList<CourseWithReport> listCourses = UAS.getCoursesBySemester(semester);
        ArrayList<Integer> listLevels = UAS.getLevelsByCourses(listCourses);

        auxiliar.removeAllViews();

        if (listCourses.size() == 0) {
            noFoundImg.setVisibility(View.VISIBLE);
            noFoundText.setVisibility(View.VISIBLE);
        }
        else{
            noFoundImg.setVisibility(View.GONE);
            noFoundText.setVisibility(View.GONE);
        }

        for (Integer level : listLevels) {

            ArrayList<CourseWithReport> ayudin = new ArrayList<CourseWithReport>();

            for (CourseWithReport c : listCourses) {
                if (c.course.getAcademicLevel().compareTo(Integer.toString(level)) == 0) {
                    ayudin.add(c);
                }
            }

            CoursesComponent coursesComponent = new CoursesComponent(getActivity(), "" + level, ayudin);
            auxiliar.addView(coursesComponent);

        }
    }
}
