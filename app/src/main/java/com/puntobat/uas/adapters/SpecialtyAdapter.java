package com.puntobat.uas.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.puntobat.uas.activities.MainActivity;
import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.model.Specialty;

import java.util.ArrayList;

/**
 * Created by edu24 on 26/05/2016.
 */
public class SpecialtyAdapter extends BaseAdapter {

    public Context _context;
    public ArrayList<Specialty> items;

    public SpecialtyAdapter(Context context, ArrayList<Specialty> lista){

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_specialty,parent,false);
        LinearLayout linearSpecialty = (LinearLayout) rowView.findViewById(R.id.linear_specialty_item);
        TextView specialtyName = (TextView) rowView.findViewById(R.id.specialty_name);

        final Specialty specialty = items.get(position);
        specialtyName.setText(specialty.getName());


        linearSpecialty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!UAS.ISREFRESHING) {
                    UAS.SPECIALTY = specialty;
                    UAS.INFOINDEX = position;
                    Intent intent = new Intent(_context, MainActivity.class);
                    _context.startActivity(intent);
                    ((Activity) _context).finish();
                }
                else{
                    Toast.makeText(_context, R.string.uas_ya_actualizando, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rowView;
    }

}
