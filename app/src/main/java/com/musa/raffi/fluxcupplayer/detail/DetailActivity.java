package com.musa.raffi.fluxcupplayer.detail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.musa.raffi.fluxcupplayer.R;
import com.musa.raffi.fluxcupplayer.deps.Deps;
import com.musa.raffi.fluxcupplayer.models.Resource;
import com.musa.raffi.fluxcupplayer.models.SingletonVideoList;
import com.musa.raffi.fluxcupplayer.models.comment.CommentList;
import com.musa.raffi.fluxcupplayer.models.comment.Item;
import com.musa.raffi.fluxcupplayer.models.detail.DetailVideo;
import com.musa.raffi.fluxcupplayer.service.RestApi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class DetailActivity extends AppCompatActivity implements DetailCommentViewInterface {
    @Inject
    RestApi restApi;

    private DetailPresenter mDetailPresenter;
    private CommentPresenter mCommentPresenter;

    private String mCurrentVideo;
    private List<String> mVideoList;

    @Bind(R.id.listComment)
    ListView listComment;

    @Bind(R.id.txtTitle)
    TextView txtTitle;

    @Bind(R.id.txtDetail)
    TextView txtDetail;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            position = extras.getInt("Position");
        }
        mVideoList = SingletonVideoList.getInstance().getVideo();
        mCurrentVideo = mVideoList.get(position);

        resolveDependency();

        ButterKnife.bind(DetailActivity.this);

        configViews();

        mDetailPresenter = new DetailPresenter(DetailActivity.this);
        mDetailPresenter.onCreate();

        mCommentPresenter = new CommentPresenter(DetailActivity.this);
        mCommentPresenter.onCreate();
    }

    private void configViews() {

    }

    private void resolveDependency() {
        ((Deps) getApplication())
                .getApiComponent()
                .bind(DetailActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetailPresenter.onResume();
        mCommentPresenter.onResume();
        mDetailPresenter.fetchDetail();
        mCommentPresenter.fetchComment();
        mDialog = new ProgressDialog(DetailActivity.this);
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
    public void onDetailVideo(DetailVideo detailVideo) {
        String mTitle = detailVideo.getItems().get(0).getSnippet().getTitle();
        String mDescription = detailVideo.getItems().get(0).getSnippet().getDescription();
        txtTitle.setText(mTitle);
        txtDetail.setText(mDescription);
    }

    @Override
    public Observable<DetailVideo> getDetail() {
        return restApi.getDetailVideo(Resource.KEY,
                "snippet",
                mCurrentVideo
                );
    }

    @Override
    public void onCommentVideo(CommentList commentList) {
        final List<HashMap<String, String>> commentVideo = new ArrayList<>();
        List<Item> items = commentList.getItems();
        if(items.size() > 0) {
            for (int i=0; i<items.size(); i++){
                String author = items.get(i).getSnippet().getTopLevelComment()
                        .getSnippet().getAuthorDisplayName();
                String comment = items.get(i).getSnippet().getTopLevelComment()
                        .getSnippet().getTextDisplay();

                HashMap<String,String> commentDetail = new HashMap<>();
                commentDetail.put("mAuthor", author);
                commentDetail.put("mComment", comment);
                commentVideo.add(commentDetail);
            }
        }
        SimpleAdapter adapter = new SimpleAdapter(DetailActivity.this,
                commentVideo,
                R.layout.list_comment,
                new String[] {"mAuthor", "mComment"},
                new int[] {R.id.txtAuthor, R.id.txtComment}
                );
        listComment.setAdapter(adapter);
    }

    @Override
    public Observable<CommentList> getComment() {
        return restApi.getCommentVideo("snippet",
                mCurrentVideo,
                Resource.KEY);
    }
}
