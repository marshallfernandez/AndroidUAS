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
import com.puntobat.uas.fragments.StudResultDetailFragment;
import com.puntobat.uas.model.StudentResult;

/**
 * Created by edu24 on 1/06/2016.
 */
public class StudResultComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public StudentResult auxiliar;
    public TextView educObjName;
    public TextView educObjDesc;
    public ImageView educObjStatus;

    public boolean isClickable;

    View linear;

    public StudResultComponent(Context context, StudentResult studentResult, boolean aux) {
        super(context);
        this._context = context;
        this.auxiliar = studentResult;
        this.isClickable = aux;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_student_result, this);

        this.educObjName = (TextView) findViewById(R.id.component_stud_result_name);
        this.educObjDesc = (TextView) findViewById(R.id.component_stud_result_description);
        this.educObjStatus = (ImageView) findViewById(R.id.component_stud_result_img);

        this.educObjName.setText(getResources().getString(R.string.uas_aspectos_titulo_resultado) + " " + auxiliar.getIdentificator());
        this.educObjDesc.setText(auxiliar.getDescription());

        if (auxiliar.getStatus() == 1)
            this.educObjStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_activo));
        else if (auxiliar.getStatus() == 0)
            this.educObjStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_inactivo));

        linear = findViewById(R.id.component_stud_result_linear);

        if (isClickable)
            this.linear.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    UAS.STUDENTRESULT = auxiliar;
                    UAS.ABTITLE.setText(getResources().getString(R.string.uas_aspectos_titulo_resultado) + " " + auxiliar.getIdentificator());
                    UAS.FRAGMENTMNGR.beginTransaction()
                            .replace(UAS.IDCONTAINER, new StudResultDetailFragment())
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }
            });
    }
}
