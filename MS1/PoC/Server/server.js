const express = require('express'); 
const bodyParser = require('body-parser');
const fs = require('fs');

const settings = {
  port: 3000
};

const app = express();

const kultur = require('./kulturerhebung.js');

app.use("/kulturerhebung", kultur);

app.get('/', function(req, res){

  res.send('Willkommen!');
});


app.listen(settings.port, function(){
  console.log("Dienstgeber ist nun auf Port " + settings.port+ " verfügbar");
})