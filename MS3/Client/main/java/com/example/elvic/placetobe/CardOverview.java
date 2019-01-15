package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CardOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_overview);


        Button buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), CityOverview.class);
                startActivity(startIntent);
            }
        });
        /*
        Button buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), CityOverview2.class);
                startActivity(startIntent);
            }
        });

        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), CityOverview3.class);
                startActivity(startIntent);
            }
        });
        */

        //TODO Karte mit vorschlägen anzeigen
    }
}
