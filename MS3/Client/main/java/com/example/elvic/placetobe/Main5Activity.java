package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main5Activity extends AppCompatActivity {

    private static String akt;
    public static String getAkt(){
        return akt;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Button commitAnswer5 = (Button) findViewById(R.id.commitAnswer5);
        commitAnswer5.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){

                EditText aktEingabe1 = (EditText) findViewById(R.id.aktEingabe1);
                akt = String.valueOf(aktEingabe1.getText());

                Intent startIntent = new Intent(getApplicationContext(), Main6Activity.class);
                startActivity(startIntent);
            }
        });
    }
}
