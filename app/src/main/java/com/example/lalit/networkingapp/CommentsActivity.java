package com.example.lalit.networkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {
    ArrayList<Comment> comments;
    CommentsCustomAdapter commentsAdapter;
    ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        comments = new ArrayList<>();
        commentsAdapter = new CommentsCustomAdapter(this,comments);
        ListView commentsListView = (ListView)findViewById(R.id.commentsListView);
        commentsListView.setAdapter(commentsAdapter);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        fetchComments();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void fetchComments() {
        progressBar2.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        int post_id = intent.getIntExtra("post_id",1);
        String urlString = "https://jsonplaceholder.typicode.com/posts/" + post_id +"/comments";
       final CommentAsyncTask commentsAsyncTask = new CommentAsyncTask(new CommentAsyncTask.commentsDownloadListener() {
           @Override
           public void onCommentsDownload(ArrayList<Comment> commentsList) {
                for(int i=0;i<commentsList.size();i++){
                    comments.add(commentsList.get(i));
                }
                progressBar2.setVisibility(View.GONE);
                commentsAdapter.notifyDataSetChanged();
           }
       });
        commentsAsyncTask.execute(urlString);
    }

}
