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
import com.puntobat.uas.components.EducObjsComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.EducationalObjective;

import java.util.ArrayList;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducationalObjectivesFragment extends Fragment {

    public LinearLayout auxiliar;
    private ImageView noFoundImg;
    private TextView noFoundText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_educational_objectives, container, false);

        auxiliar = (LinearLayout) rootView.findViewById(R.id.linear_list_educ_objs);
        noFoundImg = (ImageView) rootView.findViewById(R.id.fragment_edu_obj_no_found_img);
        noFoundText = (TextView) rootView.findViewById(R.id.fragment_edu_obj_no_found_text);

        ArrayList<EducationalObjective> listEducObjAux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).EDUCATIONALOBJECTIVES;

        if(listEducObjAux.size()==0){
            noFoundImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_objetivos_edu_plomo));
            noFoundText.setText(getResources().getString(R.string.uas_principal_nada_registrado));
        }

        UAS.ABTITLE.setText("Objetivos Educacionales");

        EducObjsComponent educObjsComponent = new EducObjsComponent(getActivity(), listEducObjAux);
        auxiliar.addView(educObjsComponent);

        return rootView;

    }
}
