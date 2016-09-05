package com.musa.raffi.fluxcupplayer;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Asus on 9/4/2016.
 */

public abstract class BasePresenter implements Presenter {
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        configureSubscription();
    }

    private CompositeSubscription configureSubscription() {
        if(mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription ;
    }

    @Override
    public void onDestroy() {
        unSubscibeAll();
    }

    protected void unSubscibeAll() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
        }
    }

    protected <R> void subscribe(Observable<R> observable, Observer<R> observer){
        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
        configureSubscription().add(subscription);
    }
}
