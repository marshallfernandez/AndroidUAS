package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.components.ScheduleComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Schedule;

/**
 * Created by edu24 on 23/06/2016.
 */
public class CourseDetailFragment extends Fragment {

    LinearLayout schedList;

    TextView courseCode;
    TextView courseLevel;
    TextView courseSpec;

    TextView courseSchedTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_course_detail, container, false);

        schedList = (LinearLayout) rootView.findViewById(R.id.fragment_course_detail_sched_list);
        courseCode = (TextView) rootView.findViewById(R.id.course_detail_codigo);
        courseLevel = (TextView) rootView.findViewById(R.id.course_detail_nivel);
        courseSpec = (TextView) rootView.findViewById(R.id.course_detail_especialidad);
        courseSchedTitle = (TextView) rootView.findViewById(R.id.course_detail_sched_title);

        courseCode.setText(UAS.COURSE.getCode());
        courseLevel.setText(UAS.COURSE.getAcademicLevel());
        courseSpec.setText(UAS.SPECIALTY.getName());

        /*String myString = new String("Horarios del curso");
        SpannableString name = new SpannableString(myString);
        name.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        courseSchedTitle.setText(name);*/

        UAS.ABTITLE.setText(UAS.COURSE.getName());

        for (Schedule s : UAS.COURSE.getSchedules()) {
            ScheduleComponent component = new ScheduleComponent(getActivity(), s);
            schedList.addView(component);
        }

        return rootView;

    }
}
