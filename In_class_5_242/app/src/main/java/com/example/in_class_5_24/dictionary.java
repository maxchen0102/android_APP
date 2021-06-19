package com.example.in_class_5_24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dictionary extends AppCompatActivity {
        Button mButton,mButton2,mButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        
        mButton=findViewById(R.id.button4);
        mButton2=findViewById(R.id.button5);
        mButton3=findViewById(R.id.button6);




    }


    //開啟讀取儲存內容畫面
    public void showcontent(View view) {
        Intent intent2=new Intent(dictionary.this,content.class);
        startActivity(intent2);
    }
    public void show_video(View view) {
        Intent intent3 =new Intent(dictionary.this,all_video.class);
        startActivity(intent3);
    }


    //  開啟影片內容畫面

}