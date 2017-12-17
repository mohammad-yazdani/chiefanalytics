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
                that.fetchList(data);
            });
    }

    fetchList(data) {
        let that = this;
        let files = data.map(function (file) {
            return (
                <div className="four wide column" key={file.name}>
                    <h3>{file.name}</h3>
                    <p>{file.path}</p>
                </div>
            );
        });
        that.setState({ files: files });
    }


    componentDidMount() {
        //this.getList();
        let data = [
            {
                name: "file 1",
                path: "docs/file1"
            },
            {
                name: "file 2",
                path: "docs/file2"
            },
            {
                name: "file 3",
                path: "docs/file3"
            },
            {
                name: "file 4",
                path: "docs/file4"
            }
        ];
        this.fetchList(data);
    }

    render() {
        console.log("Rendering file list.");
        return (
            <div className="ui grid">
                {this.state.files}
            </div>
        );
    }
}

export default FileList;