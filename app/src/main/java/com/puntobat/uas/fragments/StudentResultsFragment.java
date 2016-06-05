package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puntobat.uas.R;
import com.puntobat.uas.components.StudResultsComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.StudentResult;

import java.util.ArrayList;

/**
 * Created by edu24 on 1/06/2016.
 */
public class StudentResultsFragment extends Fragment {

    public LinearLayout auxiliar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_student_results, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.fragment_student_results_linear);

        ArrayList<String> listSemesters = UAS.getSemestersByStudResults();
        ArrayList<StudentResult> listStudResultAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).STUDENTRESULTS;

        for (String semester : listSemesters) {

            ArrayList<StudentResult> ayudin = new ArrayList<StudentResult>();

            for (StudentResult studentResult : listStudResultAux) {
                if (studentResult.getSemesterReg().compareTo(semester) == 0)
                    ayudin.add(studentResult);
            }

            StudResultsComponent studResultsComponent = new StudResultsComponent(getActivity(), semester, ayudin);
            auxiliar.addView(studResultsComponent);

        }

        return rootView;

    }
}
