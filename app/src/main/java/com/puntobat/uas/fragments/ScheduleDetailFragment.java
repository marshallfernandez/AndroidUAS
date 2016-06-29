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
import com.puntobat.uas.components.TeacherComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Teacher;

/**
 * Created by edu24 on 24/06/2016.
 */
public class ScheduleDetailFragment extends Fragment {

    LinearLayout teachersList;
    TextView teachersListTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_schedule_detail, container, false);

        teachersList = (LinearLayout) rootView.findViewById(R.id.fragment_schedule_detail_list);
        teachersListTitle = (TextView) rootView.findViewById(R.id.fragment_schedule_detail_title);

        /*String myString = new String("Profesores del horario");
        SpannableString name = new SpannableString(myString);
        name.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        teachersListTitle.setText(name);*/

        UAS.ABTITLE.setText(UAS.COURSE.getName() + " - " + UAS.SCHEDULE.getCode());

        for (Teacher t : UAS.TEACHERS) {
            TeacherComponent component = new TeacherComponent(getActivity(), t);
            teachersList.addView(component);
        }

        return rootView;

    }
}
