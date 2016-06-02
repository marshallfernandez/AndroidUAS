package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.Aspect;

import java.util.ArrayList;

/**
 * Created by edu24 on 31/05/2016.
 */
public class AspectsComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public String studentResultId;
    public ArrayList<Aspect> listAspects;
    public TextView txtTitulo;
    public LinearLayout auxiliar;


    public AspectsComponent(Context context, String titulo, ArrayList<Aspect> lista) {
        super(context);
        this._context = context;
        this.studentResultId = titulo;
        this.listAspects = lista;

        inflate();
    }

    public void inflate() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_aspects, this);
        this.txtTitulo = (TextView) findViewById(R.id.aspects_stud_res_identificator);
        this.txtTitulo.setText(getResources().getString(R.string.uas_aspectos_titulo_resultado) + " " + studentResultId);

        this.auxiliar = (LinearLayout) findViewById(R.id.linear_final_aspects);

        for (Aspect aspect : listAspects) {
            AspectComponent component = new AspectComponent(_context, aspect);
            this.auxiliar.addView(component);
        }

    }
}
