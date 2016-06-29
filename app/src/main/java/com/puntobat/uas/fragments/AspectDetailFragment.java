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
import com.puntobat.uas.components.CriteriaComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Criterio;

/**
 * Created by edu24 on 23/06/2016.
 */
public class AspectDetailFragment extends Fragment {

    LinearLayout criteriaList;

    TextView aspectCritListTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_aspect_detail, container, false);

        criteriaList = (LinearLayout) rootView.findViewById(R.id.aspect_detail_criteria_list);
        aspectCritListTitle = (TextView) rootView.findViewById(R.id.aspect_detail_criteria_title);

        /*String myString = new String("Criterios Asociados");
        SpannableString name = new SpannableString(myString);
        name.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        aspectCritListTitle.setText(name);*/

        for (Criterio c : UAS.ASPECT.getListCriterios()) {
            CriteriaComponent component = new CriteriaComponent(getActivity(), c);
            criteriaList.addView(component);
        }

        return rootView;

    }
}