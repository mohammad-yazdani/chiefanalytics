const consumer = require('./consumer');

class interpreter extends consumer {
    constructor() {
        //super("interpreter-to-view", 3); // TODO : for test
        super("none", 1); // TODO : for test
    }
}

module.exports = interpreter;