package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity_Home extends AppCompatActivity {
    Animation animation;
    private Intent intent,intent2,intent3;
    private LinearLayout btn_cours,btn_favorite,btn_about,btn_exite,btn_websiteit,btn_notes;

    String user,nom,prenom,ce,email;
    TextView tv_nomprenom,tv_username_ce_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);

        //================================recuperer Les valeures d'etudiant

        intent3 = getIntent();
        user = intent3.getStringExtra("user");
        nom = intent3.getStringExtra("nom");
        prenom = intent3.getStringExtra("prenom");
        ce = intent3.getStringExtra("ce");
        email = intent3.getStringExtra("email");

        tv_nomprenom = findViewById(R.id.tv_nomprenom);
        tv_username_ce_email = findViewById(R.id.tv_username_ce_email);

        tv_nomprenom.setText(nom+" "+prenom);
        tv_username_ce_email.setText("username : "+user+" \nCode d Etudiant  : "+ce+" \nEmail : "+email);

        //=======================================Les Buttons
        btn_cours = findViewById(R.id.btn_cours);
        btn_favorite = findViewById(R.id.btn_favorite);
        btn_about = findViewById(R.id.btn_about);
        btn_websiteit = findViewById(R.id.btn_websiteit);
        btn_notes = findViewById(R.id.btn_notes);
        btn_exite = findViewById(R.id.btn_exite);
        animation = AnimationUtils.loadAnimation(Activity_Home.this,R.anim.bounce);
        btn_cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cours.setAnimation(animation);

                intent = new Intent(Activity_Home.this,Activity_Grid.class);

                intent.putExtra("user",user);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);

                startActivity(intent);

            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_favorite.setAnimation(animation);
                intent = new Intent(Activity_Home.this,Activity_Grid_Favorite.class);

                intent.putExtra("user",user);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);

                startActivity(intent);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_about.setAnimation(animation);
                intent = new Intent(Activity_Home.this,Activity_About.class);

                intent.putExtra("user",user);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);

                startActivity(intent);

            }
        });

        btn_websiteit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_websiteit.setAnimation(animation);
                intent = new Intent(Activity_Home.this,Activity_Contenu_HTML.class);
                startActivity(intent);
            }
        });

        btn_exite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_exite.setAnimation(animation);
                finish();
            }
        });

        btn_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_notes.setAnimation(animation);
                intent = new Intent(Activity_Home.this,Activity_Notes.class);

                intent.putExtra("user",user);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);

                startActivity(intent);
            }
        });






    }
}
