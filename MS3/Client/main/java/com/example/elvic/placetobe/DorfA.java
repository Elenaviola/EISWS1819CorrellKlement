package com.example.elvic.placetobe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
public class DorfA extends YouTubeBaseActivity {

    YouTubePlayerView mYoutubePlayerview;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dorf_a);
        mYoutubePlayerview = findViewById(R.id.youtubePlay);
        btnPlay = findViewById(R.id.btnPlay);

        final TextView überschrift = findViewById(R.id.überschriftA);
        final TextView info1 = findViewById(R.id.info1);
        final TextView info2 = findViewById(R.id.info2);
        final TextView info3 = findViewById(R.id.info3);

        überschrift.setText(Main6Activity.getOrtNameA());

        if (Main6Activity.getTyp().equals("Natur")) {
            info1.setText(Main6Activity.getNaturA());
            info2.setText(Main6Activity.getAllgA());
            info3.setText(Main6Activity.getAktA());
        }
        else if (Main6Activity.getTyp().equals("Aktivitaet")){
            info1.setText(Main6Activity.getAktA());
            info2.setText(Main6Activity.getAllgA());
            info3.setText(Main6Activity.getNaturA());
        }
        else if (Main6Activity.getTyp().equals("Miete")){
            info1.setText(Main6Activity.getAllgA());
            info2.setText(Main6Activity.getNaturA());
            info3.setText(Main6Activity.getAktA());
        }

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("ZNrBe7f6JEg");
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
