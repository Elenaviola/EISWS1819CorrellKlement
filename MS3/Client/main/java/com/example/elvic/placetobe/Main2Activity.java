package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Main2Activity extends AppCompatActivity{

    private static SeekBar seekBar2;

    private static int radius;
    public static int getRadius(){
        return radius;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Die Funktion seekbar kümmert sich um die Reglereingabe
        //Der Wert wird zurückgegeben und in radius gespeichert
        seekbarr();

        Button commitAnswer2 = (Button) findViewById(R.id.commitAnswer2);

        //Frage 3 wird aufgerufen
        commitAnswer2.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(startIntent);
            }
        });

    }

    public void seekbarr(){

        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);

        seekBar2.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    //wird der Regler verändert so ändert sich auch die statische Variable radius
                    public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                        radius = progress;
                    }
                    public void onStartTrackingTouch(SeekBar seekBar2) {

                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

    }
}
