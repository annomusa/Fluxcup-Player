package com.musa.raffi.fluxcupplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.musa.raffi.fluxcupplayer.models.comment.CommentList;
import com.musa.raffi.fluxcupplayer.models.comment.Item;
import com.musa.raffi.fluxcupplayer.models.detail.DetailVideo;
import com.musa.raffi.fluxcupplayer.networking.RestApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asus on 8/24/2016.
 */

public class DetailVideoFragment extends Fragment {
    private ArrayList<String> mVideoList;
    private String mCurrentVideo;
    private String mUrlTitle;
    private String mUrlComment;

    public static final String ROOT_URL = "https://www.googleapis.com/youtube/v3/";

    private static final String TAG_AUTHOR = "authorDisplayName";
    private static final String TAG_COMMENT = "textDisplay";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mVideoList = VideoList.getInstance().getVideo();
        int position = getArguments().getInt("Position");
        mCurrentVideo = mVideoList.get(position);

        mUrlTitle = "https://www.googleapis.com/youtube/v3/videos?key=" + Resource.KEY  + "&part=snippet&id=" + mCurrentVideo;
        mUrlComment = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=" + mCurrentVideo +"&key=" + Resource.KEY;
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
        final TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        final TextView txtDescription = (TextView) view.findViewById(R.id.txtDetail);
        final ListView list_comment = (ListView) view.findViewById(R.id.list_comment);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // get detail video
        RestApi service = retrofit.create(RestApi.class);

        Call<DetailVideo> call = service.getDetailVideo(mUrlTitle);
        call.enqueue(new Callback<DetailVideo>() {
            @Override
            public void onResponse(Call<DetailVideo> call, Response<DetailVideo> response) {
                String title = response.body().getItems().get(0).getSnippet().getTitle();
                String description = response.body().getItems().get(0).getSnippet().getTitle();

                txtTitle.setText(title);
                txtDescription.setText(description);
            }

            @Override
            public void onFailure(Call<DetailVideo> call, Throwable t) {

            }
        });

        // get comment list
        final ArrayList<HashMap<String, String>> commentList = new ArrayList<>();
        RestApi service2 = retrofit.create(RestApi.class);
        Call<CommentList> call2 = service2.getCommentVideo(mUrlComment);

        call2.enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                List<Item> items;
                items = response.body().getItems();
                if(items.size() > 0){
                    for (int i=0; i<items.size(); i++){
                        String author = items.get(i).getSnippet()
                                .getTopLevelComment()
                                .getSnippet()
                                .getAuthorDisplayName();
                        String comment = items.get(i).getSnippet()
                                .getTopLevelComment()
                                .getSnippet()
                                .getTextDisplay();

                        HashMap<String, String> commentDetail = new HashMap<>();
                        commentDetail.put(TAG_AUTHOR, author);
                        commentDetail.put(TAG_COMMENT, comment);
                        commentList.add(commentDetail);

                        Log.d("req comment", "onResponse: " + author + comment);
                    }
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                        commentList,
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

            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {

            }
        });

        return view;
    }

}
