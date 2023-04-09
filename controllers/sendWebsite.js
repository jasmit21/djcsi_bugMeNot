const { log } = require("console");
const pool = require("../models/dbConfig");
// const session = require("express-session");
const path = require("path");
// const short = require('short-uuid');
// const bcrypt = require('bcrypt');
module.exports = {
    get: (req, res) => {
        let qry = `SELECT website_url from website_master`;
        pool.query(qry, (err, data) => {
            if (err) {
                console.error(err);
                res.status(400).json({
                    success: false,
                    msg: "Qry failed to execute",
                });
                return;
            }
            let result = JSON.parse(JSON.stringify(data))
            console.log("data",result);
            res.status(200).json(
                {
                    success: true,
                    data:result,
                    msg: "random string generated successfully"
                }
            )
        })
    }
};