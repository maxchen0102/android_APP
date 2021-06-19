package com.example.in_class_5_24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class video_play extends AppCompatActivity {
    WebView webView;
    String []url1 ={"https://www.youtube.com/watch?v=U8X-VHCKXXA",

            "https://www.youtube.com/watch?v=rrjcRcU2t9s"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        webView=findViewById(R.id.WebView);

        Intent i2= getIntent();
        String s2 = i2.getStringExtra("S");

        if(s2.equals("2")){

    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl( "https://www.youtube.com/watch?v=rrjcRcU2t9s");
}





    }

    public void back(View view) {


        Intent i3=new Intent(video_play.this,dictionary.class);
        startActivity(i3);
    }
}