package com.musa.raffi.fluxcupplayer.service;

import com.musa.raffi.fluxcupplayer.models.comment.CommentList;
import com.musa.raffi.fluxcupplayer.models.detail.DetailVideo;
import com.musa.raffi.fluxcupplayer.models.video.VideoList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Asus on 9/3/2016.
 */

public interface RestApi {
    @GET("videos")
    Observable<DetailVideo> getDetailVideo(@Query("key") String apiKey,
                                           @Query("part") String snippet,
                                           @Query("id") String videoId);

    @GET("commentThreads")
    Observable<CommentList> getCommentVideo(@Query("part") String snippet,
                                            @Query("videoId") String videoId,
                                            @Query("key") String apiKey);

    @GET("search")
    Observable<VideoList> getVideoId(@Query("key") String apiKey,
                                     @Query("channelId") String channelId,
                                     @Query("part") String snippetCommaId,
                                     @Query("order") String date,
                                     @Query("maxResults") String amountVideo);
}
