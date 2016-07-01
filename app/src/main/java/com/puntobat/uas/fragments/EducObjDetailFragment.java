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
import com.puntobat.uas.components.StudResultComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.StudentResult;

/**
 * Created by edu24 on 17/06/2016.
 */
public class EducObjDetailFragment extends Fragment {

    TextView educObjNro;
    TextView educObjDescrription;
    TextView educObjStudResultsTitle;

    LinearLayout educObjStudResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_educational_objective_detail, container, false);

        educObjNro = (TextView) rootView.findViewById(R.id.edu_obj_detail_number);
        educObjDescrription = (TextView) rootView.findViewById(R.id.edu_obj_detail_description);
        educObjStudResults = (LinearLayout) rootView.findViewById(R.id.edu_obj_detail_stud_res_list);
        educObjStudResultsTitle = (TextView) rootView.findViewById(R.id.edu_obj_detail_stud_res_title);

        educObjNro.setText("" + UAS.EDUCATIONALOBJECTIVE.getNumber());
        educObjDescrription.setText(UAS.EDUCATIONALOBJECTIVE.getDescription());

        /*String myString = new String("Resultados Estudiantiles Asociados");
        SpannableString name = new SpannableString(myString);
        name.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        educObjStudResultsTitle.setText(name);*/

        for (StudentResult sr : UAS.EDUCATIONALOBJECTIVE.getStudentResults()) {
            StudResultComponent component = new StudResultComponent(getActivity(), sr, false);
            educObjStudResults.addView(component);
        }

        return rootView;

    }
}
