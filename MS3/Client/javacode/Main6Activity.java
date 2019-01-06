package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Main6Activity extends AppCompatActivity {

    private static SeekBar seekBar6;

    private static int aktivitaet;
    public static int getAktivitaet(){
        return aktivitaet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        seekbarr();

        Button commitAnswer6 = (Button) findViewById(R.id.commitAnswer6);
        commitAnswer6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setUser();
                Intent startIntent = new Intent(getApplicationContext(), CardOverview.class);
                startActivity(startIntent);
            }
        });
    }
    public void seekbarr(){
        seekBar6 = (SeekBar)findViewById(R.id.seekBar6);

        seekBar6.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    public void onProgressChanged(SeekBar seekBar6, int progress, boolean fromUser) {
                        aktivitaet = progress;
                    }

                    public void onStartTrackingTouch(SeekBar seekBar6) {

                    }
                    public void onStopTrackingTouch(SeekBar seekBar6) {

                    }
                });

    }
    public void setUser(){

        String name = MainActivity.getName();
        int radius = Main2Activity.getRadius();
        int miete = Main3Activity.getMiete();
        int natur = Main4Activity.getNatur();
        String aktivitaeten = Main5Activity.getAkt();
        int aktivitaet = Main6Activity.getAktivitaet();
        String typ;

        if (natur > aktivitaet) {
            typ = "Naturmensch";
        }
        else {
            typ = "Aktivitätsmensch";
        }

        User u1 = new User (name, radius, miete, natur, aktivitaeten, aktivitaet, typ);

        System.out.println( "Ort: " +u1.getOrtName()  +"\nRadius: " +u1.getRadius()+ "\nMiete: " +u1.getMiete()+ "\nNatur: " +u1.getNatur()+ "\nHobbies: " +u1.getAktivitaeten() +"\nFreizeit: " +u1.getAktivitaet() + "\nTyp: "+ u1.getTyp());
    }
}
