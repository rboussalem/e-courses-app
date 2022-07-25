package com.example.coursfsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListeViewAdapter extends ArrayAdapter {
    private ArrayList<iteamNote> list;
    Context context;
    int resource;
    public ListeViewAdapter(Context context, int resource, ArrayList list) {
        super(context, resource, list);
        this.list = list;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resource,parent,false);

        TextView tv_module = convertView.findViewById(R.id.tv_module);
        TextView tv_titre_module = convertView.findViewById(R.id.tv_titre_module);
        TextView tv_note = convertView.findViewById(R.id.tv_note);

        tv_module.setText(list.get(position).getModule());
        tv_titre_module.setText(list.get(position).getTitreModule());
        tv_note.setText(list.get(position).getModuleNote()+"");

        return convertView;
    }
}
