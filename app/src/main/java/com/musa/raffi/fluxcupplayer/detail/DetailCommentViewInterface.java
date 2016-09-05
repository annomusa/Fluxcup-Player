package com.musa.raffi.fluxcupplayer.detail;

import com.musa.raffi.fluxcupplayer.models.comment.CommentList;
import com.musa.raffi.fluxcupplayer.models.detail.DetailVideo;

import rx.Observable;

/**
 * Created by Asus on 9/5/2016.
 */

public interface DetailCommentViewInterface {
    void onCompleted();

    void onError(String message);

    void onDetailVideo(DetailVideo detailVideo);

    Observable<DetailVideo> getDetail();

    void onCommentVideo(CommentList commentList);

    Observable<CommentList> getComment();
}
