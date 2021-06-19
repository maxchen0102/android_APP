package com.example.in_class_5_24;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class house_input extends AppCompatActivity {


    int total=0;
    String category,day,month;
    EditText mEditText,mEditTextNumber;
    ListView listView;
    Button mButton,mButton2,mButton3;
    DatabaseReference Db;
    String total_string;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_input);
        mEditText=findViewById(R.id.editTextTextPersonName2);
        listView=findViewById(R.id.listView3);
        mButton=findViewById(R.id.button7);
        mButton2=findViewById(R.id.button9);
        mButton3=findViewById(R.id.button10);
        mEditTextNumber=findViewById(R.id.editTextTextPersonName5);


        //====================================新增資料===============================
        Intent i2= getIntent();
        String s2 = i2.getStringExtra("S");


        category=s2;
        month="6";
        day="28";
        ArrayList<String> keysList = new ArrayList<String>();

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1);
        listView.setAdapter(adapter);


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference Db=firebaseDatabase.getReference("MyDataBase");



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String,String> content=new HashMap<>();

                content.put("name",mEditText.getText().toString()+"-"+mEditTextNumber.getText().toString());
                content.put("money",mEditTextNumber.getText().toString());
                content.put("month","5");
                content.put("day","31");

                String key =Db.push().getKey();
                //push() 是產生一組亂碼 去當父節點
                //getkey()是得到這組亂碼 因為你得往下建立

                //Toast.makeText(house_input.this,key,Toast.LENGTH_LONG).show();

                Db.child(key).setValue(content);

                mEditTextNumber.setText("");

                mEditText.setText(" ");

            }
        });

        //這邊不受 day month 影響 因為 都是使用db 物件 所以找尋的是 db物件最裡面的資料欄 跟父節點的分類無關
        Db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String new_item =String.valueOf(snapshot.child("name").getValue());
                adapter.add(new_item);
                keysList.add(snapshot.getKey());
                //String new_key =String.valueOf(snapshot.getKey());
                //String test_key=String.valueOf(keysList.get(0));

                Toast.makeText(house_input.this,"已新增"+new_item,Toast.LENGTH_LONG).show();

                //Toast.makeText(house_input.this,"已新增"+new_item,Toast.LENGTH_LONG).show();


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //是監聽firebase 有變動的時候 而且是firebase
                keysList.remove(snapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //讓所點擊的listview的值 出現在mEeditText上面
                String item = adapter.getItem(position);
                mEditText.setText(item);

                mButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String item = adapter.getItem(position);
                        String key = keysList.get(position);
                        Db.child(key).removeValue();
                        mEditText.setText(" ");
                        adapter.remove(item);
                        adapter.notifyDataSetChanged();

                    }
                });

            }
        });

         //============================================新增資料

    }






}