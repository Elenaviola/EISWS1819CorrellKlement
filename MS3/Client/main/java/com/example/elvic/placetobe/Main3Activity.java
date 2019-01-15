package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Main3Activity extends AppCompatActivity {

    private static SeekBar seekBar3;

    private static int miete;
    public static int getMiete(){ return miete;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        seekbarr();

        //Frage 4 wird aufgerufen
        Button commitAnswer3 = (Button) findViewById(R.id.commitAnswer3);

        commitAnswer3.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(startIntent);
            }
        });
    }
    public void seekbarr(){
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);

        seekBar3.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {
                    //wird der Regler verändert so ändert sich auch die statische Variable miete
                    public void onProgressChanged(SeekBar seekBar3, int progress, boolean fromUser) {
                        miete = progress;
                    }
                    public void onStartTrackingTouch(SeekBar seekBar3) {

                    }
                    public void onStopTrackingTouch(SeekBar seekBar3) {

                    }
                });

    }
}
