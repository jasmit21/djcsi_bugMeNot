const express = require("express");

const session = require("express-session");
const authUser = require("../controllers/authUser");
const { log } = require("console");
const block = require("../controllers/blockController")
const childParent = require('../controllers/parentChildConnect')
const sendWebsite = require('../controllers/sendWebsite')
const router = express.Router();


router.get("/",(req,res)=>{
    console.log("Home page");
})
router.post("/authuser", authUser.post );
router.get("/sendWebsites",sendWebsite.get)
router.post('/block',block.post);
router.post('/child-connect',childParent.post);
module.exports = router;