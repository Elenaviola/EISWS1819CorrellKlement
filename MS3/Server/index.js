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

var Userwish = {
  id : 1,
  Stadt :'',
  Radius : 1,
  Miete : 1,
  Aktivitaeten : [''],
  Typ : '',
  Dorf : ['','','']
};

app.post ('/userwish', function (req,res){

  var name = req.body.ortName;
  var miete = req.body.miete;
  var radius = req.body.radius;
  var aktivitaeten = req.body.aktivitaten;
  var typ = req.body.typ;

  for (i=0; i<dorf.dorf.length; i++){
    for(j=0; j<dorf.dorf[i].mapDaten.length; j++){
      if (dorf.dorf[i].mapDaten[j].naechsteStadt == name && dorf.dorf[i].mapDaten[j].entfernung <= radius ) {
          result.push(dorf.dorf[i]);

        /*
        if (dorf.dorf[i].natur.gruenflaeche > 200) {
          console.log("1");
          if (typ == "Natur"){
            dorf.dorf[i].punkte+=100;
          } else {
            dorf.dorf[i].punkte+=50;
          }
        } else if ((dorf.dorf[i].allgemeineInfos.miete - miete) >= 50){

          if (typ == "Miete"){
            dorf.dorf[i].punkte+=100;
            console.log(dorf.dorf[i].punkte);
            console.log("2");
          } else {
            dorf.dorf[i].punkte+=50;
        }
        } /*for (j=0; j<dorf.dorf[i].aktivitaeten.length; j++){
        console.log("2");
        if (dorf.dorf[i].aktivitaeten[j]== aktivitaeten){
          dorf.dorf[i].punkte+=500;
          console.log("3");
          console.log(dorf.dorf[i].punkte);
        }
      } */
    }
  }
  }
/*
  for (m=0; m<dorf.dorf[m].length; m++){
    console.log("4");
    if (dorf.dorf[m].punte <= dorf.dorf[m+1].punkte){
      result.push(dorf.dorf[m].name);
    }
  }*/

  
  console.log(result);
  res.send(result);
});

app.get ('/dorf', function (req,res){
  res.json(dorf);
});

app.get ('/userwish/dorf', function (req, res) {
  
  console.log("getrequest");
  res.send(result);
  
});

