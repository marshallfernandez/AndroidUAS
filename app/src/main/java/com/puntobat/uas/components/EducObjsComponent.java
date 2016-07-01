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
    public ArrayList<EducationalObjective> list;
    public LinearLayout auxiliar;


    public EducObjsComponent(Context context, ArrayList<EducationalObjective> list) {
        super(context);
        this._context = context;
        this.list = list;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_educ_objs, this);

        this.auxiliar = (LinearLayout) findViewById(R.id.linear_final_educ_objs);

        for (EducationalObjective educationalObjective : list) {
            EducObjComponent component = new EducObjComponent(_context, educationalObjective,true);
            this.auxiliar.addView(component);
        }

    }
}
