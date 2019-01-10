console.log('Server startet');

var express = require ('express');

var app = express();

var server = app.listen(3000, listening);

function listening(){
  console.log("listening..")
}

app.use(express.static('city'));

app.get('/search/:dorf', sendCity);
app.post('/userwish', sendAntwort);
app.get('/userwish/:uid/dorf', sendDorf);

function sendCity(req, res) {
  var data = req.params;
  var reply = "Wir werden Ihnen die Übersicht von " + data.city + " ausgeben";
  res.send(reply);
}

function sendAntwort(req,res){
  var data = req.params;
  var reply = "Es hat geklappt";
  res.send(reply);
}
//Pseudocode
/*
function sendDorf(req,res){

  var data = req.params;
  geeigneteOrte[]

  for alle Orte
    if u.stadt == d.nächsteStadt && u.radius >= d.Entfernung && u.miete >= d.Miete 
      for d.aktivitaeten 
        if u.aktivitaeten == d.aktivitaeten



  res.send(dorf);
}