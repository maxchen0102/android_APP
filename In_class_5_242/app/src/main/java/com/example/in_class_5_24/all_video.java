package com.example.in_class_5_24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class all_video extends AppCompatActivity {

    WebView webView;
    Spinner mSpinner ;
    String[] video={"無","超高效學生理財方法","最厲害的12種存錢方法"};
    String []url ={"https://www.youtube.com/watch?v=U8X-VHCKXXA",
            "https://www.youtube.com/watch?v=rrjcRcU2t9s"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_video);
        webView=findViewById(R.id.webView);
        mSpinner=findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter=new ArrayAdapter<String >(all_video.this, android.R.layout.simple_spinner_dropdown_item,video);
        mSpinner.setAdapter(adapter);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    if (video[position] == video[1]) {
                        Toast.makeText(all_video.this, "你選的是" + video[1], Toast.LENGTH_LONG).show();

                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl("https://deanlife.blog/college-students-financial-management/");


                    } else if (video[position] == video[2]) {
                        Toast.makeText(all_video.this, "你選的是" + video[2], Toast.LENGTH_LONG).show();



                        Intent intent2 = new Intent(all_video.this, video_play.class);
                        intent2.putExtra("S", "2");
                        startActivity(intent2);


                    }
                    }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void back(View view) {
        Intent i3=new Intent(all_video.this,dictionary.class);
        startActivity(i3);
    }
}