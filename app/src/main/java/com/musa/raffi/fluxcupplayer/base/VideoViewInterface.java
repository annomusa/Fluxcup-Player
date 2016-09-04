package com.musa.raffi.fluxcupplayer.base;

import com.musa.raffi.fluxcupplayer.models.video.VideoList;

import rx.Observable;

/**
 * Created by Asus on 9/4/2016.
 */

public interface VideoViewInterface {
    void onCompleted();

    void onError(String message);

    void onVideoList(VideoList videoList);

    Observable<VideoList> getVideos();
}