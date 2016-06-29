package com.puntobat.uas.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puntobat.uas.R;
import com.puntobat.uas.model.Suggestion;

/**
 * Created by edu24 on 26/06/2016.
 */
public class SuggestionComponent extends LinearLayout {

    public LinearLayout linearLayout;
    public Context _context;
    public Suggestion auxiliar;

    private TextView teacher;
    private TextView title;
    private TextView date;
    private TextView suggestion;

    public SuggestionComponent(Context context, Suggestion suggestion){
        super(context);
        this._context = context;
        this.auxiliar = suggestion;

        inflate();
    }

    public void inflate(){

        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = new LinearLayout(_context);
        layoutInflater.inflate(R.layout.item_suggestion, this);

        teacher = (TextView) findViewById(R.id.item_suggestion_profesor);
        title = (TextView) findViewById(R.id.item_suggestion_titulo);
        date = (TextView) findViewById(R.id.item_suggestion_fecha);
        suggestion = (TextView) findViewById(R.id.item_suggestion_sugerencia);

        teacher.setText(auxiliar.getTeacher().getName()+" "+auxiliar.getTeacher().getLastName());
        title.setText(auxiliar.getTitle());
        date.setText(auxiliar.getDate());
        suggestion.setText(auxiliar.getDescription());

    }
}
