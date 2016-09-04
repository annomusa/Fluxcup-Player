package com.musa.raffi.fluxcupplayer;

import java.util.ArrayList;

/**
 * Created by Asus on 8/24/2016.
 */

public class SingletonVideoList {
    private static SingletonVideoList ourInstance = new SingletonVideoList();

    private ArrayList<String> mVideoList ;

    public static SingletonVideoList getInstance(){ return ourInstance; }

    private SingletonVideoList(){
        mVideoList = new ArrayList<>();
    }

    public void addNewVideo(String string){ mVideoList.add(string); }

    public ArrayList<String> getVideo(){ return mVideoList; }

}
