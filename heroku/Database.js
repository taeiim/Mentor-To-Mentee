const mysql = require('mysql');

const conn = mysql.createConnection({
  host: "us-cdbr-iron-east-05.cleardb.net",
  user: "bdf3f668cf65a4",
  password: "35e3747d",
  database: "heroku_c867bf8940d5f06"
});

const Database = {
  conn: mysql.createConnection({
    host: "us-cdbr-iron-east-05.cleardb.net",
    user: "bdf3f668cf65a4",
    password: "35e3747d",
    database: "heroku_c867bf8940d5f06"
  }),
  
  
  query: (sql, args) =>{
    return new Promise((resolve, reject) => {
      conn.query(sql, args, (err, result) => {
        if (err) return reject(err)
        resolve(result); 
      })
    })
  }
  
}

module.exports = Database;