const pool = require("../models/dbConfig");
// const session = require("express-session");
const path = require("path");
// const bcrypt = require('bcrypt');
module.exports = {
  post: (req, res) => {
    let user_phone = req.body.user_phone;
    let websiteBlockStatus = req.body.webstatus;
    qry = `UPDATE WebsiteAndUser_Mapping SET isDisabled = ${websiteBlockStatus} where user_id= (SELECT user_id from user_details where phone_no = ${user_phone})
    `;
    pool.query(qry,(err,result)=>{
        if (err) {
            throw err
            res.status(400).json({
                success:false,
                msg:"Qry failed to execute"
            })
        };
        console.log("Flag did change");
        res.status(200).json({
            success:true,
            msg:"status updated successfully"
        })
    })

  }
};