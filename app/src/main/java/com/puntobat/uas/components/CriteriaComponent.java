package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.Criterio;

/**
 * Created by edu24 on 23/06/2016.
 */
public class CriteriaComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Criterio auxiliar;
    public TextView criterioName;
    public ImageView criterioStatus;

    View linear;

    public CriteriaComponent(Context context, Criterio crit){
        super(context);
        this._context = context;
        this.auxiliar = crit;

        inflate();
    }

    public void inflate(){

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_criterio, this);

        this.criterioName = (TextView)findViewById(R.id.component_criterio_name);
        this.criterioStatus = (ImageView) findViewById(R.id.component_criterio_status_img);

        this.criterioName.setText(auxiliar.getName());

        if (auxiliar.getStatus() == 1)
            this.criterioStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_activo));
        else if (auxiliar.getStatus() == 0)
            this.criterioStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_inactivo));

        linear = findViewById(R.id.component_criterio_linear);

    }
}
