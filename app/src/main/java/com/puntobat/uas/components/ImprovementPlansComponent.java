package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.ImprovementPlan;

/**
 * Created by edu24 on 2/06/2016.
 */
public class ImprovementPlansComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public ImprovementPlan auxiliar;

    private TextView creator;
    private TextView type;
    private TextView impDate;
    private TextView analysis;
    private TextView description;

    private Button download;

    public ImprovementPlansComponent(Context context, ImprovementPlan improvementPlan){
        super(context);
        this._context = context;
        this.auxiliar = improvementPlan;

        inflate();
    }

    public void inflate(){

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.item_improv_plan, this);

        creator = (TextView) findViewById(R.id.item_improv_plan_creador);
        type = (TextView) findViewById(R.id.item_improv_plan_tipo);
        impDate = (TextView) findViewById(R.id.item_improv_plan_fechaimp);
        analysis = (TextView) findViewById(R.id.item_improv_plan_analisis);
        description = (TextView) findViewById(R.id.item_improv_plan_descripcion);

        download = (Button) findViewById(R.id.item_improv_plan_boton_descargar);

        creator.setText(auxiliar.getTeacher().getName()+" "+auxiliar.getTeacher().getLastName());
        type.setText(auxiliar.getImprovementPlanType().getCode());
        impDate.setText(UAS.getDateFormat(auxiliar.getImplementationDate()));
        analysis.setText(auxiliar.getCauseAnalisis());
        description.setText(auxiliar.getDescription());

        download.setVisibility(View.GONE);
    }
}
