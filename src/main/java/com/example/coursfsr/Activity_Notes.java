package com.example.coursfsr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Notes extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    Intent intent,intent2 ;
    ImageView btn_back;
    TextView tv_nomprenom_2;
    String nom,prenom,user;

    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__notes);

        animation = AnimationUtils.loadAnimation(Activity_Notes.this,R.anim.fadein);

        intent2 = getIntent();
        nom = intent2.getStringExtra("nom");
        prenom = intent2.getStringExtra("prenom");
        tv_nomprenom_2 = findViewById(R.id.tv_nomprenom_2);
        tv_nomprenom_2.setText(nom+" "+prenom);

        database = FirebaseDatabase.getInstance();

        user = intent2.getStringExtra("user");
        reference = database.getReference("EtudiantsMIT/"+user+"/Notes");

        //set list view
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Notes notes = dataSnapshot.getValue(Notes.class);

                PutNotesLV(notes.getM1(), notes.getM2(), notes.getM3(),
                notes.getM4(), notes.getM5(), notes.getM6());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back.setAnimation(animation);
                finish();
            }
        });



    }

    public void PutNotesLV(Double M1,Double M2,Double M3,Double M4,Double M5,Double M6){
        iteamNote N1 = new iteamNote("M1",getString(R.string.m1),M1);
        iteamNote N2 = new iteamNote("M2",getString(R.string.m2),M2);
        iteamNote N3 = new iteamNote("M3",getString(R.string.m3),M3);
        iteamNote N4 = new iteamNote("M4",getString(R.string.m4),M4);
        iteamNote N5 = new iteamNote("M5",getString(R.string.m5),M5);
        iteamNote N6 = new iteamNote("M6", getString(R.string.m6),M6);

        ArrayList<iteamNote> list = new ArrayList<>();
        list.add(N1);list.add(N2);list.add(N3);list.add(N4);list.add(N5);list.add(N6);

        ListView listView_notes = findViewById(R.id.list_view_notes);
        ListeViewAdapter adapter = new ListeViewAdapter(this,R.layout.notes_iteam,list);
        listView_notes.setAdapter(adapter);
    }
}
