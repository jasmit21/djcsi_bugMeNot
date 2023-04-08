//pool.qry 
const mysql = require('mysql');
// const homeController = require('../controllers/homeController');
const pool = mysql.createPool({

    host: "128.199.23.207",
    user: "bugmenot",
    password: "bugmenot",
    database: 'bugMeNot',
    multipleStatements: false,
    connectionLimit : 100,
});
pool.getConnection((err)=>{
    if (err) throw err;    
    console.log("DATABASE CONNECTED!!!");
});

module.exports = pool;

