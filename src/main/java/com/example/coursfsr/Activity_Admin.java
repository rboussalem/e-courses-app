package com.example.coursfsr;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Admin extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    Spinner sp_etudiants;
    ArrayAdapter<String> adapter;

    EditText ed_note_m1,ed_note_m2,ed_note_m3,ed_note_m4,ed_note_m5,ed_note_m6;
    Etudiant etudiant;
    ArrayList<String> users,users2;

    Button btn_modefier;
    String userGold;

    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin);

        animation = AnimationUtils.loadAnimation(Activity_Admin.this,R.anim.fadein);

        sp_etudiants = findViewById(R.id.sp_etudiants);
        ed_note_m1 = findViewById(R.id.ed_note_m1);
        ed_note_m2 = findViewById(R.id.ed_note_m2);
        ed_note_m3 = findViewById(R.id.ed_note_m3);
        ed_note_m4 = findViewById(R.id.ed_note_m4);
        ed_note_m5 = findViewById(R.id.ed_note_m5);
        ed_note_m6 = findViewById(R.id.ed_note_m6);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("EtudiantsMIT");

        users = new ArrayList<>();
        users2 = new ArrayList<>();

        PutDataSpi();
        PutDataNot();

        btn_modefier = findViewById(R.id.btn_modefier);
        btn_modefier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_modefier.setAnimation(animation);

                reference.child(userGold).child("Notes").child("m1").setValue(Double.parseDouble(ed_note_m1.getText().toString()));
                reference.child(userGold).child("Notes").child("m2").setValue(Double.parseDouble(ed_note_m2.getText().toString()));
                reference.child(userGold).child("Notes").child("m3").setValue(Double.parseDouble(ed_note_m3.getText().toString()));
                reference.child(userGold).child("Notes").child("m4").setValue(Double.parseDouble(ed_note_m4.getText().toString()));
                reference.child(userGold).child("Notes").child("m5").setValue(Double.parseDouble(ed_note_m5.getText().toString()));
                reference.child(userGold).child("Notes").child("m6").setValue(Double.parseDouble(ed_note_m6.getText().toString()));

                Toast.makeText(getApplicationContext(),"Le processus est terminee",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void PutDataNot() {
        sp_etudiants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userGold = users2.get(position);
                        Notes notesA = dataSnapshot.child(users2.get(position)).child("Notes").getValue(Notes.class);
                        //Toast.makeText(getApplicationContext(),notesA.getM1()+"",Toast.LENGTH_LONG).show();
                        ed_note_m1.setText(notesA.getM1()+"");
                        ed_note_m2.setText(notesA.getM2()+"");
                        ed_note_m3.setText(notesA.getM3()+"");
                        ed_note_m4.setText(notesA.getM4()+"");
                        ed_note_m5.setText(notesA.getM5()+"");
                        ed_note_m6.setText(notesA.getM6()+"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void PutDataSpi() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    etudiant = postSnapshot.getValue(Etudiant.class);
                    users.add(etudiant.getNom()+" "+etudiant.getPrenom());
                    users2.add(etudiant.getUsername());

                }


                adapter = new ArrayAdapter<String>(Activity_Admin.this,android.R.layout.simple_spinner_dropdown_item,users);
                sp_etudiants.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
