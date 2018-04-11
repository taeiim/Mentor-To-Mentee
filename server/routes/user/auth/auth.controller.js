const SHA256 = require('sha256');
const key = require('../../TokenKey');
const JWT = require('jsonwebtoken')
const Database = require('../../Database')

//signup
exports.signup = (req, res) => {
  const { id, pwd, name, age, phone} = req.body;
  let status;

  Database.query('select * from user where id = ?', [id, pwd])
    .then(result => {
      if(result.length === 1) status = 204;
      return Database.query('insert into user value(?, ?, ?, ?, ?)', [id, pwd, name, age, phone]);
    })
    .then(result => {
      if(result.affectedRows === 1) status = 204;
      status = 400;
    })
    .catch(err => {
      throw err;
    })

    res.writeHead(status, { 'Content-Type': 'application/json'});
    res.end();
};

//signin
exports.signin = (req, res) => {
  const { id, pwd } = req.body;
  const payLoad  = {'uid': id};
  const JWTOption = {
    algorithm : 'HS256',
    expiresIn : 60 * 60 * 24 * 7,
  }

  const JWT = jsonwebtoken.sign(payLoad, token.key, JWTOption);
  
  let status;

  Database.query('select * from user where id = ? and pwd = ? ', [id, pwd])
    .then(result => {
      if(result.length === 1) {
        JWT = jsonwebtoken.sign(payLoad, key, JWTOption)
        status = 200;
      }
      status = 204;
    })
    .catch(err => {
      throw err;
    })

    res.writeHead(status, { 'Content-Type': 'application/json'});
    if (status === 200) res.end(JSON.stringify({'Authorization' : jwt}));
    res.end()
};

exports.refreshToken = (req, res) => {
  const { authorization } = req.header;
  const uuid = JWT.verify(token.key);
  const payLoad  = {'uid': uuid};
  const JWTOption = {
    algorithm : 'HS256',
    expiresIn : 60 * 60 * 24 * 7,
  }

  const JWT = jsonwebtoken.sign(payLoad, token.key, JWTOption);

  res.writeHead(200, { 'Content-Type': 'application/json'});
  res.end(JSON.stringify({'Authorization' : JWT}));
};