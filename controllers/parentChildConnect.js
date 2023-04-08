const pool = require("../models/dbConfig");
// const session = require("express-session");
const path = require("path");
const short = require('short-uuid');
// const bcrypt = require('bcrypt');
module.exports = {
    post: (req, res) => {
        let ParentUserId = req.body.user_id;
        // Quick start with flickrBase58 format
        let uniqueString = short.generate(); // 73WakrfVbNJBaAmhQtEeDv
        console.log(uniqueString);
        res.status(200).json(
            {
                success:true,
                data:[
                    {
                        uniqueString: uniqueString
                    }
                ],
                msg:"random string generated successfully"
            }
        )
    }
};