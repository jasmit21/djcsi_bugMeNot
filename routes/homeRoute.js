const express = require("express");

const session = require("express-session");
const authUser = require("../controllers/authUser");
const { log } = require("console");
const block = require("../controllers/blockController")
const router = express.Router();


router.get("/",(req,res)=>{
    console.log("Home page");
})
router.post("/authuser", authUser.post );

router.post('/block',block.post);
module.exports = router;