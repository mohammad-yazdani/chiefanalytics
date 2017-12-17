import React, {Component} from 'react';

export default class Pipeline extends Component {

    constructor(props) {
        super();
        console.log("States:");
        this.state = {
            stage: props
        };
        console.log(this.state);
    }

    render() {

        let fileActive = "item ";
        let procActive = "item ";
        let pubActive = "item ";
        let active = "active";

        switch (this.state.stage) {
            case 1:
                fileActive += active;
                break;
            case 2:
                procActive += active;
                break;
            case 3:
                pubActive += active;
                break;
            default:
                fileActive += active;
        }

        return (
            <div className="Pipeline">
                <div className="ui three item menu">
                    <a className={fileActive}>File</a>
                    <a className={procActive}>Procedure</a>
                    <a className={pubActive}>Publish</a>
                </div>
            </div>
        );
    }
}