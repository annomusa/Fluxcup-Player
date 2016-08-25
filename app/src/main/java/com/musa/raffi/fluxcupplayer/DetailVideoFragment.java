package com.musa.raffi.fluxcupplayer;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * Created by Asus on 8/24/2016.
 */

public class DetailVideoFragment extends Fragment {
    private ArrayList<String> mVideoList;
    private String mCurrentVideo;
    private String mUrlTitle;
    private String mUrlComment;

    public static HashMap<String, String> detailVideo;
    public static ArrayList<HashMap<String, String>> commentsVideo;

    private static final String TAG_ITEMS = "items";
    private static final String TAG_SNIPPET = "snippet";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_CHANNEL_TITLE = "channelTitle";

    private static final String TAG_TOP = "topLevelComment";
    private static final String TAG_AUTHOR = "authorDisplayName";
    private static final String TAG_COMMENT = "textDisplay";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mVideoList = VideoList.getInstance().getVideo();
        int position = (int) getArguments().getInt("Position");
        mCurrentVideo = mVideoList.get(position);
        mUrlTitle = "https://www.googleapis.com/youtube/v3/videos?key=" + Resource.KEY  + "&part=snippet&id=" + mCurrentVideo;
        mUrlComment = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=" + mCurrentVideo +"&key=" + Resource.KEY;

        try {
            new GetDetail().execute().get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public static DetailVideoFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt("Position", position);

        DetailVideoFragment fragment = new DetailVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.detail_video, container, false);
        TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) view.findViewById(R.id.txtDetail);
        ListView list_comment = (ListView) view.findViewById(R.id.list_comment);

        txtTitle.setText(detailVideo.get(TAG_TITLE));
        txtDescription.setText(detailVideo.get(TAG_DESCRIPTION));

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                commentsVideo,
                R.layout.list_comment,
                new String[]{
                        TAG_AUTHOR,
                        TAG_COMMENT
                },
                new int[]{
                        R.id.txtAuthor,
                        R.id.txtComment
                });
        list_comment.setAdapter(adapter);
        return view;
    }

    private class GetDetail extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();

            // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(mUrlTitle, WebRequest.GET);
            String jsonStr2 = webreq.makeWebServiceCall(mUrlComment, WebRequest.GET);

            Log.d("Response: ", "> " + jsonStr);
            Log.d("Response2: ", "> " + jsonStr2);

            detailVideo = ParseJSON(jsonStr);
            commentsVideo = ParseJSONComment(jsonStr2);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.d("Hashmap", "onPostExe: >" +  detailVideo.get(TAG_TITLE) + detailVideo.get(TAG_DESCRIPTION));
        }
    }

    private HashMap<String, String> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView

                JSONObject jsonObj = new JSONObject(json);

                JSONArray items2 = jsonObj.getJSONArray(TAG_ITEMS);
                JSONObject item = items2.getJSONObject(0);
                JSONObject snippet = item.getJSONObject(TAG_SNIPPET);
                String title = snippet.getString(TAG_TITLE);
                String description = snippet.getString(TAG_DESCRIPTION);
                String channelTitle = snippet.getString(TAG_CHANNEL_TITLE);

                HashMap<String, String> detail = new HashMap<String, String>();

                detail.put(TAG_TITLE, title);
                detail.put(TAG_DESCRIPTION, description);
                detail.put(TAG_CHANNEL_TITLE, channelTitle);

                Log.d("Hashmap", "parse: >" +  title + description);

                return detail;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }

    private ArrayList<HashMap<String,String>> ParseJSONComment(String json){
        if(json != null){
            try{
                ArrayList<HashMap<String,String>> commentList = new ArrayList<HashMap<String,String>>();
                JSONObject jsonObj = new JSONObject(json);

                JSONArray items = jsonObj.getJSONArray(TAG_ITEMS);
                for (int i=0;i<items.length(); i++){
                    JSONObject item = items.getJSONObject(i);
                    JSONObject snippet = item.getJSONObject(TAG_SNIPPET);
                    JSONObject topLevel = snippet.getJSONObject(TAG_TOP);
                    JSONObject snippet2 = topLevel.getJSONObject(TAG_SNIPPET);

                    String author = snippet2.getString(TAG_AUTHOR);
                    String comment = snippet2.getString(TAG_COMMENT);

                    HashMap<String, String> commentDetail = new HashMap<>();

                    commentDetail.put(TAG_AUTHOR, author);
                    commentDetail.put(TAG_COMMENT, comment);

                    Log.d("Arraylist", "parse: >" +  author + comment);

                    commentList.add(commentDetail);
                }
                return commentList;

            } catch (JSONException e){
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }
}
