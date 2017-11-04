const consumer = require('./consumer');

class interpreter extends consumer {
    constructor() {
        //super("interpreter-to-view", 3); // TODO : for test
        super("test", 1); // TODO : for test
    }
}

module.exports = interpreter;