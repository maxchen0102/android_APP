package com.example.in_class_5_24;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class content extends AppCompatActivity {


    ListView listView;
    TextView mTextView,mTextView2;
    Spinner mSpinner;
    String total_string;
    String []month_list={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String find_category,find_month,find_day;

    private void showData(String find_month )
    {
        SwipeMenuListView listView=findViewById(R.id.listView3);

        final int[] total = {0};
        ArrayList<String> keysList = new ArrayList<String>();


        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator()
        {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(255, 87, 51)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("刪除");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                deleteItem.setWidth(170);
                deleteItem.setTitle("修改");
                // set a icon
                deleteItem.setIcon(R.drawable.ic_action_name);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("MyDataBase");
        final int[] count = {0};
      databaseReference.addChildEventListener(new ChildEventListener() {

          @Override
          public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

              String month=String.valueOf(snapshot.child("month").getValue());
              if(month.equals(find_month)) {

                  String name=String.valueOf(snapshot.child("name").getValue());
                  adapter.add(name);
                  keysList.add(snapshot.getKey());
                  String s = String.valueOf(snapshot.child("money").getValue());
                  int i = Integer.parseInt(s);


                  total[0] += i;
                  total_string = String.valueOf(total[0]);
                  mTextView.setText(total_string);
                  count[0] +=1;
              }
              if(count[0] ==0){
                  mTextView.setText("0");

              }
          }

          @Override
          public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

          }

          @Override
          public void onChildRemoved(@NonNull DataSnapshot snapshot) {
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


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        //index 是左滑按鍵的總類
                        //position 才是位置


                        String item = (String) adapter.getItem(position);
                        String key = keysList.get(position);
                        databaseReference.child(key).removeValue();
                        adapter.remove(item);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        // delete

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //listView=findViewById(R.id.listView3);
        SwipeMenuListView listView=findViewById(R.id.listView3);
        mTextView=findViewById(R.id.textView10);
        mSpinner=findViewById(R.id.spinner3);
        mTextView2=findViewById(R.id.textView9);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String >(content.this, android.R.layout.simple_spinner_dropdown_item,month_list);
        mSpinner.setAdapter(adapter2);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               find_month=month_list[position];
                showData(find_month);
                mTextView2.setText(find_month+" 月的總支出");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toast.makeText(content.this,find_month,Toast.LENGTH_LONG).show();


    }



}