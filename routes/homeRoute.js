const express = require("express");

const session = require("express-session");
const authUser = require("../controllers/authUser");
const router = express.Router();


router.post("/auth", authUser.post )
module.exports = router;