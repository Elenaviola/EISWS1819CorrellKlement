package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    //statische variable ortName wird erstellt
    private static String ortName;
    public static String getName(){
        return ortName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button definieren
        Button commitAnswer1 = (Button) findViewById(R.id.commitAnswer1);
        //Funktion: wenn der Button geklickt wird
        commitAnswer1.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){

                //der statischen Variable wird die Benutzereingabe ins Textfeld zugewiesen
                EditText ortEingabe = (EditText) findViewById(R.id.ortEingabe);
                ortName = String.valueOf(ortEingabe.getText());
                //Die nächste Seite (Frage 2 wird aufgerufen)
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(startIntent);
            }
        });

        // TODO Fortschritt anzeigen (Frage 2/6)

    }
}
