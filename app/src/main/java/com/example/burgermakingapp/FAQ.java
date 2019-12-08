package com.example.burgermakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class FAQ extends AppCompatActivity {
    private final LinkedList<String> mFAQlist = new LinkedList<>();
    private int mCount = 0;

    FAQListAdapter mAdapter;
    RecyclerView mRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        mFAQlist.addLast("1. How to make an order ?");
        mFAQlist.addLast("2. Can I cancel my order ?");
        mFAQlist.addLast("3. How to view my order status?");
        mFAQlist.addLast("4. How long do I need to wait for my order ?");
        mFAQlist.addLast("5. Any hidden cost for my order ?");


        mRV = (RecyclerView)findViewById(R.id.rv_recycleview);

        mAdapter = new FAQListAdapter(this,mFAQlist);
        mRV.setAdapter(mAdapter);
        mRV.setLayoutManager(new LinearLayoutManager(this));

    }
}
