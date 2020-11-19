package com.example.uts_amub_ti7a_1711500007_andrechandra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class RegisterTwoAct extends AppCompatActivity {
    ImageView pic_photo_register_user;
    Button btn_continue,btn_add_photo;
    EditText hobby, alamat;

    EditText username, password, email;



    DatabaseReference reference;
    String USERNAME_KEY = " usernamekey";
    String username_key = "";

    String username_key_new = "";
    Uri photo_location;
    Integer photo_max = 1;

    StorageReference storage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);



        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

            getUsernameLocal();
        btn_add_photo = findViewById(R.id.btn_add_photo);
        hobby = findViewById(R.id.hobby);
        alamat = findViewById(R.id.alamat);
        //email = findViewById(R.id.email);
         pic_photo_register_user = findViewById(R.id.pic_photo_register_user);
        btn_continue = findViewById(R.id.btn_continue);
        //back = findViewById(R.id.login);

        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhoto();
            }
        });


        //berpindah dari activity lain ke activity registered
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users/Andre");
                storage = FirebaseStorage.getInstance().getReference().child("Photousers/Andre Chandra");

/*
                private void uploadImage() {

                    if(photo_location != null)
                    {
    //filepath = photo_location

                        StorageReference ref = storage.child("images/"+ UUID.randomUUID().toString());
                        ref.putFile(photo_location)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                                .getTotalByteCount());
                                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                                    }
                                });
                    }
                } */







                //validasi utk file apakah ada atau tidak
                if(photo_location != null) {
                    StorageReference storageReference1 =
                            storage.child(System.currentTimeMillis() + "."+
                                    getFileExtension(photo_location));
                    storageReference1.putFile(photo_location)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    //String uri_photo = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                                    String uri_photo = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                                    //reference.getRef().child("username").setValue(username.getText().toString());
                                   // reference.getRef().child("password").setValue(password.getText().toString());
                                    //reference.getRef().child("email").setValue(email.getText().toString());
                                    //reference.getRef().child("url_photo_profile").setValue(uri_photo);
                                    reference.getRef().child("hobby").setValue(hobby.getText().toString());
                                    reference.getRef().child("alamat").setValue(alamat.getText().toString());
                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Intent gotoregister2register = new Intent(RegisterTwoAct.this, LoginAct.class);
                            startActivity(gotoregister2register);
                        }
                    });
                }




            }
        }

        );




 /*


        //menyimpan data kepada localstorage (handphone)
        SharedPreferences sharedPreferences =getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username_key,hotel.getText().toString()); //mengambil data inputan username disimpan ke variabel username_key
        editor.apply();

        //proses SIMPAN KE DATABASE FIREBASE
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(hotel.getText().toString());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("hotel").setValue(hotel.getText().toString());
                dataSnapshot.getRef().child("alamat").setValue(alamat.getText().toString());
                //dataSnapshot.getRef().child("email").setValue(email.getText().toString());
                //dataSnapshot.getRef().child("saldo").setValue(100000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //berpindah dari activity lain ke activity loginact
        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotologin = new Intent(RegisterAct.this, RegisterTwoAct.class);
                startActivity(gotologin);
            }
        }); */
















    }

    String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void findPhoto() {
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }

    //kita membutuhkan library picasso utk menampilkan gambar menambahkan di gradle app
    //membuat activity baru dengan cara generate (klik kanan, generate)

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == photo_max && resultCode == RESULT_OK && data!= null && data.getData() != null) {
            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(pic_photo_register_user);
        }
    }


}
