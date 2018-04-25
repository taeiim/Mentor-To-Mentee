const router = require('express').Router();
const controller = require('./auth.controller');

//signup
router.route('/auth/signup').post(controller.signup);

//signin
router.route('/auth/signin').get(controller.signin);

// //find id
// router.route('/user/account').get(controller.findId);

// //find pwd
// router.route('/user/account').patch(controller.);

//refreshToken
router.route('/token').get(controller.refreshToken);

module.exports = router;
