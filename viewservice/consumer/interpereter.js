const consumer = require('./consumer');

class interpreter extends consumer {
    constructor() {
        //super("view-service");
        super("none", 3); // TODO : for test
    }
}

module.exports = interpreter;