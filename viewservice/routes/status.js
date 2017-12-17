let express = require('express');
let router = express.Router();
router.get('/', function(req, res, next) {

    res.send("Server running like a fine tuned machine. (Express J Trump)")
});

module.exports = router;
