package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Grid extends AppCompatActivity {
    private GridView gridView;
    private ImageView img_grid_style;
    private ImageView img_home,img_favListe,btn_back;

    //private ImageView img_fav;

    TextView tv_nomprenom_2;
    Intent intent,intent2;
    String nom,prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__grid);

        intent2 = getIntent();
        nom = intent2.getStringExtra("nom");
        prenom = intent2.getStringExtra("prenom");
        tv_nomprenom_2 = findViewById(R.id.tv_nomprenom_2);
        tv_nomprenom_2.setText(nom+" "+prenom);

        // ========DATA=======Les Titres et l'images (int) des Cours=========
        ArrayList<Cours> list = new ArrayList<Cours>();
        Cours C1 = new Cours("JAVA",R.drawable.c0);
        Cours C2 = new Cours("Arduino",R.drawable.c1);
        Cours C3 = new Cours("Android",R.drawable.c2);
        Cours C4 = new Cours("Javascript",R.drawable.c3);
        Cours C5 = new Cours("Data mining",R.drawable.c4);
        Cours C6 = new Cours("Ontologie",R.drawable.c5);
        Cours C7 = new Cours("Reseaux de Petri",R.drawable.c6);
        Cours C8 = new Cours("Big Data",R.drawable.c7);

        list.add(C1);list.add(C2);list.add(C3);list.add(C4);list.add(C5);list.add(C6);
        list.add(C7);list.add(C8);


        //set la list a la GridView
        gridView = findViewById(R.id.grid_view);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,R.layout.grid_view_item,list);
        gridView.setAdapter(gridViewAdapter);

        //Button de style de GridView
        img_grid_style = findViewById(R.id.img_grid_style);
        img_grid_style.setOnClickListener(new View.OnClickListener() {
            int t=0;
            @Override
            public void onClick(View v) {
                if(t==0){
                    gridView.setNumColumns(2);t=1;
                    img_grid_style.setImageResource(R.drawable.ic_apps_black_24dp);
                }
                else {
                    gridView.setNumColumns(1);t=0;
                    img_grid_style.setImageResource(R.drawable.ic_view_list_black_24dp);
                }
            }
        });

        //click sur item(l'element) de gridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"TEST :::::: ", Toast.LENGTH_LONG).show();
                intent = new Intent(Activity_Grid.this, Activity_Contenu_HTML.class);
                intent.putExtra("position",position+"");
                startActivity(intent);
            }
        });


        //Button de Home
        img_home = findViewById(R.id.img_home);
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Button de favorite
        img_favListe = findViewById(R.id.img_favListe);
        img_favListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Activity_Grid.this,Activity_Grid_Favorite.class);

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
