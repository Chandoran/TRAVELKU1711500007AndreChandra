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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAct extends AppCompatActivity {
    ImageView wut;
    Button login2,register;
    EditText username2, password2;

    DatabaseReference reference;
    String USERNAME_KEY = " usernamekey";
    String username_key = "";



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

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login2.setEnabled(false);
                login2.setText("Loading ...");
                final  String username = username2.getText().toString();
                final String password = password2.getText().toString();

                if(username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username kosong!", Toast.LENGTH_SHORT).show();
                    //ubah state menjadi loading
                    login2.setEnabled(true);
                    login2.setText("SIGN IN");
                }
                else {
                    if (password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Password kosong!", Toast.LENGTH_SHORT).show();
                        login2.setEnabled(true);
                        login2.setText("SIGN IN");
                    }
                    else {
                        reference =  FirebaseDatabase.getInstance().getReference().child("User/Andre");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    //ambil data password dari firebase
                                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                                     //validasi  password dgn password firebase
                                    if (password.equals(passwordFromFirebase)){
                                        //simpan username (key) kepada local
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, username2.getText().toString());
                                        editor.apply();
                                        //berpindah activity
                                        Intent gotohome = new Intent(LoginAct.this,HomeActivity.class);
                                        startActivity(gotohome);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Password salah", Toast.LENGTH_SHORT).show();
                                        //ubah state menjadi loading
                                        login2.setEnabled(true);
                                        login2.setText("SIGN IN");
                                    }

                                    }
                                /*else {
                                    Toast.makeText(getApplicationContext(), "Username tidak ada", Toast.LENGTH_SHORT).show();
                                    //ubah state menjadi loading
                                    login2.setEnabled(true);
                                    login2.setText("SIGN IN");
                                } */
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

    }
}
