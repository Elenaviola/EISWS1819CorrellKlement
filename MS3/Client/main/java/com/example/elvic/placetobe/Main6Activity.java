package com.example.elvic.placetobe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main6Activity extends AppCompatActivity {

    private static SeekBar seekBar6;

    private static int aktivitaet;

    public static int getAktivitaet() {
        return aktivitaet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        seekbarr();

        //Die Seite mit den Vorschlägen wird aufgerufen
        Button commitAnswer6 = (Button) findViewById(R.id.commitAnswer6);
        final TextView text = findViewById(R.id.text);
        commitAnswer6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //Es wird pberprüft ob eine Netzwerkverbindung steht
                checkNetwork();
                //die JSON Datei wird mit den Informationen aus Frage 1-6 gefüllt
                final String jsons = fillUser();

                //CLIENT
                AsyncTask asynk = new AsyncTask(){

                    @Override
                    protected Object doInBackground(Object[] objects) {

                        //Client wird erstell
                        OkHttpClient client = new OkHttpClient();

                        //der Mediatype und der body (aus json) werden erstellt
                        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, jsons.toString());

                        //ein POST Request wird kreiert
                        Request post = new Request.Builder()
                                .url("http://10.0.2.2:3000/userwish")
                                .post(body)
                                .build();

                        Response res = null;

                        try{
                            //POST wird gesendet und Antwort des Servers empfangen
                            res = client.newCall(post).execute();
                            return res.body().string();

                        } catch (IOException e){
                            System.out.println("ioexc");
                            e.printStackTrace();
                        }
                        return null;
/*
                        //GET REQUEST
                        Request get = new Request.Builder()
                                .url("http://10.0.2.2:3000/userwish/dorf")
                                .build();

                        Response response = null;

                        try{
                            response = client.newCall(get).execute();
                            return response.body().string();
                        } catch (IOException e){
                            System.out.println("ioexc");
                            e.printStackTrace();
                        }
                        return null; */
                    }
                    protected void onPostExecute(Object o){

                        text.setText(o.toString());
                    }
                }.execute();

                //nächste Seite wird aufgerufen
                Intent startIntent = new Intent(getApplicationContext(), CardOverview.class);
                startActivity(startIntent);
            }
        });
    }

    public void seekbarr() {
        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);

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



    public String fillUser() {

        //Die Variablen werden mit den ermittelten Werten aus den vorherigen Seiten gefüllt
        String name = MainActivity.getName();
        int radius = Main2Activity.getRadius();
        int miete = Main3Activity.getMiete();
        int natur = Main4Activity.getNatur();
        String aktivitaeten = Main5Activity.getAkt();
        int aktivitaet = Main6Activity.getAktivitaet();
        String typ;

        //Je nachdem ob den User Natur oder Freizeit wichitger ist wird ihm ein Typ zugeordnet
        if (natur > aktivitaet) {
            typ = "Naturmensch";
        } else {
            typ = "Aktivitätsmensch";
        }

        //Ein Objekt u1 der Klasse User wird erstellt
        User u1 = new User(name, radius, miete, natur, aktivitaeten, aktivitaet, typ);

        //Das Objekt wird in ein JSON umgewandelt
        Gson gson = new Gson();
        String json = gson.toJson(u1);
        System.out.println(json);

        //die JSON Datei wird zurückgegeben
        return json;
    }

    //Funktion um die Netzwerkverbindung zu überprüfen
    public boolean checkNetwork() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean isConnected = false;

        if (ni != null && (isConnected = ni.isConnected())) {
            System.out.println("connected");
        } else {
            System.out.println("not connected");
        }
        return isConnected;
    }
}

