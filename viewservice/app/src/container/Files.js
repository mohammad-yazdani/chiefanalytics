import React, { Component } from 'react';
import FileList from "../component/FileList";
import Upload from "./Upload";

class Files extends Component {
    render() {
        return (
            <div className="Files">
                <Upload/>
                <FileList/>
            </div>
        );
    }
}

export default Files;