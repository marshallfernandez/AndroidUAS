package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.Aspect;

/**
 * Created by edu24 on 31/05/2016.
 */
public class AspectComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Aspect auxiliar;
    public TextView aspectName;

    View linearAspect;

    public AspectComponent(Context context, Aspect aspect){
        super(context);
        this._context = context;
        this.auxiliar = aspect;

        inflate();
    }

    public void inflate(){

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.component_aspect, this);

        this.aspectName = (TextView)findViewById(R.id.component_aspect_name);
        this.aspectName.setText(auxiliar.getName());

        linearAspect = findViewById(R.id.component_aspect_linear);

        this.linearAspect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
}
