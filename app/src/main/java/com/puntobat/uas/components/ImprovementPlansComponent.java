package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.ImprovementPlan;

/**
 * Created by edu24 on 2/06/2016.
 */
public class ImprovementPlansComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public ImprovementPlan auxiliar;

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

        TextView user = (TextView) findViewById(R.id.item_improv_plan_usuario);
        TextView creator = (TextView) findViewById(R.id.item_improv_plan_creador);
        TextView startDate = (TextView) findViewById(R.id.item_improv_plan_fechai);
        TextView endDate = (TextView) findViewById(R.id.item_improv_plan_fechaf);

        Button viewActivities = (Button) findViewById(R.id.item_improv_plan_boton_actividades);
        Button download = (Button) findViewById(R.id.item_improv_plan_boton_descargar);

        viewActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //view schedules
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
