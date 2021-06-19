package com.example.in_class_5_24;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    ListView listView ;
    EditText editText;
    Button button ;
    String a ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    listView=findViewById(R.id.listView3);
    editText=findViewById(R.id.editTextTextPersonName);
    button=findViewById(R.id.button);



        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1);
        listView.setAdapter(adapter);


        //建立與 FirebaseDatabase 的關係
        //getReference
        //是為了確認對應的樹狀節點。
        a="test";
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(a);
        //using class's function  to make a object

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add random key
                //由於我們不知道 目前的節點使用量，
                //因此會使用
                //push(). getkey 來隨機產生一個名稱。
                String key =databaseReference.push().getKey()+"/name";
            databaseReference.child(key).setValue(editText.getText().toString());
            //可以無限往下搜尋子節點
                // mDatabase.child("users").child(userId).child("username").setValue(name);


            //databaseReference 是我們自己定的物件 是取到“食物”這個父節點
                //所以下面 才用.child 去取得子節點 並輸入 我們產生的random key
                //並用 setValue() 去輸入資料
            // add data to database
                editText.setText(" ");
            }
        });

        // to inspect what data in the database
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.add(String.valueOf(snapshot.child("5").child("31").child("name").getValue()));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}