package com.example.burgermakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class FAQListAdapter extends RecyclerView.Adapter<FAQListAdapter.FAQViewHolder> {
    private LinkedList<String> mFAQList;
    private LayoutInflater mInflater;

    class FAQViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mFAQListItem;
        final FAQListAdapter mAdapter;

        FAQViewHolder (View viewItem, FAQListAdapter adapter){
            super(viewItem);
            mFAQListItem = (TextView)viewItem.findViewById(R.id.FAQ);
            mAdapter = adapter;
            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String content = mFAQList.get(position);
            String answer1 = "\tSelect your choice and then submit it";
            String answer2 = "\tCan, just press submit and then it will show ok or cancel, just press cancel";
            String answer3 = "\tPress submit and it will show the status of your order";
            String answer4 = "\t3 sec";
            String answer5 = "\tNope";

            switch (position) {
                case 0:
                    if (content.contains(answer1)){
                        mFAQList.set(position,"1. How to make an order ?");
                        mAdapter.notifyDataSetChanged();
                    }else{
                        mFAQList.set (position, content + "\n" + answer1);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;

                case 1:
                    if (content.contains(answer2)){
                        mFAQList.set(position,"2. Can I cancel my order ?");
                        mAdapter.notifyDataSetChanged();
                    }else{
                        mFAQList.set (position, content + "\n" + answer2);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;

                case 2:
                    if (content.contains(answer3)){
                        mFAQList.set(position,"3. How to view my order status?");
                        mAdapter.notifyDataSetChanged();
                    }else{
                        mFAQList.set (position, content + "\n" + answer3);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;

                case 3:
                    if (content.contains(answer4)){
                        mFAQList.set(position,"4. How long do I need to wait for my order ?");
                        mAdapter.notifyDataSetChanged();
                    }else{
                        mFAQList.set (position, content + "\n" + answer4);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;

                case 4:
                    if (content.contains(answer5)){
                        mFAQList.set(position,"5. Any hidden cost for my order ?");
                        mAdapter.notifyDataSetChanged();
                    }else{
                        mFAQList.set (position, content + "\n" + answer5);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    public FAQListAdapter(Context context, LinkedList<String> FAQlist){

        mInflater = LayoutInflater.from(context);
        mFAQList = FAQlist;
    }
    @NonNull
    @Override
    public FAQListAdapter.FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mItemView = mInflater.inflate(R.layout.faq_list_layout,parent,false);

        return new FAQViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQListAdapter.FAQViewHolder FAQViewHolder, int i) {
        String mCurrent = mFAQList.get(i);
        FAQViewHolder.mFAQListItem.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mFAQList.size();
    }


}
