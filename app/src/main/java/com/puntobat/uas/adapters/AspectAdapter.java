package com.puntobat.uas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Aspect;

import java.util.ArrayList;

/**
 * Created by edu24 on 27/05/2016.
 */
public class AspectAdapter extends BaseAdapter {

    public Context _context;
    public ArrayList<Aspect> items;

    public AspectAdapter(Context context, ArrayList<Aspect> lista){

        this._context = context;
        this.items = lista;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_aspect,parent,false);
        LinearLayout linearAspect = (LinearLayout) rowView.findViewById(R.id.linear_aspect_item);
        TextView aspectName = (TextView) rowView.findViewById(R.id.aspect_name);
        TextView aspectDesc = (TextView) rowView.findViewById(R.id.aspect_description);

        final Aspect aspect = items.get(position);
        aspectName.setText(aspect.getName());
        aspectDesc.setText("Pertenece al resultado estudiantil: "+UAS.getStudResultByAspect(aspect.getIdStudentResult()).getIdentificator());


        linearAspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UAS.ASPECT = aspect;
                Intent intent = new Intent(_context, AspectActivity.class);*/
                /*intent.putExtra("titulo",destinin.nombre);
                intent.putExtra("descripcion",destinin.descripcion);
                intent.putExtra("urlImg", destinin.imagen_princ);*/
                //_context.startActivity(intent);
            }
        });

        return rowView;
    }
}
