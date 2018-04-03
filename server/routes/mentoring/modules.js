const Database = require('../../Database')

module.exports = {
  createMentoring : (title, info, leader_id, category, max_member, start_date, end_date, callback) => {
    const mentoring_id = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
    
    Database.query('insert into mentoring values(?, ?, ?, ?, ?, ?, ?, ?', [mentoring_id, leader_id, max_member, start_date, end_date])
      .then(result => {
        if(result.affectedRows === 1) callback(201)
        else callback(400)
      })
  }
}

// function makeid() {
//   let status= true
//   let id = ""
//   const possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//   while (status) {
//     Database.query('select * from mentoring where mentoring_id = ?', Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15))
//     .then(result => {
//         if(result.length === 1) { } 
//         else {
//           status =false
//         }
//       })
//       .catch(err => {
//         throw err
//       })
//   }
//   return id
// }
