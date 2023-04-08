const pool = require("../models/dbConfig");
const http = require("http");
const querystring = require("querystring");

module.exports = {
  post: (req, res) => {
    let website = req.body.website_url;
    let websiteBlockStatus = req.body.webstatus;
    let qry = `UPDATE website_master SET isDisabled = ${websiteBlockStatus} WHERE website_id= ${website}`;

    console.log(qry);

    pool.query(qry, (err, result) => {
      if (err) {
        console.error(err);
        res.status(400).json({
          success: false,
          msg: "Qry failed to execute",
        });
        return;
      }

      console.log("Flag did change");

      let qry2 = `SELECT isDisabled FROM website_master WHERE website_id = ${website}`;

      pool.query(qry2, (err, result) => {
        if (err) {
          console.error(err);
          res.status(400).json({
            success: false,
            msg: "Qry failed to execute",
          });
          return;
        }

        console.log("Ho raha hai!", result[0].isDisabled);

        let PiDisable = result[0].isDisabled;

        if (PiDisable === 1) {
          console.log("Adding website to blocklist");

          const apiToken = process.env.PI_HOLE_API_KEY;
          const url = website;

          const postData = querystring.stringify({
            auth: apiToken,
            list: "black",
            add: url,
          });

          const options = {
            hostname: process.env.PI_HOST_IP,
            port: 80,
            path: "/admin/api.php",
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
              "Content-Length": Buffer.byteLength(postData),
            },
          };

          const request = http.request(options, (response) => {
            response.setEncoding("utf8");
            response.on("data", (chunk) => {
              console.log(chunk);
            });
          });

          request.on("error", (error) => {
            console.error(error);
          });

          request.write(postData);
          request.end();
        } else {
          console.log("Removing website from blocklist");

          const apiToken = process.env.PI_HOLE_API_KEY;
          const url = website;

          const postData = querystring.stringify({
            auth: apiToken,
            list: "black",
            sub: url,
          });

          const options = {
            hostname: process.env.PI_HOST_IP,
            port: 80,
            path: "/admin/api.php",
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
              "Content-Length": Buffer.byteLength(postData),
            },
          };

          const request = http.request(options, (response) => {
            response.setEncoding("utf8");
            response.on("data", (chunk) => {
              console.log(chunk);
            });
          });

          request.on("error", (error) => {
            console.error(error);
          });

          request.write(postData);
          request.end();
        }

        res.status(200).json({
          success: true,
          msg: "status updated successfully",
        });
      });
    });
  },
};
