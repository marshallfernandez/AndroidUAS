package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puntobat.uas.R;
import com.puntobat.uas.components.EducObjsComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.EducationalObjective;

import java.util.ArrayList;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducationalObjectivesFragment extends Fragment {

    public LinearLayout auxiliar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_educational_objectives, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.linear_list_educ_objs);

        ArrayList<String> listSemesters = UAS.getSemestersByEducObjs();
        ArrayList<EducationalObjective> listEducObjAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).EDUCATIONALOBJECTIVES;

        for (String semester : listSemesters) {

            ArrayList<EducationalObjective> ayudin = new ArrayList<EducationalObjective>();

            for (EducationalObjective educationalObjective : listEducObjAux) {
                if (educationalObjective.getSemesterReg().compareTo(semester) == 0)
                    ayudin.add(educationalObjective);
            }

            EducObjsComponent educObjsComponent = new EducObjsComponent(getActivity(), semester, ayudin);
            auxiliar.addView(educObjsComponent);

        }

        return rootView;

    }
}
