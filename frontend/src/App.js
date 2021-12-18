import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductList from "./products/ProductList";
import Home from "./home/Home";
import ProductDetails from "./products/ProductDetails";
class App extends Component {
    render() {
        return (
          <Router>
              <Switch>
                  <Route path="/products/:id" component={ProductDetails}/>
                  <Route path="/products" exact={true} component={ProductList}/>
                  <Route path="/" exact={true} component={Home}/>
              </Switch>
          </Router>
        );
    }
}

export default App;
