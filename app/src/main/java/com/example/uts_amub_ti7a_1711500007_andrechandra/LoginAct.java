package com.example.uts_amub_ti7a_1711500007_andrechandra;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAct extends AppCompatActivity {
    ImageView wut;
    Button login2,register;
    EditText username2, password2;

    //DatabaseReference reference;
    //String USERNAME_KEY = " usernamekey";
    //String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username2 = findViewById(R.id.username2);
        password2 = findViewById(R.id.password2);
        //email = findViewById(R.id.email);

        login2 = findViewById(R.id.login2);
        register = findViewById(R.id.register1);

        //berpindah dari login ke register page
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotologin2register = new Intent(LoginAct.this, RegisterAct.class);
                startActivity(gotologin2register);
            }
        });

 /*
        //menyimpan data kepada localstorage (handphone)
        SharedPreferences sharedPreferences =getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username_key,username2.getText().toString()); //mengambil data inputan username disimpan ke variabel username_key
        editor.apply();

        //proses SIMPAN KE DATABASE FIREBASE
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username2.getText().toString());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("username").setValue(username2.getText().toString());
                dataSnapshot.getRef().child("password").setValue(password2.getText().toString());
                //dataSnapshot.getRef().child("email").setValue(email.getText().toString());
                dataSnapshot.getRef().child("saldo").setValue(100000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //berpindah dari activity lain ke activity loginact
        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotologin = new Intent(RegisterAct.this, RegisterTwoAct.class);
                startActivity(gotologin);
            }
        }); */

    }
}
