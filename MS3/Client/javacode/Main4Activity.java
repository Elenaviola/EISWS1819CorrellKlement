package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Main4Activity extends AppCompatActivity {

    private static SeekBar seekBar4;

    private static int natur;
    public static int getNatur(){
        return natur;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        seekbarr();

        Button commitAnswer4 = (Button) findViewById(R.id.commitAnswer4);
        commitAnswer4.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), Main5Activity.class);
                startActivity(startIntent);
            }
        });
    }

    public void seekbarr(){
        seekBar4 = (SeekBar)findViewById(R.id.seekBar4);

        seekBar4.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    public void onProgressChanged(SeekBar seekBar4, int progress, boolean fromUser) {
                        natur = progress;
                    }

                    public void onStartTrackingTouch(SeekBar seekBar4) {

                    }
                    public void onStopTrackingTouch(SeekBar seekBar4) {
                    }
                });

    }
}
