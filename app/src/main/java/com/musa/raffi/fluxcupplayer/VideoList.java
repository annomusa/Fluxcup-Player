package com.musa.raffi.fluxcupplayer;

import java.util.ArrayList;

/**
 * Created by Asus on 8/24/2016.
 */

public class VideoList {
    private static VideoList ourInstance = new VideoList();

    private ArrayList<String> mVideoList ;

    public static VideoList getInstance(){ return ourInstance; }

    private VideoList(){
        mVideoList = new ArrayList<String>();
        mVideoList.add("bCn_VcWa1hQ");
        mVideoList.add("O2oEdXn8HgA");
        mVideoList.add("3BmiiDs0DC8");
        mVideoList.add("lf-EmkICNZs");
        mVideoList.add("5U6H9ut8oe0");
    }

    public ArrayList<String> getVideo(){ return mVideoList; }

}
