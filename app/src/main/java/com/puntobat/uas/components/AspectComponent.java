package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.fragments.AspectDetailFragment;
import com.puntobat.uas.fragments.EducObjDetailFragment;
import com.puntobat.uas.model.Aspect;

/**
 * Created by edu24 on 31/05/2016.
 */
public class AspectComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Aspect auxiliar;
    public TextView aspectName;
    public ImageView aspectStatus;

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
        this.aspectStatus = (ImageView) findViewById(R.id.component_aspect_status_img);

        this.aspectName.setText(auxiliar.getName());

        if (auxiliar.getStatus() == 1)
            this.aspectStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_activo));
        else if (auxiliar.getStatus() == 0)
            this.aspectStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_estado_inactivo));

        linearAspect = findViewById(R.id.component_aspect_linear);

        this.linearAspect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UAS.ASPECT = auxiliar;
                UAS.ABTITLE.setText(auxiliar.getName());
                UAS.FRAGMENTMNGR.beginTransaction()
                        .replace(UAS.IDCONTAINER, new AspectDetailFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });


    }
}
