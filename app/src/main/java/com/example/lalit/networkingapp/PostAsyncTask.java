package com.example.lalit.networkingapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jainl on 01-10-2017.
 */

public class PostAsyncTask extends AsyncTask<String,Void,ArrayList<Post>> {
    public postsDownloadListener onDownloadListener;
    public PostAsyncTask(postsDownloadListener listener) {
        onDownloadListener = listener;
    }

    @Override
    protected ArrayList<Post> doInBackground(String... strings) {
        ArrayList<Post> posts = new ArrayList<>();
        String stringURL = strings[0];
        String response = "";
        try {
            URL url = new URL(stringURL);
            HttpURLConnection httpURLConnection = ((HttpURLConnection) url.openConnection());
            httpURLConnection.setRequestMethod("GET");
            Log.d("Post Connection","Connection start");
            httpURLConnection.connect();
            Log.d("Post Connection","Connection made");
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scan = new Scanner(inputStream);
            while (scan.hasNext()) {
                response += scan.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        posts = parseResponse(response);
        return posts;
    }

    private ArrayList<Post> parseResponse(String response) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            JSONArray rootObjectsArray = new JSONArray(response);
            for(int i=0;i<rootObjectsArray.length();i++){
                JSONObject post = rootObjectsArray.getJSONObject(i);
                int post_id = post.getInt("id");
                String title = post.getString("title");
                String body = post.getString("body");
                Post newPost = new Post(post_id,title,body);
                posts.add(newPost);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        super.onPostExecute(posts);
        onDownloadListener.onPostsDownload(posts);
    }

    public interface postsDownloadListener {
        void onPostsDownload(ArrayList<Post> posts);
    }
}