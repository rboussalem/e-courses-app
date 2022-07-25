package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_go ;
    private Intent intent,intent2;
    private ImageView logo;
    String user,nom,prenom,ce,email;
    TextView tv_bienvenue;

    Animation animation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent2 = getIntent();
        user = intent2.getStringExtra("user");
        nom = intent2.getStringExtra("nom");
        prenom = intent2.getStringExtra("prenom");
        ce = intent2.getStringExtra("ce");
        email = intent2.getStringExtra("email");

        tv_bienvenue = findViewById(R.id.tv_bienvenue);
        tv_bienvenue.setText("BIENVENUE \n"+nom+" "+prenom);



        logo = findViewById(R.id.img_logo);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.blink_anim);
        logo.startAnimation(animation);

        animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);


        btn_go = findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_go.setAnimation(animation2);

                intent = new Intent(MainActivity.this,Activity_Home.class);

                intent.putExtra("user",user);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);
                intent.putExtra("ce",ce);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();

            }
        });
    }
}
