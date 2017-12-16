import React, { Component }  from 'react';

class FileList extends Component {

    constructor() {
        super();
        this.state = {
            files: []
        }
    }

    getList() {
        let that = this;
        let url = 'http://localhost:3030/feed';

        fetch(url)
            .then(function(response) {
                if (response.status >= 400) {
                    throw new Error("Bad response from server");
                }
                return response.json();
            })
            .then(function(data) {
                console.log("Data:" + data);
                let files = data.map(function (file) {
                    return (
                        <li key={file.name}>
                            <h3>{file.name}</h3>
                            <p>{file.path}</p>
                        </li>
                    );
                });
                that.setState({ files: files });
            });
    }


    componentDidMount() {
        this.getList();
    }

    render() {
        console.log("Rendering file list.");
        return (
            <div className="File-list">
                <header className="File-list-header">
                    <h1 className="File-list-title">List of files:</h1>
                </header>
                <ul>
                    {this.state.files}
                </ul>
            </div>

        );
    }
}

export default FileList;