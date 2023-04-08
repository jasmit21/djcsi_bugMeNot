const pool = require("../models/dbConfig");
const session = require("express-session");
const path = require("path");
const bcrypt = require('bcrypt');
module.exports = {
  post: (req, res) => {
    console.log("================================Login Authentication (post req)=================================");
    var usermobno = req.body.userName;
    var pwd = req.body.user_pwd;
    let notAllowedRole = 2;
    var sq0 = `Select t1.* ,t2.role_name from user_details t1 , role_master t2 where phone_no = ? and t1.user_role_id=t2.role_id`;
    // var sql1 = `Select * from user_details where user_mobno = ? and user_pwd = ? and user_role_id not in (?)`;
    // var sql2 = `select role_name from role_master where role_id = ?`;
    pool.query(sq0, [usermobno], (err, result) => {
      // console.log(result);
      if (err) console.log(err);
    // console.log("dashresult",result);
      if (result.length >= 1) {
        if (result[0].user_role_id === 2) {
          req.flash("Emsg", "Unauthorized Access");
          return res.status(401).json({success: false, message: "Unauthorized Access"});
        }
        bcrypt.compare(pwd, result[0].user_pwd, function (err, isMatch) {
          if (err) {
            console.error(err);
            res.send({
              success: false,
              message: 'Error occurred while comparing passwords'
            });
          } else if (!isMatch) {
            // req.flash("Emsg", "Invalid Credentials");
            // return res.redirect("/");
            return res.status(403).json({success: false, message: "Invalid Credentials"})
          } else {
            var session = req.session;
            // session.userid = req.body.userName;
            // session.senderid = result[0].user_id; //this is for senderId of the user who sends the notification
            // var userRoleId = result[0].user_role_id;
            // session.userRoleId = userRoleId;
            // session.userRole = result[0].role_name;
            // session.user_name = result[0].user_fname + "" + result[0].user_lname  //updating the session stored values after the changes made in profile page 
            // console.log("===================Session===========================\n", req.session);
            // return res.redirect("/dashboard");
            return res.status(200).json({success: true, message: "Login Successful"})
          }
        });
      }
      else {
        // req.flash("Emsg", "User Does Not exists");
        // res.redirect("/");
        return res.status(403).json({success: false, message: "User not found"})
      }
    });
  },
};