const key = require('../../TokenKey');
const jsonwebtoken = require('jsonwebtoken')
const Database = require('../../Database')

//signup
exports.signup = (req, res) => {
  const { id, pwd, name, age, phone} = req.body;
  let status;
  
  Database.query('select * from user where id = ?', [id, pwd])
    .then(result => {
      if(result.length === 1) res.status(204).end();
      return Database.query('insert into user value(?, ?, ?, ?, ?)', [id, pwd, name, age, phone]);
    })
    .then(result => {
      if(result.affectedRows === 1) return res.status(201).end();
      else return res.status(400).end();
    })
    .catch(err => {
      throw err;
    })
};

//signin
exports.signin = (req, res) => {
  const { id, pwd } = req.body;
  const JWT = makeToken(id);
  let status;

  Database.query('select * from user where id = ? and pwd = ? ', [id, pwd])
    .then(result => {
      
      if(result.length === 1) {
        return res.status(200).end(JSON.stringify({'Authorization' : JWT}));
      } else {
        return res.status(204).end();
      }
    })
    .catch(err => {
      console.log(err);
      
      return err;
    })
};

exports.refreshToken = (req, res) => {
  const { authorization } = req.header;
  const uuid = authorization.verify(key);

  const JWT = makeToken(uuid);
  
  res.status(200).end(JSON.stringify({'Authorization' : JWT}));
};

const makeToken = (uuid) => {
  const payLoad  = {'uid': uuid};
  const JWTOption = {
    algorithm : 'HS256',
    expiresIn : 60 * 60 * 24 * 7,
  }

  return jsonwebtoken.sign(payLoad, key, JWTOption);
}