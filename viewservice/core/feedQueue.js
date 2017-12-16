let instance = null;

class feedQueue {

    constructor() {
        if (!instance) {
            instance = this;
        }

        this.queue = [];
        return instance;
    }

    add(id, payload) {
        if (id === undefined || payload === undefined) return;
        let pack = {"name": id, "path": payload};
        this.queue.push(pack);
        console.info("Queue:");
        console.info(this.queue);
    }

    get(id) {
        // TODO : move from front
        // TODO : This is a promise so make wait
        // TODO : Finished after the payload arrives or fails
        let payload = this.queue[id];
        delete this.queue[id];
        console.info("Queue:");
        console.info(this.queue);
        return payload;
    }
}

module.exports = feedQueue;