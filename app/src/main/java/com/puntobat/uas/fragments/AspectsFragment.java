package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puntobat.uas.R;
import com.puntobat.uas.components.AspectsComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.StudentResult;

import java.util.ArrayList;

/**
 * Created by edu24 on 27/05/2016.
 */
public class AspectsFragment extends Fragment {

    public LinearLayout auxiliar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_aspects, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.linear_list_aspects);

        ArrayList<StudentResult> listSRAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).STUDENTRESULTS;
        ArrayList<Aspect> listAspectAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).ASPECTS;

        for (StudentResult studentResult : listSRAux) {

            ArrayList<Aspect> ayudin = new ArrayList<Aspect>();

            for (Aspect aspect : listAspectAux) {
                if (aspect.getIdStudentResult() == studentResult.getId())
                    ayudin.add(aspect);
            }

            AspectsComponent aspectsComponent = new AspectsComponent(getActivity(), studentResult.getIdentificator(), ayudin);
            auxiliar.addView(aspectsComponent);

        }

        return rootView;

    }
}
