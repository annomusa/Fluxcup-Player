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

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Asus on 8/24/2016.
 */

public class DetailVideoFragment extends Fragment {
    private ArrayList<String> mVideoList;
    private String mCurrentVideo;

    private Subscription subscription;

    public static final String ROOT_URL = "https://www.googleapis.com/youtube/v3/";

    private static final String TAG_AUTHOR = "authorDisplayName";
    private static final String TAG_COMMENT = "textDisplay";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mVideoList = VideoList.getInstance().getVideo();
        int position = getArguments().getInt("Position");
        mCurrentVideo = mVideoList.get(position);
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

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        RestApi apiService = retrofit.create(RestApi.class);

        Observable<DetailVideo> callDetail = apiService.getDetailVideo(Resource.KEY, "snippet", mCurrentVideo);
        subscription = callDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<DetailVideo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailVideo detailVideo) {
                        String title = detailVideo.getItems().get(0).getSnippet().getTitle();
                        String description = detailVideo.getItems().get(0).getSnippet().getDescription();
                        txtTitle.setText(title);
                        txtDescription.setText(description);
                    }
                });

        Observable<CommentList> callComment = apiService.getCommentVideo("snippet", mCurrentVideo, Resource.KEY);
        subscription = callComment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommentList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommentList commentList) {
                        final ArrayList<HashMap<String, String>> commentVideo = new ArrayList<>();
                        List<Item> items;
                        items = commentList.getItems();
                        if(items.size() > 0){
                            for (int i=0; i<items.size(); i++){
                                String author = items.get(i).getSnippet().getTopLevelComment()
                                        .getSnippet().getAuthorDisplayName();
                                String comment = items.get(i).getSnippet().getTopLevelComment()
                                        .getSnippet().getTextDisplay();

                                HashMap<String, String> commentDetail = new HashMap<>();
                                commentDetail.put(TAG_AUTHOR, author);
                                commentDetail.put(TAG_COMMENT, comment);
                                commentVideo.add(commentDetail);

                                Log.d("req comment", "onResponse: " + author + comment);
                            }
                        }

                        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                                commentVideo,
                                R.layout.list_comment,
                                new String[]{ TAG_AUTHOR, TAG_COMMENT },
                                new int[]{ R.id.txtAuthor, R.id.txtComment }
                        );
                        list_comment.setAdapter(adapter);

                    }
                });

        return view;
    }

    @Override
    public void onDestroy(){
        subscription.unsubscribe();
        super.onDestroy();
    }

}
