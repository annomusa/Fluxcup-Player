package com.musa.raffi.fluxcupplayer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private ArrayList<String> mVideoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FragmentManager fragmentManager = getFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.detailVideo);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            position = extras.getInt("Position");
        }

        if(fragment == null){
            fragment = DetailVideoFragment.newInstance(position);
            fragmentManager.beginTransaction().add(R.id.detailVideo, fragment).commit();
        }
    }
}
