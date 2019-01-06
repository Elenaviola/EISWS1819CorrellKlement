package com.example.elvic.placetobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private static String ortName;
    public static String getName(){
        return ortName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button commitAnswer1 = (Button) findViewById(R.id.commitAnswer1);
        commitAnswer1.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v){

                EditText ortEingabe = (EditText) findViewById(R.id.ortEingabe);
                ortName = String.valueOf(ortEingabe.getText());

                //System.out.println(ortName);

                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(startIntent);
            }
        });

        // TODO Fortschritt anzeigen (Frage 2/6)

    }
}
