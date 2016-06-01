package com.puntobat.uas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.EducationalObjective;

import java.util.ArrayList;

/**
 * Created by edu24 on 31/05/2016.
 */
public class EducObjAdapter extends BaseAdapter {

    public Context _context;
    public ArrayList<EducationalObjective> items;

    public EducObjAdapter(Context context, ArrayList<EducationalObjective> lista) {

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

        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_educational_objectives, parent, false);
        LinearLayout linearAspect = (LinearLayout) rowView.findViewById(R.id.linear_educ_obj_item);
        TextView educObjName = (TextView) rowView.findViewById(R.id.educational_objective_name);
        TextView educObjSemester = (TextView) rowView.findViewById(R.id.educational_objective_semester);

        final EducationalObjective educObj = items.get(position);
        educObjName.setText(_context.getResources().getString(R.string.uas_educ_obj_title) + " " + educObj.getNumber());
        educObjSemester.setText(_context.getResources().getString(R.string.uas_educ_obj_semester) + ": " + educObj.getSemesterReg());


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
