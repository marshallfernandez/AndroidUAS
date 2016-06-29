package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.EducationalObjective;

import java.util.ArrayList;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducObjsComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public String title;
    public ArrayList<EducationalObjective> list;
    public TextView txtTitle;
    public LinearLayout auxiliar;


    public EducObjsComponent(Context context, String title, ArrayList<EducationalObjective> list) {
        super(context);
        this._context = context;
        this.title = title;
        this.list = list;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_educ_objs, this);
        this.txtTitle = (TextView) findViewById(R.id.educ_obj_semester_title);
        this.txtTitle.setText(getResources().getString(R.string.uas_educ_obj_semester) + " " + title);

        this.auxiliar = (LinearLayout) findViewById(R.id.linear_final_educ_objs);

        for (EducationalObjective educationalObjective : list) {
            EducObjComponent component = new EducObjComponent(_context, educationalObjective,true);
            this.auxiliar.addView(component);
        }

    }
}
