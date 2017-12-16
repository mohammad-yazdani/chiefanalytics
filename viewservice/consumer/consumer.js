let kafka = require('kafka-node');
let feedQueue = require('../core/feedQueue');

class consumer {

    constructor(defaultTopic, partition) {
        this.topics = [{
            topic: defaultTopic, partition: partition
        }];
        this.client = new kafka.Client();
        this._instance = new kafka.Consumer(
            this.client,
            this.topics
            ,
            {
                autoCommit: false
            }
        );

        this.instance.on('message', consumer.addToQueue);
    }

    static addToQueue(message) {
        let payload = message.value;
        try {
            payload = JSON.parse(payload);
        }
        catch(_) {
            console.error("Cannot parse " + message.value);
        }
        (new feedQueue()).add(payload.name, payload.file);
    }

    get instance() {
        return this._instance;
    }

    addTopic(topic) {
        this.instance.addTopic([topic], function (err, added) {
            console.log(added);
            console.log(err);
        });
    }

    removeTopic(topic) {
        this.instance.removeTopic([topic], function (err, removed) {
            console.log(removed);
            console.log(err);
        });
    }

    commit() {
        this.instance.commit(function (err, data) {
            console.log(data);
            console.log(err);
        })
    }

    close(force = false) {
        this.instance.close(force, function (err, closed) {
            console.log(closed);
            console.log(err);
        });
    }
}

module.exports = consumer;