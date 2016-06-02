package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.EducationalObjective;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducObjComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public EducationalObjective auxiliar;
    public TextView educObjName;

    View linearEducObj;

    public EducObjComponent(Context context, EducationalObjective educationalObjective) {
        super(context);
        this._context = context;
        this.auxiliar = educationalObjective;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_educ_obj, this);

        this.educObjName = (TextView) findViewById(R.id.component_educ_obj_name);
        this.educObjName.setText(getResources().getString(R.string.uas_educ_obj_title) + " " + auxiliar.getId());

        linearEducObj = findViewById(R.id.component_educ_obj_linear);

        this.linearEducObj.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
}
