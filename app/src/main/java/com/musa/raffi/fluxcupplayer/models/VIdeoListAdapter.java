package com.musa.raffi.fluxcupplayer.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.musa.raffi.fluxcupplayer.detail.DetailActivity;
import com.musa.raffi.fluxcupplayer.R;
import com.musa.raffi.fluxcupplayer.models.video.Item;
import com.musa.raffi.fluxcupplayer.models.video.VideoList;

import java.util.List;


/**
 * Created by Asus on 8/23/2016.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoInfoHolder> {
    Context ctx;
//    private ArrayList<String> mVideoList = com.musa.raffi.fluxcupplayer.models.SingletonVideoList.getInstance().getVideo();
    private List<String> mVideoList;

    public VideoListAdapter(Context context) {
        this.ctx = context;
        mVideoList = SingletonVideoList.getInstance().getVideo();
    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup view, int viewType) {
        View itemView = LayoutInflater.from(view.getContext()).inflate(R.layout.card_view, view, false);
        return new VideoInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize(Resource.KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(mVideoList.get(position));
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public void addVideosList(VideoList videoList) {
        List<Item> items;
        items = videoList.getItems();
        if (items.size() > 0){
            for (int i=0; i<items.size(); i++){
                String videoId = items.get(i).getId().getVideoId();
                mVideoList.add(videoId);
            }
        }
        notifyDataSetChanged();
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;

        public VideoInfoHolder (View view) {
            super(view);
            playButton = (ImageView) view.findViewById(R.id.btnYoutube_player);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) view.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) view.findViewById(R.id.youtube_thumbnail);

            playButton.setOnClickListener(this);
            playButton.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent( (Activity) ctx,
                    Resource.KEY,
                    mVideoList.get(getLayoutPosition()),
                    100,
                    false,
                    true);
            ctx.startActivity(intent);
//            Intent intent = new Intent(v.getContext(), DetailActivity.class);
//            intent.putExtra("Position", getLayoutPosition());
//            ctx.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v){
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("Position", getLayoutPosition());
            ctx.startActivity(intent);

            return false;
        }
    }
}
