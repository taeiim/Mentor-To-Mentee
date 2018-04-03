const createError = require('http-errors')
const express = require('express')
const path = require('path')
const cookieParser = require('cookie-parser')
const logger = require('morgan')
const app = express()
const user = require('./routes/user/router')
const mentoring = require('./routes/mentoring/router')
app.set('port', 8080)

app.use(logger('dev'))
app.use(express.json())
app.use(express.urlencoded({ extended: false }))
app.use(cookieParser())
app.use(express.static(path.join(__dirname, 'public')))

app.use('/', user)
app.use('/', mentoring)
app.listen(app.get('port'), function () {
  console.log('Example app listening on' + app.get('port') + 'port');
})