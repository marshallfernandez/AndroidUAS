package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.fragments.EducObjDetailFragment;
import com.puntobat.uas.model.EducationalObjective;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducObjComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public EducationalObjective auxiliar;
    public TextView educObjName;
    public TextView educObjDesc;
    public ImageView educObjStatus;

    public boolean isClickable;

    View linearEducObj;

    public EducObjComponent(Context context, EducationalObjective educationalObjective, boolean aux) {
        super(context);
        this._context = context;
        this.auxiliar = educationalObjective;
        this.isClickable = aux;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_educ_obj, this);

        this.educObjName = (TextView) findViewById(R.id.component_educ_obj_name);
        this.educObjDesc = (TextView) findViewById(R.id.component_educ_obj_description);
        this.educObjStatus = (ImageView) findViewById(R.id.component_educ_obj_status_img);

        this.educObjName.setText(getResources().getString(R.string.uas_educ_obj_title) + " " + auxiliar.getNumber());
        this.educObjDesc.setText(auxiliar.getDescription());

        if (auxiliar.getStatus() == 1)
            this.educObjStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_activo));
        else if (auxiliar.getStatus() == 0)
            this.educObjStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_inactivo));

        linearEducObj = findViewById(R.id.component_educ_obj_linear);

        if (isClickable)
            this.linearEducObj.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    UAS.EDUCATIONALOBJECTIVE = auxiliar;
                    UAS.ABTITLE.setText(getResources().getString(R.string.uas_educ_obj_title) + " " + auxiliar.getNumber());
                    UAS.FRAGMENTMNGR.beginTransaction()
                            .replace(UAS.IDCONTAINER, new EducObjDetailFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }
            });
    }
}
