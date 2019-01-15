package com.example.agando.googleapicode;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    public void onMapReady(GoogleMap googleMap){
        Toast.makeText(this,"Karte kann geladen werden",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onMapReady: Karte kann geladen werden");
        mMap = googleMap;
    }


    private static final String TAG = "MapActivity";

    private static final String Fine_Location =  Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_Location = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int Location_Permission_REQUEST_CODE = 20119;


    private Boolean mLocationPermissionGRANTED = false;
    private GoogleMap mMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getLocationPermission();
    }
        // Benötigen ein Fragment von API aus
        private void initMap(){
            Log.d(TAG, "initMap: Karte starten");
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager(). findFragmentById(R.id.map);
            // Vorbereitung der Karte
            mapFragment.getMapAsync(MapActivity.this);


    }
        //Permission Checken
    private void getLocationPermission(){
        Log.d(TAG,"getLocationPermission: Lokale Rechteeinforderung");
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Fine_Location)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_Location)== PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGRANTED = true;
            }else {
                ActivityCompat.requestPermissions(this,
                        permission,
                        Location_Permission_REQUEST_CODE);}
            }else{
                    ActivityCompat.requestPermissions(this,
                            permission,
                            Location_Permission_REQUEST_CODE);
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"onRequestPermissionsResult:Gerufen");
        // Gehen von Fehlschlag aus und switchen dann zum Erfolg
        mLocationPermissionGRANTED = false;

        switch (requestCode) {
            case Location_Permission_REQUEST_CODE: {
                // mehrere Zugriffe gleichzeitig checken
                if (grantResults.length > 0)
                    // Dafür legen wir ein Loop an und return die Angabe
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGRANTED = false;
                            Log.d(TAG,"onRequestPermissionResult:permission failed");
                            return;
                        }
                    }
                    Log.d(TAG,"onRequestPermissionReslut:permission granted");
                mLocationPermissionGRANTED = true;
                // danach Karte starten
                initMap();

            }
        }

    }}