package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Main1Activity extends AppCompatActivity {

    //statische variable ortName zur Übergabe an fillUser() -> Main6Activity
    private static String ortName;
    public static String getName(){
        return ortName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button zur nächsten Frage
        Button commitAnswer1 = (Button) findViewById(R.id.commitAnswer1);

        commitAnswer1.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){

                //ortName wird die Benutzereingabe zugewiesen
                EditText ortEingabe = (EditText) findViewById(R.id.ortEingabe);
                ortName = String.valueOf(ortEingabe.getText());

                //Frage 2 wird aufgerugen
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(startIntent);
            }
        });

        // TODO Fortschritt anzeigen

    }
}
