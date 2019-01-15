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
      if (dorf.dorf[i].naechsteStadt == name && dorf.dorf[i].entfernung <= radius){
          result.push(dorf.dorf[i].name);
        }  
   }
    /*
    if (dorf.dorf[i].gruenflaeche > 200) {
      if (typ == "Naturtyp"){
        dorf.dorf[i].punkte+=100;
      } else {
        dorf.dorf[i].punkte+=50;
      }
    } if (dorf.dorf[i].miete - miete <= 50){
      if (typ == "Aktivitaetstyp"){
        dorf.dorf[i].punkte+=100;
      } else {
        dorf.dorf[i].punkte+=50;
      }
    } for (j=0; j<dorf.dorf[i].aktivitaeten[j]; j++){
        if (dorf.dorf[i].aktivitaeten[j]==aktivitaeten){
          dorf.dorf[i].punkte+=500;
          console.log(dorf.dorf[i].punkte);
        }
    } 
  }

  for (l=0; l<dorf.dorf[l].length; l++){
    if (dorf.dorf[l].punte <= dorf.dorf[l+1].punkte){
      result.push(dorf.dorf[l].name);
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

