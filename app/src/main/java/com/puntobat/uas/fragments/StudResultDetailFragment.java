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
import com.puntobat.uas.components.EducObjComponent;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.EducationalObjective;

/**
 * Created by edu24 on 23/06/2016.
 */
public class StudResultDetailFragment extends Fragment {

    TextView studResId;
    TextView studResDescrription;
    TextView studResEducObjsTitle;

    LinearLayout studResEducObjs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student_result_detail, container, false);

        studResId = (TextView) rootView.findViewById(R.id.stud_res_detail_id);
        studResDescrription = (TextView) rootView.findViewById(R.id.stud_res_detail_description);
        studResEducObjs = (LinearLayout) rootView.findViewById(R.id.stud_res_detail_educ_obj_list);
        studResEducObjsTitle = (TextView) rootView.findViewById(R.id.stud_res_detail_edu_obj_title);

        studResId.setText(UAS.STUDENTRESULT.getIdentificator());
        studResDescrription.setText(UAS.STUDENTRESULT.getDescription());

        /*String myString = new String("Objetivos Educacionales Asociados");
        SpannableString name = new SpannableString(myString);
        name.setSpan(new UnderlineSpan(), 0, myString.length(), 0);

        studResEducObjsTitle.setText(name);*/

        for (EducationalObjective eo : UAS.STUDENTRESULT.getListEducObj()) {
            EducObjComponent component = new EducObjComponent(getActivity(), eo, false);
            studResEducObjs.addView(component);
        }

        return rootView;

    }
}
