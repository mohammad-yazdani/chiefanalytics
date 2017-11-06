let express = require('express');
let router = express.Router();
let queue = require('../core/feedQueue');
router.get('/singular', function(req, res, next) {
    let result = new queue().get(req.param("id"));
    res.send(result);
});

router.get('/', function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.send((new queue().queue));
});

module.exports = router;
