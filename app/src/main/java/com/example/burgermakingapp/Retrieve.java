package com.example.burgermakingapp;

import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Retrieve extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reff;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    OrderInfo orderInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        orderInfo = new OrderInfo();
        listView = (ListView)findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        reff = database.getReference("OrderInfo");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_info, R.id.userInfo, list);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    orderInfo = ds.getValue(OrderInfo.class);
                    list.add("BurgerType: " + orderInfo.getBurgertype().toString());
                    list.add("Doneness: " + orderInfo.getDoneness().toString());
                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
