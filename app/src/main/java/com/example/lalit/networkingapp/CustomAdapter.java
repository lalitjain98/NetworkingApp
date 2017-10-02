package com.example.lalit.networkingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jainl on 01-10-2017.
 */

public class CustomAdapter extends ArrayAdapter{
    public Context mContext;
    public ArrayList<Post> mPosts;
    public CustomAdapter(@NonNull Context context, ArrayList<Post> posts) {
        super(context,0);
        mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_row_layout,null);
            Post post = mPosts.get(position);
            TextView postTitleView = convertView.findViewById(R.id.postTitleView);
            TextView postBodyView = convertView.findViewById(R.id.postBodyView);
            postTitleView.setText(post.title);
            postBodyView.setText(post.body);
            ViewHolder holder = new ViewHolder();
            holder.postTitleView = postTitleView;
            holder.postBodyView = postBodyView;
            convertView.setTag(holder);
        }
        ViewHolder holder = ((ViewHolder) convertView.getTag());
        Post post = mPosts.get(position);
        holder.postTitleView.setText(post.title);
        holder.postBodyView.setText(post.body);
        return convertView;
    }
    public class ViewHolder{
        TextView postTitleView,postBodyView;
    }
    @Override
    public int getCount() {
        return mPosts.size();
    }
}
