package com.musa.raffi.fluxcupplayer.detail;

import com.musa.raffi.fluxcupplayer.BasePresenter;
import com.musa.raffi.fluxcupplayer.models.comment.CommentList;

import rx.Observer;

/**
 * Created by Asus on 9/5/2016.
 */

public class CommentPresenter extends BasePresenter implements Observer<CommentList> {
    private DetailCommentViewInterface  mViewInterface;

    public CommentPresenter(DetailCommentViewInterface viewInterface) {
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
    public void onNext(CommentList commentList) {
        mViewInterface.onCommentVideo(commentList);
    }

    public void fetchComment(){
        unSubscibeAll();
        subscribe(mViewInterface.getComment(), CommentPresenter.this);
    }
}
