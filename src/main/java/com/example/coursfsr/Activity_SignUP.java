package com.example.coursfsr;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_SignUP extends AppCompatActivity {

    private Button btn_signup;
    private EditText ed_user,ed_email,ed_password,ed_nom,ed_prenom,ed_ce;

    //private FirebaseAuth mAuth;

    FirebaseDatabase Database ;
    DatabaseReference users;

    Animation animation;
    Etudiant etudiant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_up);

        animation = AnimationUtils.loadAnimation(Activity_SignUP.this,R.anim.fadein);

        btn_signup = findViewById(R.id.btn_signup);
        ed_user = findViewById(R.id.ed_user);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_nom = findViewById(R.id.ed_nom);ed_nom.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        ed_prenom = findViewById(R.id.ed_prenom);ed_prenom.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        ed_ce = findViewById(R.id.ed_ce);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_signup.setAnimation(animation);
                /*
                mAuth = FirebaseAuth.getInstance();
                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password);
                */

                 etudiant = new Etudiant(
                                ed_user.getText().toString(),
                                ed_password.getText().toString(),
                                ed_email.getText().toString(),
                                ed_nom.getText().toString(),
                                ed_prenom.getText().toString(),
                                ed_ce.getText().toString());

                Database = FirebaseDatabase.getInstance();
                users = Database.getReference("EtudiantsMIT");
                //users.setValue("Hello, World!");


                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    Notes notes = new Notes(99.9,99.9,99.9,99.9,99.9,99.9);
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etudiant.getUsername()).exists()){
                            Toast.makeText(getApplicationContext(),"L'etudiant deja existe", Toast.LENGTH_LONG).show();
                            //Etudiant login = dataSnapshot.child(etudiant.getUsername()).getValue(Etudiant.class);
                            //Toast.makeText(getApplicationContext(),login.getEmail()+":::"+login.getUsername(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            users.child(etudiant.getUsername()).setValue(etudiant);
                            users.child(etudiant.getUsername()).child("Notes").setValue(notes);
                            Toast.makeText(getApplicationContext(),"success", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //Toast.makeText(getApplicationContext(),etudiant.getEmail(), Toast.LENGTH_LONG).show();


            }
        });



    }
}
