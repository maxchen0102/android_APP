package com.example.in_class_5_24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class login extends AppCompatActivity {


    Button mButton1 ,mButton2 ;
    EditText mEditText1 ,mEditText2;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;


    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mButton1=findViewById(R.id.button2);
        mButton2=findViewById(R.id.button3);
        mEditText1=findViewById(R.id.editTextTextEmailAddress);
        mEditText2=findViewById(R.id.editTextNumberPassword);

        Uri uri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/b0843039.appspot.com/o/9convert.com%20-%2010%20minutes%20relaxing%20piano%20music%20will%20stay%20%20by%20Peder%20B%20Helland.mp3?alt=media&token=c96e0b35-72d8-432a-9d4a-58429402b97b");


        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
        try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(login.this,uri);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        auth =FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //do something when state change
                FirebaseUser user =firebaseAuth.getCurrentUser();
                //get current account and password
            }
        };

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to compare to justify

                String email =mEditText1.getText().toString();//get the text enter ;
                String password =mEditText2.getText().toString();

                // will compare by it self

                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // do something when the user has been justified
                        Toast.makeText(login.this,"登入成功",Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(login.this,dictionary.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //do something when user got failure
                        Toast.makeText(login.this,"登入失敗",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =mEditText1.getText().toString();//get the text enter ;
                String password =mEditText2.getText().toString();
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(login.this,"註冊成功",Toast.LENGTH_LONG).show();
                         }else{
                             Toast.makeText(login.this,"註冊失敗",Toast.LENGTH_LONG).show();
                         }
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}