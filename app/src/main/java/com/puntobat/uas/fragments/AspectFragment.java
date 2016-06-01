package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.adapters.AspectAdapter;

/**
 * Created by edu24 on 27/05/2016.
 */
public class AspectFragment extends Fragment {

    ListView listaxurreta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_aspects, container, false);

        listaxurreta = (ListView)rootView.findViewById(R.id.list_aspects);

        AspectAdapter aspectAdapter = new AspectAdapter(getActivity(), UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).ASPECTS);

        listaxurreta.setAdapter(aspectAdapter);

        return rootView;

    }
}
