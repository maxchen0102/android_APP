package com.example.in_class_5_24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class category extends AppCompatActivity {
    ImageButton mImageButton1,mImageButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mImageButton1=findViewById(R.id.imageButton);
        mImageButton2=findViewById(R.id.imageButton2);


    }

    public void house_implement(View view) {
        Intent intent1 =new Intent(category.this,enter.class);
        intent1.putExtra("S", "house_use");


        startActivity(intent1);
    }

    public void out_food(View view) {
        Intent intent2=new Intent(category.this,enter.class);
        intent2.putExtra("S", "out_food");

        startActivity(intent2);
    }
//===============================life_circle=================================================
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

