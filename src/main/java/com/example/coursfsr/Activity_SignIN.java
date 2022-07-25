package com.example.coursfsr;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_SignIN extends AppCompatActivity {
    Animation animation;

    EditText ed_user_in,ed_password_in;
    Button btn_signin,btn_signup_in;

    FirebaseDatabase database;
    DatabaseReference users;

    public String user,pwd;

    ProgressBar progressBar;

    Dialog dialog;
    TextView tv_message;
    Button btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_in);

        animation = AnimationUtils.loadAnimation(Activity_SignIN.this,R.anim.fadein);
        dialog = new Dialog(this);

        ed_user_in = findViewById(R.id.ed_user_in);
        ed_password_in = findViewById(R.id.ed_password_in);
        btn_signin = findViewById(R.id.btn_signin);
        btn_signup_in = findViewById(R.id.btn_signup_in);
        progressBar = findViewById(R.id.progressBar);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("EtudiantsMIT");



        //Toast.makeText(getApplicationContext(),user, Toast.LENGTH_LONG).show();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_signin.setAnimation(animation);
                progressBar.setVisibility(View.VISIBLE);
                user = ed_user_in.getText().toString();
                pwd = ed_password_in.getText().toString();
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user).exists()){
                            if (!user.isEmpty()){
                                Etudiant login = dataSnapshot.child(user).getValue(Etudiant.class);
                                if(login.getPassword().equals(pwd)){
                                    if(login.getUsername().equals("admin")){

                                        Intent intent = new Intent(Activity_SignIN.this,Activity_Admin.class);
                                        startActivity(intent);
                                        progressBar.setVisibility(View.INVISIBLE);

                                        //showDialog_1(" login successful!");

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Login ok", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Activity_SignIN.this,MainActivity.class);
                                        intent.putExtra("user",user);
                                        intent.putExtra("nom",login.getNom());
                                        intent.putExtra("prenom",login.getPrenom());
                                        intent.putExtra("ce",login.getCE());
                                        intent.putExtra("email",login.getEmail());
                                        startActivity(intent);
                                        progressBar.setVisibility(View.INVISIBLE);

                                    }

                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Password incorrect", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    showDialog_1("Password incorrect!");
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Entree votre username ", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                showDialog_1("Entree votre UserName!");

                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"L'etudiant n'existe pas ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            showDialog_1("UserName incorrect!");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btn_signup_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_signup_in.setAnimation(animation);
                Intent intent = new Intent(Activity_SignIN.this,Activity_SignUP.class);
                startActivity(intent);
            }
        });

    }

    private void showDialog_1(String message) {

        dialog.setContentView(R.layout.dialog_login);
        btn_ok = dialog.findViewById(R.id.btn_ok);
        tv_message = dialog.findViewById(R.id.tv_message);
        tv_message.setText(message);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        /*
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_account_circle_black_24dp)
                .setTitle("Bonjour")
                .setMessage("niko koo")
                .setNegativeButton("ok",null)
                .show();
        */
    }


}
