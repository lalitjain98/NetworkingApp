package com.example.lalit.networkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Post> posts;
    CustomAdapter adapter;
    ProgressBar progressBar;
    ListView postsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts = new ArrayList<>();
        adapter = new CustomAdapter(this,posts);
        postsListView = (ListView)findViewById(R.id.postsListView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        postsListView.setAdapter(adapter);
        postsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,CommentsActivity.class);
                int post_id = posts.get(i).post_id;
                intent.putExtra("post_id",post_id);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                fetchPosts();
                adapter.notifyDataSetChanged();
                Log.d("Posts Data",posts.toString());
            }
        });
    }

    private void fetchPosts() {
        progressBar.setVisibility(View.VISIBLE);
        String urlString = "https://jsonplaceholder.typicode.com/posts";
        PostAsyncTask postAsyncTask = new PostAsyncTask(new PostAsyncTask.postsDownloadListener() {
            @Override
            public void onPostsDownload(ArrayList<Post> postsList) {
                for(int i=0;i<postsList.size();i++){
                    posts.add(postsList.get(i));
                }
                progressBar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();

            }
        });
        postAsyncTask.execute(urlString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
