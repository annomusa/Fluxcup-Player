package com.musa.raffi.fluxcupplayer.base;

import android.util.Log;

import com.musa.raffi.fluxcupplayer.models.video.VideoList;

import rx.Observer;

/**
 * Created by Asus on 9/4/2016.
 */

public class VideoPresenter extends BasePresenter implements Observer<VideoList>{
    private VideoViewInterface mViewInterface;

    public VideoPresenter(VideoViewInterface viewInterface) {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(VideoList videoList) {
        mViewInterface.onVideoList(videoList);
    }

    public void fetchVideos() {
        unSubscibeAll();
        subscribe(mViewInterface.getVideos(), VideoPresenter.this);
    }
}
