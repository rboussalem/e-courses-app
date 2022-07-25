package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity_Grid_Favorite extends AppCompatActivity {
    private GridView gridView;

    private ImageView img_home_f,img_grid_style_f,btn_back;
    //private ImageView img_fav;

    Intent intent,intent2;
    String nom,prenom;
    TextView tv_nomprenom_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__grid__favorite);

        gridView = findViewById(R.id.grid_view_f);


        intent2 = getIntent();
        nom = intent2.getStringExtra("nom");
        prenom = intent2.getStringExtra("prenom");
        tv_nomprenom_2 = findViewById(R.id.tv_nomprenom_2);
        tv_nomprenom_2.setText(nom+" "+prenom);

        DataBaseFav dataBaseFav = new DataBaseFav(this);
        ArrayList<Cours> list = new ArrayList<Cours>();
        list = dataBaseFav.get_All_Favorite();

        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,R.layout.grid_view_item_favorite,list);
        gridView.setAdapter(gridViewAdapter);

        img_grid_style_f = findViewById(R.id.img_grid_style_f);
        img_grid_style_f.setOnClickListener(new View.OnClickListener() {
            int t=0;
            @Override
            public void onClick(View v) {
                if(t==0){
                    gridView.setNumColumns(1);t=1;
                    img_grid_style_f.setImageResource(R.drawable.ic_view_list_black_24dp);
                }
                else {
                    gridView.setNumColumns(2);t=0;
                    img_grid_style_f.setImageResource(R.drawable.ic_apps_black_24dp);
                }

            }
        });



        //int t = list.get(3).getNumCours();
        final ArrayList<Cours> finalList = list;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"TEST :::::: ", Toast.LENGTH_LONG).show();

                intent = new Intent(Activity_Grid_Favorite.this, Activity_Contenu_HTML.class);
                intent.putExtra("position", finalList.get(position).getNumCours()+"");
                startActivity(intent);
            }
        });

        //fav

        //gridView.btn_fav.

        img_home_f = findViewById(R.id.img_home_f);

        img_home_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent = new Intent(Activity_Grid_Favorite.this,Activity_Home.class);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);
               startActivity(intent);
            }
        });




        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
