// dependencies
const express = require("express");
require("dotenv").config();
const app = express();
const bodyParser = require("body-parser");
const database = require('./models/dbConfig')
// const userModel = require('./models/userModel')
const homeroute = require('./routes/homeRoute')
//flash
var flash = require("connect-flash");
app.use(flash());

//session
const sessions = require("express-session");
const cookieParser = require("cookie-parser");
const short = require('short-uuid');
//set cookie-parser
app.use(cookieParser());

//----------------session setup------------------------
// var session;
//creating 5 mins from milliseconds
const expiry = 1000 * 60 * 60;

//session middleware
app.use(
  sessions({
    secret: "BugMeNotSecretKey",
    saveUninitialized: true,
    cookie: { maxAge: expiry },
    resave: false,
  })
);
//--------------------session ended-------------------

// register view engine
app.set("view engine", "ejs");

//configuring middlewares to handle post request

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json()); // to support JSON-encoded bodies
app.use(express.json());


//routes 
app.use('/',homeroute)
app.use('/block',homeroute)

app.use('/authuser',homeroute);
app.use('/sendWebsites',homeroute)

//try 
app.use('/child-connect',homeroute);

//creating server
var port = process.env.PORT || 3000;
app.listen(port, (err) => {
  if (err) throw err;
  console.log(`server running http://localhost:${port}`);
});