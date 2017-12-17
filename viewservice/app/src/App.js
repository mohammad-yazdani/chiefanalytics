import React, { Component } from 'react';
import './App.css';
import Files from "./container/Files";
import Pipeline from "./component/Pipeline";
import {Button} from "semantic-ui-react";

class App extends Component {

  constructor() {
    super();
    this.state = {
      stage: 0
    };
    console.log(this.state.stage);
  }

  render() {
    return (
      <div className="App">
          <h1 className="header">Chief Analytics</h1>
          <div className="ui divider"/>
          <p>Input --> stuff done on it --> output ;)</p>
          <Pipeline props={this.state.stage}/>
          <div className="ui divider"/>
          <Files/>
          <br/>
          <Button onClick={() => this.setState({stage: this.state.stage + 1})}>Next</Button>
      </div>
    );
  }
}

export default App;
