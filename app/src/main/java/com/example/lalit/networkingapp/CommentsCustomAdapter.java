package com.example.lalit.networkingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by jainl on 01-10-2017.
 */

public class CommentsCustomAdapter extends ArrayAdapter{
    public Context mContext;
    public ArrayList<Comment> mComments;

    public CommentsCustomAdapter(@NonNull Context context, ArrayList<Comment> comments) {
        super(context,0);
        mComments = comments;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_row_layout,null);
            Comment comment = mComments.get(position);
            TextView commentNameView = convertView.findViewById(R.id.commentNameView);
            TextView commentEmailView = convertView.findViewById(R.id.commentEmailView);
            TextView commentBodyView = convertView.findViewById(R.id.commentBodyView);
            commentNameView.setText(comment.name);
            commentEmailView.setText(comment.email);
            commentBodyView.setText(comment.body);

            CommentsCustomAdapter.ViewHolder holder = new CommentsCustomAdapter.ViewHolder();
            holder.commentNameView = commentNameView;
            holder.commentEmailView = commentEmailView;
            holder.commentBodyView = commentBodyView;
            convertView.setTag(holder);
        }
        CommentsCustomAdapter.ViewHolder holder = ((CommentsCustomAdapter.ViewHolder) convertView.getTag());
        Comment comment = mComments.get(position);
        holder.commentNameView.setText(comment.name);
        holder.commentEmailView.setText(comment.email);
        holder.commentBodyView.setText(comment.body);
        return convertView;
    }
    public class ViewHolder{
        TextView commentNameView,commentEmailView,commentBodyView;
    }
    @Override
    public int getCount() {
        return mComments.size();
    }
}
