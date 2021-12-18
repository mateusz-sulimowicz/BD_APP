import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductList from "./products/ProductList";
import Home from "./home/Home";

class App extends Component {
    render() {
        return (
          <Router>
              <Switch>
                  <Route path="/" exact={true} component={Home}/>
                  <Route path="/products" exact={true} component={ProductList}/>
              </Switch>
          </Router>
        );
    }
}

export default App;
