const express = require('express')
const modules = require('./modules')
const SHA256 = require('sha256')

const router = express.Router()

//signup
router.route('/auth/signup').post( (req, res) => {
  const { id, pwd, name, age, phone} = req.body
  
  modules.signup(id, SHA256(pwd), name, age, phone, status => {
    res.writeHead(status, { 'Content-Type': 'application/json'})
    res.end()
  })

})

//signin
router.route('/auth/signin').post( (req, res) => {
  const { id, pwd } = req.body

  modules.signin(id, SHA256(pwd), (status, jwt) => {  
    res.writeHead(status, { 'Content-Type': 'application/json'})
    if (status === 200) res.end(JSON.stringify({'Authorization' : jwt}))
    res.end()
  })
})

module.exports = router