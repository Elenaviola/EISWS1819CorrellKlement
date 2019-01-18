package com.example.elvic.placetobe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DorfB extends YouTubeBaseActivity {


    YouTubePlayerView mYoutubePlayerview;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dorf_b);
        mYoutubePlayerview = findViewById(R.id.youtubePlayb);
        btnPlay = findViewById(R.id.btnPlay);

        final TextView überschriftb = findViewById(R.id.überschriftb);
        final TextView info1 = findViewById(R.id.info1);
        final TextView info2 = findViewById(R.id.info2);
        final TextView info3 = findViewById(R.id.info3);

        überschriftb.setText(Main6Activity.getOrtNameB());
        info1.setText(Main6Activity.getAllgB());
        info2.setText(Main6Activity.getNaturB());
        info3.setText(Main6Activity.getAktB());


        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("5_ARibfCMhw");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubePlayerview.initialize(com.example.elvic.placetobe.YoutubeConfig.getApiKey(), mOnInitializedListener);
            }
        });
    }
}
