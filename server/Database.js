const mysql = require('mysql');

const conn = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "hy980615",
  database: "mentomen"
});

const Database = {
  conn: mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "hy980615",
    database: "mentomen"
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

module.exports = Database