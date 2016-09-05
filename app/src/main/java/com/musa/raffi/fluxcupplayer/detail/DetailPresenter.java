package com.musa.raffi.fluxcupplayer.detail;

import com.musa.raffi.fluxcupplayer.BasePresenter;
import com.musa.raffi.fluxcupplayer.models.detail.DetailVideo;

import rx.Observer;

/**
 * Created by Asus on 9/5/2016.
 */

public class DetailPresenter extends BasePresenter implements Observer<DetailVideo> {
    private DetailCommentViewInterface mViewInterface;

    public DetailPresenter(DetailCommentViewInterface viewInterface) {
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
    public void onNext(DetailVideo detailVideo) {
        mViewInterface.onDetailVideo(detailVideo);
    }

    public void fetchDetail(){
        unSubscibeAll();
        subscribe(mViewInterface.getDetail(), DetailPresenter.this);
    }
}
