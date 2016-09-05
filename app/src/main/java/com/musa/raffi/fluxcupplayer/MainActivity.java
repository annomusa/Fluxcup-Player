package com.musa.raffi.fluxcupplayer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.musa.raffi.fluxcupplayer.base.VideoPresenter;
import com.musa.raffi.fluxcupplayer.base.VideoViewInterface;
import com.musa.raffi.fluxcupplayer.deps.Deps;
import com.musa.raffi.fluxcupplayer.models.Resource;
import com.musa.raffi.fluxcupplayer.models.VideoListAdapter;
import com.musa.raffi.fluxcupplayer.models.video.VideoList;
import com.musa.raffi.fluxcupplayer.service.RestApi;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements VideoViewInterface {
    @Inject
    RestApi restApi;

    private VideoPresenter mPresenter;

    @Bind(R.id.list)
    RecyclerView mRecyclerView;

    private VideoListAdapter mAdapter;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        resolveDependency();

        ButterKnife.bind(MainActivity.this);

        if(mRecyclerView != null) configViews();
        mPresenter = new VideoPresenter(MainActivity.this);
        mPresenter.onCreate();

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        if (null != recyclerView) {
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.setLayoutManager(linearLayoutManager);
//
//            VIdeoListAdapter adapter = new VIdeoListAdapter(MainActivity.this);
//            recyclerView.setAdapter(adapter);
//        }
    }

    private void resolveDependency() {
        ((Deps) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void configViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new VideoListAdapter(getApplicationContext(), MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchVideos();
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onVideoList(VideoList videoList) {
        mAdapter.addVideosList(videoList);
    }

    @Override
    public Observable<VideoList> getVideos() {
//        Log.d("much", "https://www.googleapis.com/youtube/v3/search?key=" + Resource.KEY + "&channelId=" + Resource.CHANNEL_ID + "&part=snippet,id&order=date&maxResults=20");
        return restApi.getVideoId(Resource.KEY,
                Resource.CHANNEL_ID,
                "snippet,id",
                "date",
                "5");
    }
}