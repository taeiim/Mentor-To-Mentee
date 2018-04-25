const Database = require('../../Database')
const JWT = require('jsonwebtoken');
const key = require('../../TokenKey');

exports.createMentoring = (req, res) => {
  const { title, 
          info, 
          leader_id, 
          category, 
          max_member, 
          start_date, 
          end_date,
          questions } = req.body;
  const mentoring_id = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);

  if(checkJWT(req.headers.authorization)) {
    res.writeHead(498, { 'Content-Type': 'application/json'});
    res.end();
  }

  Database.query('insert into mentoring values(?, ?, ?, ?, ?, ?, ?, ?', [mentoring_id, leader_id, max_member, start_date, end_date])
    .then(result => {
      const question_id = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
      if(result.affectedRows === 1) {
        Database.query('insert into question values(')
        res.status(201).end();
      } else res.status(400).end();
    })
}

exports.getMentoringList = (req, res) => {

}

exports.getMentoring = (req, res) => {
  
}

exports.applyMentoring = (req, res) => {

}

const checkJWT = (token) => {
  try {
    const decoded = jwt.verify(token,key);
    return false;
  } catch(err){
    return true;
  }
}