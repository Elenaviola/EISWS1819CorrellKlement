package com.example.elvic.placetobe;

import android.annotation.SuppressLint;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main6Activity extends AppCompatActivity {

    private static SeekBar seekBar6;

    public static String aktaa = "";
    public static String naturaa = "";
    public static String aktab = "";
    public static String naturab = "";
    public static String aktac = "";
    public static String naturac = "";

    private static int aktivitaet;
    public static int getAktivitaet() {
        return aktivitaet;
    }
    private static String typ;
    public static String getTyp() {
        return typ;
    }

    private static String ortNameA;
    public static String getOrtNameA(){ return ortNameA; }
    private static String allgA;
    public static String getAllgA(){
        return allgA;
    }
    private static String naturA;
    public static String getNaturA(){
        return naturA;
    }
    private static String aktA;
    public static String getAktA(){ return aktA; }

    private static String ortNameB;
    public static String getOrtNameB(){ return ortNameB; }
    private static String allgB;
    public static String getAllgB(){
        return allgB;
    }
    private static String naturB;
    public static String getNaturB(){
        return naturB;
    }
    private static String aktB;
    public static String getAktB(){ return aktB; }

    private static String ortNameC;
    public static String getOrtNameC(){ return ortNameC; }
    private static String allgC;
    public static String getAllgC(){
        return allgC;
    }
    private static String naturC;
    public static String getNaturC(){
        return naturC;
    }
    private static String aktC;
    public static String getAktC(){ return aktC; }


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
                @SuppressLint("StaticFieldLeak") AsyncTask asynk = new AsyncTask(){

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
                            String data = res.body().string();

                            try{
                                //JSON vom server in ein JSON Array umwandeln
                                JSONArray JA = new JSONArray(data);

                                //Dorf A
                                JSONObject jsonA = (JSONObject) JA.get(0);

                                JSONObject allgemeinA = (JSONObject) jsonA.get("allgemeineInfos");
                                ortNameA = (String) allgemeinA.get("name");
                                allgA = "Bundesland: " + allgemeinA.get("bundesland") + "\n" +
                                        "Region: " + allgemeinA.get("region") + "\n" +
                                        "Einwohnerzahl: " + allgemeinA.get("einwohnerzahl");

                                JSONObject naturaA = (JSONObject) jsonA.get("natur");
                                JSONArray natur = (JSONArray) naturaA.get("natur");

                                StringBuilder sb = new StringBuilder();

                                for (int n = 0; n < natur.length(); n++){
                                    sb.append(natur.get(n));
                                    sb.append(" \n");
                                    naturaa = String.valueOf(sb);
                                    System.out.println(naturaa);
                                }

                                naturA = "Gruenflaeche pro Einwohner: " + naturaA.get("gruenflaeche") + "\n" +
                                        " Natur in der Umgebung: " + naturaa;

                                JSONArray aktivitaeta = (JSONArray) jsonA.get("aktivitaeten");
                                StringBuilder sb1 = new StringBuilder();

                                for (int m = 0; m < aktivitaeta.length(); m++){
                                    sb1.append(aktivitaeta.get(m));
                                    sb1.append(" \n");
                                    aktaa = String.valueOf(sb1);
                                }

                                aktA = "Freizeitaktivitaten in " + ortNameA + ": \n" + aktaa;


                                //DorfB
                                JSONObject jsonB = (JSONObject) JA.get(1);

                                JSONObject allgemeinB = (JSONObject) jsonB.get("allgemeineInfos");
                                ortNameB = (String) allgemeinB.get("name");
                                allgB = "Bundesland: " + allgemeinB.get("bundesland") + "\n" +
                                        "Region: " + allgemeinB.get("region") + "\n" +
                                        "Einwohnerzahl: " + allgemeinB.get("einwohnerzahl");

                                JSONObject naturaB = (JSONObject) jsonB.get("natur");
                                JSONArray naturb = (JSONArray) naturaB.get("natur");

                                StringBuilder sb2 = new StringBuilder();

                                for (int n = 0; n < naturb.length(); n++){
                                    sb2.append(naturb.get(n));
                                    sb2.append(" \n");
                                    naturab = String.valueOf(sb2);
                                    System.out.println(naturab);
                                }

                                naturB = "Gruenflaeche pro Einwohner: " + naturaB.get("gruenflaeche") + "\n" +
                                        " Natur in der Umgebung: " + naturab;

                                JSONArray aktivitaetb = (JSONArray) jsonB.get("aktivitaeten");
                                StringBuilder sb3 = new StringBuilder();

                                for (int m = 0; m < aktivitaeta.length(); m++){
                                    sb3.append(aktivitaetb.get(m));
                                    sb3.append(" \n");
                                    aktab = String.valueOf(sb3);
                                }

                                aktB = "Freizeitaktivitaten in " + ortNameB + ": \n" + aktab;

                                //DorfB
                                JSONObject jsonC = (JSONObject) JA.get(2);

                                JSONObject allgemeinC = (JSONObject) jsonC.get("allgemeineInfos");
                                ortNameC = (String) allgemeinC.get("name");
                                allgC = "Bundesland: " + allgemeinC.get("bundesland") + "\n" +
                                        "Region: " + allgemeinC.get("region") + "\n" +
                                        "Einwohnerzahl: " + allgemeinC.get("einwohnerzahl");

                                JSONObject naturaC = (JSONObject) jsonC.get("natur");
                                JSONArray naturc = (JSONArray) naturaC.get("natur");

                                StringBuilder sb4 = new StringBuilder();

                                for (int n = 0; n < naturc.length(); n++){
                                    sb4.append(naturc.get(n));
                                    sb4.append(" \n");
                                    naturac = String.valueOf(sb4);
                                    System.out.println(naturac);
                                }

                                naturC = "Gruenflaeche pro Einwohner: " + naturaC.get("gruenflaeche") + "\n" +
                                        " Natur in der Umgebung: " + naturac;

                                JSONArray aktivitaetc = (JSONArray) jsonC.get("aktivitaeten");
                                StringBuilder sb5 = new StringBuilder();

                                for (int m = 0; m < aktivitaetc.length(); m++){
                                    sb5.append(aktivitaetc.get(m));
                                    sb5.append(" \n");
                                    aktac = String.valueOf(sb5);
                                }

                                aktC = "Freizeitaktivitaten in " + ortNameC + ": \n" + aktac;


                            } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("JSONException");
                        }


                        return data;

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

                        //ortName = ueberschrift;
                        System.out.println("onPostExecute");


                    }
                }.execute();

                //nächste Seite wird aufgerufen
                Intent startIntent = new Intent(getApplicationContext(), MainMapActivity.class);
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
        String name = Main1Activity.getName();
        int radius = Main2Activity.getRadius();
        int miete = Main3Activity.getMiete();
        int natur = Main4Activity.getNatur();
        String aktivitaeten = Main5Activity.getAkt();
        int aktivitaet = Main6Activity.getAktivitaet();

        //Je nachdem ob den User Natur oder Freizeit wichitger ist wird ihm ein Typ zugeordnet
        if (natur > aktivitaet) {
            typ = "Natur";
        } else if (miete < 600) {
            typ = "Miete";
        } else {
            typ = "Aktivitaet";
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

