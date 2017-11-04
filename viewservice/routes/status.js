let express = require('express');
let router = express.Router();
let interpreter = require('../consumer/interpereter');

router.get('/', function(req, res, next) {

    // Checking version
    console.log(process.versions);

    console.log(new interpreter());

    res.send("Server running like a fine tuned machine. (Express J Trump)")
});

module.exports = router;
