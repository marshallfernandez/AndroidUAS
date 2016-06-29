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
    private ImageView noFoundImg;
    private TextView noFoundText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_aspects, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.linear_list_aspects);
        noFoundImg = (ImageView) rootView.findViewById(R.id.fragment_aspects_no_found_img);
        noFoundText = (TextView) rootView.findViewById(R.id.fragment_aspects_no_found_text);

        ArrayList<Aspect> listAspectAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).ASPECTS;
        ArrayList<StudentResult> listSRAux = UAS.getStudResultsByAspects(listAspectAux);

        if (listAspectAux.size() == 0) {
            noFoundImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_aspectos_plomo));
            noFoundText.setText(getResources().getString(R.string.uas_principal_nada_registrado));
        }

        UAS.ABTITLE.setText("Aspectos");

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
