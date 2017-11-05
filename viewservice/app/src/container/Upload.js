import React, { Component } from 'react';
import FileInput from "../component/FileInput";

class Upload extends Component {
    render() {
        return (
            <div className="Upload">
                <FileInput/>
            </div>
        );
    }
}

export default Upload;