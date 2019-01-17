package com.example.elvic.placetobe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Karte kann geladen werden", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: Karte kann geladen werden");
        mMap = googleMap;

        if (mLocationPermissionGRANTED) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            //GPS ICON von Google Deaktivieren,weil es von der Suchbar blockiert wird
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            init();
        }
    }


    private static final String TAG = "MapActivity";

    private static final String Fine_Location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_Location = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int Location_Permission_REQUEST_CODE = 20119;
    private static final float Default_Zoom = 15f;

    //widgets
    private EditText mSearchText;
    private ImageView mGps;
    //vars
    private Boolean mLocationPermissionGRANTED = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        mSearchText = (EditText) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);
        getLocationPermission();


    }

    // Enter button wird ausgenommen man kann eifach jetzt auf die Tastatur enter klicken
    // Es gab Probleme beim Button drüken also wurde eine alternative genommen und sicher zu stellen das es geht
    private void init() {
        Log.d(TAG, "init: initalizierung");

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEventevent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEventevent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEventevent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    //execute für das Suchen
                    geoLocate();
                }
                return false;
            }
        });
        hideSoftKeyboard();
        // Zurück zu Unsere Position
        mGps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG,"onCLick: clicked gps icon");
                getDeviceLocation();
            }
        });
    }

    private void geoLocate() {
        Log.d(TAG, "geoLocate: geoLocating");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> list = new ArrayList<>();
        // Liste gib nur einen Standort aus muss auf drei hochgesettet werden
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }
        if (list.size() > 0) {
            Address address = list.get(0);
            Log.d(TAG, "geoLocate: Location gefunden: " + address.toString());
            //    Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
            // Hier könnten die Lat/Log Daten reinkommen um alle Dörfer mit Markern zu suchen
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), Default_Zoom,
                    address.getAddressLine(0));
        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: Ausgabe der jetzigen Gerätelocation");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionGRANTED) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    Default_Zoom,
                                    "My Location");
                        } else {
                            Log.d(TAG, "onComplete: current location not found");
                            Toast.makeText(MapActivity.this, "Standort konnte nicht gefunden werden", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
            System.out.println("secex");
        }
    }

    // Benötigen ein Fragment von API aus
    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ",lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title(title);
        mMap.addMarker(options);

        hideSoftKeyboard();
    }



        private void initMap(){
            Log.d(TAG, "initMap: Karte anwendungsbereit");
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
                initMap();
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

    }
    // Hierbei lassen wir die Tastatur auf dem Handy verschwinden
    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}