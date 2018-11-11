const express = require("express");
const fs = require("fs");
const router = express.Router();
const bodyParser = require('body-parser');

const ressourceName = "kulturerhebung";

global.data = require("./kultur.json");

router.get('/', function(req, res){
    res.status(200).send(data);
});

router.post('/kulturerhebung', bodyParser.json(), function(req, res){
    data.kultur.push(req.body);
    res.status(200);
});


router.get("/:ort", function(req, res){
var id = parseInt(req.params.id);
});

exports.users = [];
module.exports = router;

