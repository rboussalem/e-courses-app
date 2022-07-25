package com.example.coursfsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_About extends AppCompatActivity {
    ImageView btn_back;

    TextView tv_nomprenom_2;
    Intent intent,intent2;
    String nom,prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about);

        intent2 = getIntent();
        nom = intent2.getStringExtra("nom");
        prenom = intent2.getStringExtra("prenom");
        tv_nomprenom_2 = findViewById(R.id.tv_nomprenom_2);
        tv_nomprenom_2.setText(nom+" "+prenom);


        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
