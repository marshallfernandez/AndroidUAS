package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.puntobat.uas.R;

/**
 * Created by edu24 on 29/04/2016.
 */
public class MaintFragment extends Fragment {

    ListView listaxurreta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_maint, container, false);

        return rootView;

    }
}
