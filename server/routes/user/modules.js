const Database = require('../../Database')
const jsonwebtoken = require('jsonwebtoken')
const key = require('../../TokenKey')

module.exports = {
  signup : (id, pwd, name, age, phone, callback) => {
    Database.query('select * from user where id = ?', [id, pwd])
      .then(result => {
        if(result.length === 1) callback(204)
        return Database.query('insert into user value(?, ?, ?, ?, ?)', [id, pwd, name, age, phone])
      })
      .then(result => {
        if(result.affectedRows === 1) callback(201)
        else callback(400)
      })
      .catch(err => {
        throw err
      })
  },
  signin : (id, pwd, callback) => {
    let jwt = '';
    let payLoad  = {'uid': id};

    Database.query('select * from user where id = ? and pwd = ? ', [id, pwd])
      .then(result => {
        if(result.length === 1) {
          jwt = jsonwebtoken.sign(payLoad, key, {
          algorithm : 'HS256',
          expiresIn : 60 * 60 * 24 * 7})
          callback(200, jwt)
        }
        else callback(204, jwt)
      })
      .catch(err => {
        throw err
      })
  }
}