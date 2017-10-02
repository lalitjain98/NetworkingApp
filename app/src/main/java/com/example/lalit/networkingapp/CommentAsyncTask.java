package com.example.lalit.networkingapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jainl on 01-10-2017.
 */

public class CommentAsyncTask extends AsyncTask<String ,Void, ArrayList<Comment>>{

    public CommentAsyncTask.commentsDownloadListener onDownloadListener;

    public CommentAsyncTask(CommentAsyncTask.commentsDownloadListener listener) {
        onDownloadListener = listener;
    }

    @Override
    protected ArrayList<Comment> doInBackground(String... strings) {
        ArrayList<Comment> comments = new ArrayList<>();
        String stringURL = strings[0];
        String response = "";
        try {
            URL url = new URL(stringURL);
            HttpURLConnection httpURLConnection = ((HttpURLConnection) url.openConnection());
            httpURLConnection.setRequestMethod("GET");
            Log.d("Comment Connection","Connection start");
            httpURLConnection.connect();
            Log.d("Comment Connection","Connection made");
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scan = new Scanner(inputStream);
            while (scan.hasNext()) {
                response += scan.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        comments = parseResponse(response);
        return comments;
    }

    private ArrayList<Comment> parseResponse(String response) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            JSONArray rootObjectsArray = new JSONArray(response);
            for(int i=0;i<rootObjectsArray.length();i++){
                JSONObject comment = rootObjectsArray.getJSONObject(i);
                int post_id = comment.getInt("postId");
                int comment_id = comment.getInt("id");
                String name = comment.getString("name");
                String email = comment.getString("email");
                String body = comment.getString("body");

                Comment newComment = new Comment(post_id,comment_id,name,email,body);
                comments.add(newComment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Comment> comments) {
        super.onPostExecute(comments);
        onDownloadListener.onCommentsDownload(comments);
    }

    public interface commentsDownloadListener {
        void onCommentsDownload(ArrayList<Comment> comments);
    }
}
