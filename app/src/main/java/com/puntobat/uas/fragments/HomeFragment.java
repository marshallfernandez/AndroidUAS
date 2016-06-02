package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Specialty;

/**
 * Created by edu24 on 29/04/2016.
 */
public class HomeFragment extends Fragment {

    TextView specialtyName;
    TextView specialtyCode;
    TextView specialtyDescription;
    TextView specialtyCoordinator;
    TextView specialtyCriteria;
    TextView specialtyAcceptance;
    TextView specialtyAcceptancePorc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        specialtyName = (TextView) rootView.findViewById(R.id.home_specialty_name);
        specialtyCode = (TextView) rootView.findViewById(R.id.home_specialty_code);
        specialtyDescription = (TextView) rootView.findViewById(R.id.home_specialty_description);
        specialtyCoordinator = (TextView) rootView.findViewById(R.id.home_specialty_coordinator);
        specialtyCriteria = (TextView) rootView.findViewById(R.id.home_specialty_criteria);
        specialtyAcceptance = (TextView) rootView.findViewById(R.id.home_specialty_aceptation);
        specialtyAcceptancePorc = (TextView) rootView.findViewById(R.id.home_specialty_aceptation_perc);

        Specialty aux = UAS.SPECIALTY;

        String myString = new String(aux.getName());
        SpannableString name = new SpannableString(myString);
        name.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        specialtyName.setText(name);
        specialtyCode.setText(aux.getCode());
        specialtyDescription.setText(aux.getDescription());
        specialtyCoordinator.setText(aux.getTeacher().getName() + " " + aux.getTeacher().getLastName());
        specialtyCriteria.setText("");
        specialtyAcceptance.setText("");
        specialtyAcceptancePorc.setText("");

        return rootView;

    }
}
