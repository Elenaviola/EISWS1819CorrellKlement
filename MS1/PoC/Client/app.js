var readline = require('readline');
var fs = require ('fs');
var express = require ('express');
var bodyParser = require('body-parser');
var request = require ('request');
var router = require ('router');

var app = express();


const ressourcename = "kultur";

var rl = readline.createInterface(process.stdin, process.stdout);

//global.kultur = require ("./kultur.json");

var kulturwert = {
    'Ort': String,
    'Kultur': [],
    'Gesamtwert': Number
};

rl.question("Welchen Ort möchten Sie bewerten? ", function(antwort){
    kulturwert.Ort = antwort;
    rl.setPrompt('Wie würden Sie die Kultur von ' + kulturwert.Ort + ' von 1-10 bewerten? \n' +
                'Wie würden Sie die Tradition von ' +kulturwert.Ort + ' von 1-10 bewerten? \n' +
                'Wie würden Sie das soziale Umfeld von ' +kulturwert.Ort + ' von 1-10 bewerten? \n'+
                'Ihr Wert: ');
    rl.prompt();
    rl.on('line', function(kultur){
        kulturwert.Kultur.push(kultur);
        if (kulturwert.Kultur.length > 2){
            rl.close();
        } else {
            rl.setPrompt("Ihr Wert: ");
            rl.prompt();
    }
    });
});


rl.on('close', function(){
    console.log("Der Ort %s hat die Werte %d %d %d erhalten", kulturwert.Ort, kulturwert.Kultur[0], kulturwert.Kultur[1], kulturwert.Kultur[2]);
    kulturwert.Gesamtwert = (((Number(kulturwert.Kultur[0])*3) + (Number(kulturwert.Kultur[1])*2) + (Number(kulturwert.Kultur[2])))/8);
    console.log("Der Gesamtwert beträgt %d", kulturwert.Gesamtwert);

    var bewertung = {
        "Ort" : null,
        "Gesamtbewertung" : null
    };
    bewertung.Ort = kulturwert.Ort;
    bewertung.Gesamtbewertung = kulturwert.Gesamtwert;

    //kultur.bewertungen.push(bewertung);
  
    process.exit();
});

app.get('/kulturerhebung', function(req,res){
    var url = "http://localhost:3000/kulturerhebung";
    request (url, function (err, response, body){
        body = JSON.parse(body);
        res.json(body);
    })
});

app.post('/kulturerhebung', bodyParser.json(), function(req,res){

    var bewertung = req.body;
    var url = "http://localhost:3000/kulturerhebung";
    var options = {
        uri: url,
        method: "POST",
        headers : {
            "Content-Type": "application/json"
        },
        json: bewertung
    }

    bewertung.gesamtbewertung = (((bewertung.Kultur*3) + (bewertung.Tradition*2) + (bewertung.sozialesUmfeld*2))/7);

    request(options, function(err, response, body){
        res.json(bewertung);
    });

});

app.listen (8080, function(){
    console.log("Dienstnutzer verfügbar");
});








