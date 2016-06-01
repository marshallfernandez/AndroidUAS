package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.puntobat.uas.R;
import com.puntobat.uas.adapters.EducObjAdapter;
import com.puntobat.uas.constans.UAS;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducationalObjectiveFragment extends Fragment {

    ListView listaxurreta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_educational_objectives, container, false);

        listaxurreta = (ListView)rootView.findViewById(R.id.list_educational_objectives);

        EducObjAdapter educObjAdapter = new EducObjAdapter(getActivity(), UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).EDUCATIONALOBJECTIVES);

        listaxurreta.setAdapter(educObjAdapter);

        return rootView;

    }
}
