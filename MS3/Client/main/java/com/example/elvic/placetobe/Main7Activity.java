package com.example.elvic.placetobe;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Main7Activity extends AppCompatActivity {
    // Sicherstellen das der User die korrekte Google Play Version haben
    private static final String TAG = "Main7Activity";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    // Ausgabe: Google Services funktioniert

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK:checking google services version");

        int availalbe = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Main7Activity.this);
        if (availalbe == ConnectionResult.SUCCESS) {
            //alles gut und der user bekommt map requests
            Log.d(TAG, "isServicesOK: GooglePlayServices is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(availalbe)) {
            //trotzdem Error aber man kann ihn lösen dann...
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Main7Activity.this, availalbe, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else
            Toast.makeText(this, "Wir können eine Kartenausgabe machen", Toast.LENGTH_SHORT).show();
            return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        init();
    }

    public void init(){
        if (isServicesOK()) {
            Button btnMap = (Button) findViewById(R.id.btnMap);
            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Main7Activity.this, com.example.elvic.placetobe.MainMapActivity.class);
                    startActivity(intent);
                }
            });

            Button overview = (Button) findViewById(R.id.overview);
            overview.setOnClickListener( new View.OnClickListener() {

                public void onClick(View v){
                    Intent intent = new Intent(Main7Activity.this, com.example.elvic.placetobe.DorfA.class);
                    startActivity(intent);
                }
            });

        }
    }


}
