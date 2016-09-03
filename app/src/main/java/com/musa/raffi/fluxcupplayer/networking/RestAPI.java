package com.musa.raffi.fluxcupplayer.networking;

import com.musa.raffi.fluxcupplayer.models.comment.CommentList;
import com.musa.raffi.fluxcupplayer.models.detail.DetailVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Asus on 9/3/2016.
 */

public interface RestApi {
    @GET
    Call<CommentList> getCommentVideo(@Url String url);

    @GET
    Call<DetailVideo> getDetailVideo(@Url String url);
}
