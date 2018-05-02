const cookieParser = require('cookie-parser')
const createError = require('http-errors');
const express = require('express');
const path = require('path');
const logger = require('morgan');
const app = express();
const mentoring = require('./routes/mentoring/index');
const user = require('./routes/user/index');
const PORT = process.env.PORT || 8080;
const swaggerJSDoc = require('swagger-jsdoc')
const swaggerUi = require('swagger-ui-express');
app.set('port', PORT);


const options = {
  swaggerDefinition: {
    info: { 
      title: 'Men-To-Men', 
      version: '1.0.0', // Version (required)
      description: 'A sample API', // Description (optional)
    }
  },
  apis: ['./api/swagger.yaml'],
}

const swaggerSpec = swaggerJSDoc(options);
app.use('/docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', user);
app.use('/', mentoring);
app.listen(app.get('port'), function () {
  console.log('Example app listening on' + app.get('port') + 'port');
})