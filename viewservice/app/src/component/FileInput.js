import React, { Component } from 'react';

class FileInput extends Component {

    constructor() {
        super();
        this.state = {
            files: [],
        };
        this.inputValue = [];
    }

    handleInput(input) {
        if (input.files.length <= 0) return;
        let formData = new FormData();
        const files = input.files;
        console.log("Files:");
        console.log(files);
        formData.append("file", files[0]);

        let url = 'http://localhost:8080/uploadFile?name=file';

        fetch(url, {
            method: 'POST',
            body: formData
        })
            .then(function (response) {
                if (response.status >= 400) {
                    throw new Error("Bad response from server");
                }
                return response.json();
            }).then(function (data) {
            console.log("Data:" + data);
        }).catch(function (err) {
            console.log(err.message);
        });
    }



    render() {
        console.log("Loading upload page.");
        return (
            <div className="File-input">
                <br/>
                <label>Please select your file:</label>
                <input type="file" name="file" ref={(ref) => {this.inputValue = ref;}}
                       onChange={evt => {
                           console.log(evt);
                           this.handleInput(this.inputValue)
                       }}/>
                <br/>
                <br/>
            </div>
        );
    }
}

export default FileInput;