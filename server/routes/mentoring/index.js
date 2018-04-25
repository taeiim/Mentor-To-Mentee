const router = require('express').Router();
const controller = require('./mentoring.controller');

router.route('/mentoring').post(controller.createMentoring);

router.route('/mentoring').get(controller.getMentoringList);

router.route('/mentoring/:mentoring_id').get(controller.getMentoring);

module.exports = router;