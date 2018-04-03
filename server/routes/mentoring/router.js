const express = require('express')
const modules = require('./modules')
const jwt = require('jsonwebtoken')
const key = require('../../TokenKey')
const router = express.Router()

router.route('/mentoring').post( (req, res) => {
  const { title, 
          info, 
          leader_id, 
          category, 
          max_member, 
          start_date, 
          end_date } = req.body
          
  if(checkJWT(req.headers.authorization)) {
    res.writeHead(498, { 'Content-Type': 'application/json'})
    res.end()
  }
  
  modules.createMentoring(title, info, leader_id, category, max_member, start_date, end_date, (status => {
    res.writeHead(status, { 'Content-Type': 'application/json'})
    res.end()
  }))
})

const checkJWT = (token) => {
  try {
    const decoded = jwt.verify(token,key);
    return false
  } catch(err){
    console.log(err);
    
    return true
  }
}

module.exports = router