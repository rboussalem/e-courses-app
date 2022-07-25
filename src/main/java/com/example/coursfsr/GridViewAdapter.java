package com.example.coursfsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends ArrayAdapter {
    //private String[] strings;
    private ArrayList<Cours> list;
    private Context context;
    private int resId;

    DataBaseFav dataBaseFav;

    public GridViewAdapter(Context context, int resource, ArrayList list) {
        super(context, resource, list);
        this.list = list;
        this.context = context;
        this.resId = resource;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resId,parent,false);

        TextView tv_titre = convertView.findViewById(R.id.tv_titre);
        ImageView img = convertView.findViewById(R.id.img_cours);

        tv_titre.setText(list.get(position).getName());
        img.setImageResource(list.get(position).getImage());

        //#########################################################################
        dataBaseFav = new DataBaseFav(context.getApplicationContext());
        //favorite image_button
        ImageView img_fav = convertView.findViewById(R.id.img_fav);
        actionFav(img_fav,position);
        //***************************************************************************


        //icon favorite etat (mettre les etats des icons favorite )
        int check = dataBaseFav.get_check_List_Favorite(list.get(position).getName());
        if (check>0)
            img_fav.setImageResource(R.drawable.ic_favorite_black_24dp);

        return convertView;

    }



    public void actionFav(final ImageView img_fav,final int position){

        img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = dataBaseFav.get_check_List_Favorite(list.get(position).getName());
                if (check>0){
                    dataBaseFav.Delete(list.get(position).getName());
                    img_fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(context.getApplicationContext(),"le cours est supprimé",
                            Toast.LENGTH_LONG).show();
                }else{
                    dataBaseFav.Insert_to_favorite(list.get(position).getName(),list.get(position)
                            .getImage(),position);
                    img_fav.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(context.getApplicationContext(),"le cours est ajouté", Toast.LENGTH_LONG).show();
                    //Toast.makeText(Webhtml.this,"تم الاضافة في المفضلة", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
