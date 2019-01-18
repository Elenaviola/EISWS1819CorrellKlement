console.log('Server startet');

var express = require ('express');
var bodyParser = require ('body-parser');
var fs = require('fs');
var path = require('path');


var app = express();

app.use(bodyParser.json());

var server = app.listen(3000, listening);

function listening(){
  console.log("listening..")
}

var data = fs.readFileSync('dorf.json');
var dorf = JSON.parse(data);

var result = [];

//var finalResult = [];

app.post ('/userwish', function (req,res){

  result.length = 0;

  console.log("Der Client hat ein POST Request gesendet");
  var name = req.body.ortName;
  var miete = req.body.miete;
  var radius = req.body.radius;
  var aktivitaeten = req.body.aktivitaeten;
  var typ = req.body.typ;

  for (i=0; i<dorf.dorf.length; i++){
    for(j=0; j<dorf.dorf[i].mapDaten.length; j++){
      if (dorf.dorf[i].mapDaten[j].naechsteStadt == name && dorf.dorf[i].mapDaten[j].entfernung <= radius && 
        dorf.dorf[i].allgemeineInfos.mietspiegel <= miete) {

          for(l=0; l<dorf.dorf[i].aktivitaeten.length; l++){
            if (dorf.dorf[i].aktivitaeten[l] == aktivitaeten){
              result.push(dorf.dorf[i]);
            }
          }
      }
    }
  }
  console.log(result.length);

  if (typ == "Natur" && result.length >1){ res.send(selectionSortNatur(result)); }
  if (typ == "Miete" && result.length >1){ res.send(selectionSortMiete(result)); }
  if (typ == "Aktivitaet" && result.length>1){ res.send(selectionSortAktivitaet(result)); }

});

function selectionSortNatur (result){
    
    var i, j,  minIx, minVal;
    var ergebnis = [];

    for (i=0; i<result.length; ++i){
      //console.log(result[i].natur.gruenflaeche);
      minVal = result[minIx=i].natur.gruenflaeche;
      for (j= i+1; j<result.length; j++){
        result[j].natur.gruenflaeche < minVal && (minVal = result[minIx = j].natur.gruenflaeche);
        temp = result[i];
        result[i] = result[minIx];
        result[minIx] = temp;
      }
    }
    
    ergebnis[0] = result[result.length-1];
    ergebnis[1] = result[result.length-2];
    ergebnis[2] = result[result.length-3];

    //console.log(ergebnis);
    return ergebnis;
}

function selectionSortMiete (result){
    
  var i, j, minIx, minVal;
  var ergebnis = [];

  for (i=0; i<result.length; ++i){
    //console.log(result[i].allgemeineInfos.mietspiegel);
    minVal = result[minIx=i].allgemeineInfos.mietspiegel;
    for (j= i+1; j<result.length; j++){
      result[j].allgemeineInfos.mietspiegel < minVal && (minVal = result[minIx = j].allgemeineInfos.mietspiegel);
      temp = result[i];
      result[i] = result[minIx];
      result[minIx] = temp;
    }
  }
  
  ergebnis[0] = result[0];
  ergebnis[1] = result[1];
  ergebnis[2] = result[2];

  //console.log(ergebnis);
  return ergebnis;
}

function selectionSortAktivitaet (result){
    
  var i, j,  minIx, minVal;
  var ergebnis = [];

  for (i=0; i<result.length; ++i){
    //console.log(result[i].aktivitaeten.length);
    minVal = result[minIx=i].aktivitaeten.length;
    for (j= i+1; j<result.length; j++){
      result[j].aktivitaeten.length < minVal && (minVal = result[minIx = j].aktivitaeten.length);
      temp = result[i];
      result[i] = result[minIx];
      result[minIx] = temp;
    }
  }
  
  ergebnis[0] = result[result.length-1];
  ergebnis[1] = result[result.length-2];
  ergebnis[2] = result[result.length-3];

  //console.log(ergebnis);
  return ergebnis;
}


app.get ('/dorf', function (req,res){
  res.json(dorf);
});



