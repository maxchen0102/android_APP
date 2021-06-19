package com.example.in_class_5_24;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class enter extends AppCompatActivity {

    Button mButton,mButton2,mButton3,mButton4;
    TextView mTextView ;
    CalendarView mCalendarView;
    private Calendar c = Calendar.getInstance(); //得到日曆資訊
    EditText mEditText,mEditText2 ;
    String select_category,select_month,select_day;
    public static final int RQS_VOICE_RECOGNITION=1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        //=======================連接XML================================
        mButton=findViewById(R.id.button15);//更多功能
        mButton2=findViewById(R.id.button13); //選分類
        mButton3=findViewById(R.id.button11); //確定新增
        mButton4=findViewById(R.id.button14);//語音輸入
        mTextView=findViewById(R.id.textView14); //顯示本月支出
        mCalendarView=findViewById(R.id.calendarView); //日歷
        mEditText=findViewById(R.id.editTextTextPersonName3);
        mEditText2=findViewById(R.id.editTextTextPersonName4);
    //====================================================================
     //==================================

        //==========得到分類==================================
        Intent i2= getIntent();
        String s2 = i2.getStringExtra("S");
        //==========得到分類==================================
        //==========得到日期參數==================================

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               select_day = String.valueOf(dayOfMonth);
                select_month=String.valueOf(month+1);


        Toast.makeText(enter.this,select_day,Toast.LENGTH_LONG).show();
        select_category=s2;
        //select_month="6";
        //select_day="28";
        //==========得到日期參數==================================

        //====================================新增資料===============================
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference Db=firebaseDatabase.getReference("MyDataBase");
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> content=new HashMap<>();
                content.put("name",mEditText.getText().toString()+"-"+mEditText2.getText().toString());
                content.put("money",mEditText2.getText().toString());
                content.put("category",select_category);// 存入選擇的分類項目
                content.put("month",select_month);
                content.put("day",select_day);
                String key =Db.push().getKey();//push() 是產生一組亂碼 去當父節點,getkey()是得到這組亂碼 因為你得往下建立
                Db.child(key).setValue(content);
                mEditText2.setText("");
                mEditText.setText(" ");
            }
        });
        //============================================新增資料=============================================
                //============================================語音輸入=============================================


                mButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"START Speech");
                        startActivityForResult(intent,RQS_VOICE_RECOGNITION);

                    }
                });
                //============================================語音輸入=============================================

    }

});
    } //create_end
    //============================================語音輸入=============================================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RQS_VOICE_RECOGNITION){
            if(resultCode ==RESULT_OK){
                ArrayList<String> result=
                        data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String firstMatch=result.get(0);
                mEditText.setText(firstMatch);
            }

        }
    }

    //============================================語音輸入=============================================

    //按下 去更多分類頁面
     public void to_more(View view) {
        Intent intent =new Intent(enter.this,dictionary.class);
        startActivity(intent);
    }
    //按下去分類頁面

    public void to_category(View view) {
        Intent intent2 =new Intent(enter.this,category.class);
        startActivity(intent2);
    }

    //=======================life_cycle==========================================
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